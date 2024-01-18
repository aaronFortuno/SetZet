package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.viewmodels.ExerciseViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SingleOperationCard(
    viewModel: ExerciseViewModel,
    index: Int,
    operands: Pair<Int, Int>,
    exerciseType: ExerciseType,
    userAnswer: String,
    onAnswerChanged: (String) -> Unit,
    isActive: Boolean,
    currentActiveIndex: MutableState<Int>,
    isReviewPhase: Boolean
) {
    val backgroundColor = if (isActive)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent
    val textSize = if (index == currentActiveIndex.value) 32.sp else 16.sp
    val padding = if (isActive) 8.dp else 0.dp
    val alpha = if (isReviewPhase) 1f else 0.5f
    val reviewInitialCheckState = if (userAnswer.isEmpty()) CheckState.Unable else CheckState.Unchecked
    val reviewCheckState = remember { mutableStateOf(reviewInitialCheckState) }
    val isReviewDone = viewModel.isReviewDone
    val reviewDoneTint = if (isReviewDone.value) Color.Green else Color.Transparent

    val focusManager = LocalFocusManager.current

    val operatorString =
        when (exerciseType) {
            ExerciseType.ADDITION -> "+"
            ExerciseType.SUBTRACTION -> "-"
            ExerciseType.MULTIPLICATION -> "×"
            ExerciseType.DIVISION -> "÷"
        }

    Surface(
        modifier = Modifier
            .padding(padding)
            .background(backgroundColor)
            .clickable {
                currentActiveIndex.value = index
            },
        shadowElevation = if (isActive) 8.dp else 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "${index + 1}   ",
                fontWeight = FontWeight.ExtraLight,
                color = Color.Gray
            )
            Text(
                text = "${operands.first} $operatorString ${operands.second} = ",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = textSize
            )

            val keyboardController = LocalSoftwareKeyboardController.current
            keyboardController?.hide()

            OutlinedTextField(
                value = userAnswer,
                onValueChange = { newValue ->
                    onAnswerChanged(newValue)
                    currentActiveIndex.value = index
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            currentActiveIndex.value = index
                            keyboardController?.hide()
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                        )
                    },
                singleLine = true,
                readOnly = (index != currentActiveIndex.value && !isReviewPhase),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = textSize
                )
            )
            Row(
                modifier = Modifier
                    .alpha(alpha)
                    .align(Alignment.CenterVertically)
            ) {
                TriStateCheckbox(
                    state = reviewCheckState.value,
                    onStateChange = { newState ->
                        if (reviewCheckState.value != CheckState.Unable) {
                            reviewCheckState.value = newState
                        }
                    }
                )
                Icon(
                    imageVector = setReviewIcon(operands, exerciseType, userAnswer, reviewCheckState.value),
                    contentDescription = null,
                    tint = reviewDoneTint
                )
            }
        }
    }
}

private fun setReviewIcon(
    operands: Pair<Int, Int>,
    exerciseType: ExerciseType,
    userAnswer: String,
    reviewCheckState: CheckState
): ImageVector {
    val userAnswerInt = if (userAnswer != "") userAnswer.toInt() else -1
    val result = when (exerciseType) {
        ExerciseType.ADDITION -> operands.first + operands.second
        ExerciseType.SUBTRACTION -> operands.first - operands.second
        ExerciseType.MULTIPLICATION -> operands.first * operands.second
        ExerciseType.DIVISION -> operands.first / operands.second
    }

    if (
        (userAnswerInt == result && reviewCheckState == CheckState.Correct) ||
        (userAnswerInt != result && reviewCheckState == CheckState.Unchecked)
        ) {
        return Icons.Default.Check
    } else {
        return Icons.Default.Clear
    }
}
