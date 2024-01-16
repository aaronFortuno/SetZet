package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.estemon.studio.setzet.models.enums.ExerciseType

@Composable
fun SingleOperationCard(
    index: Int,
    operands: Pair<Int, Int>,
    exerciseType: ExerciseType,
    userAnswer: String,
    onAnswerChanged: (String) -> Unit,
    isActive: Boolean,
    currentActiveIndex: MutableState<Int>
) {
    val backgroundColor = if (isActive)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
    val textSize = if (index == currentActiveIndex.value) 32.sp else 16.sp
    val padding = if (isActive) 8.dp else 0.dp

    fun getOperatorSymbol(exerciseType: ExerciseType): String {
        return when (exerciseType) {
            ExerciseType.ADDITION -> "+"
            ExerciseType.SUBTRACTION -> "-"
            ExerciseType.MULTIPLICATION -> "×"
            ExerciseType.DIVISION -> "÷"
        }
    }

    Surface(
        modifier = Modifier
            .padding(padding)
            .background(backgroundColor),
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
                text = "${operands.first} ${getOperatorSymbol(exerciseType)} ${operands.second} = ",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = textSize
            )
            OutlinedTextField(
                value = userAnswer,
                onValueChange = { newValue ->
                    onAnswerChanged(newValue)
                    currentActiveIndex.value = index
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(

                ),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            currentActiveIndex.value = index
                        }
                    },
                singleLine = true,
                readOnly = index != currentActiveIndex.value,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = textSize
                )
            )
        }
    }
}