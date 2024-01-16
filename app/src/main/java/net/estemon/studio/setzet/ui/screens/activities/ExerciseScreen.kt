package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    val timeLimit = 120
    val timeLeft = remember { mutableIntStateOf(timeLimit) }
    val isTimeRunning = remember { mutableStateOf(false) }
    val isReviewPhase = remember { mutableStateOf(false) }

    LaunchedEffect(isTimeRunning.value) {
        if (isTimeRunning.value) {
            while (timeLeft.intValue > 0) {
                delay(1000L)
                timeLeft.intValue--
            }
        }
    }

    val isShowPopup = remember { mutableStateOf(true) }
    if (isShowPopup.value) {
        AlertDialog(
            onDismissRequest = {  },
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
            dismissButton = {  }
        )
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
                            currentActiveIndex = currentActiveIndex
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                TimerDisplay(timeLeft = timeLeft)
            }
            CustomNumericKeyboard(
                onNumberClick = { handleNumberInput(it) },
                onDeleteClick = { handleDelete() },
                onNextClick = { handleNext() }
            )
        }
    }

    LaunchedEffect(currentActiveIndex.intValue) {
        val scrollToIndex = maxOf(0, (currentActiveIndex.intValue - halfScreenHeightInItems))
        listState.animateScrollToItem(scrollToIndex)
    }
}