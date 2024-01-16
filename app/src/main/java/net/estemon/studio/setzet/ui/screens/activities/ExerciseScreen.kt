package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.estemon.studio.setzet.models.StdExerciseKey
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.models.standardExercises
import net.estemon.studio.setzet.ui.components.SingleOperationCard
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
                            isActive = index == currentActiveIndex.value,
                            currentActiveIndex = currentActiveIndex
                        )
                    }
                }
            }
/*            CustomNumericKeyboard(
                onNumberClick = { handleNumberInput(it) },
                onDeleteClick = { handleDelete() },
                onNextClick = { handleNext() }
            )*/
        }
    }

    LaunchedEffect(currentActiveIndex.value) {
        val scrollToIndex = maxOf(0, (currentActiveIndex.value - halfScreenHeightInItems))
        listState.animateScrollToItem(scrollToIndex)
    }
    /*    fun handleNumberInput(number: Int) {
    val updatedInput = inputs[currentActiveIndex] + number.toString()
    inputs[currentActiveIndex] = updatedInput
}

fun handleDelete() {
    val currentInput = inputs[currentActiveIndex]
    if (currentInput.isNotEmpty()) {
        inputs[currentActiveIndex] = currentInput.dropLast(1)
    }
}

fun handleNext() {
    if (currentActiveIndex < inputs.size -1) {
        currentActiveIndex++
    }
}*/
}