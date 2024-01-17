package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import net.estemon.studio.setzet.models.StdExerciseKey
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.models.standardExercises
import net.estemon.studio.setzet.ui.components.CustomNumericKeyboard
import net.estemon.studio.setzet.ui.components.SingleOperationCard
import net.estemon.studio.setzet.ui.components.TimerDisplay
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun ExerciseScreen(
    navController: NavController,
    exerciseType: ExerciseType,
    difficultyLevel: DifficultyLevel
) {
    val exercises = standardExercises[StdExerciseKey(exerciseType, difficultyLevel)] ?: emptyList()
    val inputs = remember { mutableStateListOf<String>() }
    val currentActiveIndex = remember { mutableIntStateOf(0) }
    val listState = rememberLazyListState()
    
    if (inputs.isEmpty()) {
        exercises.forEach { _ -> inputs.add("") }
    }

    val timeLimitSeconds = 5 /* TODO set work time */
    val timeLeft = remember { mutableIntStateOf(timeLimitSeconds) }
    val isTimeRunning = remember { mutableStateOf(false) }
    val isReviewPhase = remember { mutableStateOf(false) }
    val dismissReviewDialog = remember { mutableStateOf(false) }

    ExerciseTimer(isTimeRunning, timeLeft, isReviewPhase)

    val isShowPopup = remember { mutableStateOf(true) }
    if (isShowPopup.value) {
        StartExerciseAlertDialog(isShowPopup, isTimeRunning)
    }

    if (isReviewPhase.value && !dismissReviewDialog.value) {
        ReviewPhaseAlertDialog(isReviewPhase, dismissReviewDialog)
    }

    fun handleNumberInput(number: Int) {
        val updatedInput = inputs[currentActiveIndex.intValue] + number.toString()
        inputs[currentActiveIndex.intValue] = updatedInput
    }

    fun handleDelete() {
        val currentInput = inputs[currentActiveIndex.intValue]
        if (currentInput.isNotEmpty()) {
            inputs[currentActiveIndex.intValue] = currentInput.dropLast(1)
        }
    }

    fun handleNext() {
        if (currentActiveIndex.intValue < inputs.size -1) {
            currentActiveIndex.intValue++
        }
    }

    val itemHeight = 48.dp
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val screenHeightDp = displayMetrics.heightPixels / displayMetrics.density
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }
    val halfScreenHeightInItems = (screenHeightDp / (2 * itemHeightPx)).toInt()

    BaseLayout {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Exercises of ${exerciseType.name} with ${difficultyLevel.name} difficulty")
                Box(modifier = Modifier.weight(1f)) {
                    LazyColumn(state = listState) {
                        itemsIndexed(exercises) { index, exercise ->
                            SingleOperationCard(
                                index = index,
                                operands = exercise,
                                exerciseType = exerciseType,
                                userAnswer = inputs[index],
                                onAnswerChanged = {
                                    inputs[index] = it
                                },
                                isActive = index == currentActiveIndex.intValue,
                                currentActiveIndex = currentActiveIndex,
                                isReviewPhase.value
                            )
                        }
                    }
                }
                if (!isReviewPhase.value) {
                    CustomNumericKeyboard(
                        onNumberClick = { handleNumberInput(it) },
                        onDeleteClick = { handleDelete() },
                        onNextClick = { handleNext() }
                    )
                }
            }
            if (!isReviewPhase.value) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                ) {
                    TimerDisplay(timeLeft = timeLeft)
                }
            }
        }
    }

    LaunchedEffect(currentActiveIndex.intValue) {
        val scrollToIndex = maxOf(0, (currentActiveIndex.intValue - halfScreenHeightInItems))
        listState.animateScrollToItem(scrollToIndex)
    }
}

@Composable
private fun StartExerciseAlertDialog(
    isShowPopup: MutableState<Boolean>,
    isTimeRunning: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Ready to start?") },
        text = { Text(text = "You'll have 2 minutes to complete as much calculations as you can") },
        confirmButton = {
            Button(
                onClick = {
                    isShowPopup.value = false
                    isTimeRunning.value = true
                }
            ) {
                Text(text = "Start!")
            }
        },
        dismissButton = { }
    )
}

@Composable
private fun ReviewPhaseAlertDialog(
    isReviewPhase: MutableState<Boolean>,
    dismissReviewDialog: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Time up!") },
        text = { Text(text = "Now's time to check your work!") },
        confirmButton = {
            Button(
                onClick = {
                    isReviewPhase.value = true
                    dismissReviewDialog.value = true
                }
            ) {
                Text(text = "Check")
            }
        },
        dismissButton = { }
    )
}

@Composable
private fun ExerciseTimer(
    isTimeRunning: MutableState<Boolean>,
    timeLeft: MutableIntState,
    isReviewPhase: MutableState<Boolean>
) {
    LaunchedEffect(isTimeRunning.value) {
        if (isTimeRunning.value) {
            while (timeLeft.intValue > 0) {
                delay(1000L)
                timeLeft.intValue--
            }
            if (timeLeft.intValue <= 0) {
                isReviewPhase.value = true
            }
        }
    }
}