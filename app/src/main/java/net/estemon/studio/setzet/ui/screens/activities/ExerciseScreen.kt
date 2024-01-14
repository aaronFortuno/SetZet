package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import net.estemon.studio.setzet.models.StdExerciseKey
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.models.standardExercises
import net.estemon.studio.setzet.ui.components.CustomNumericKeyboard
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun ExerciseScreen(
    navController: NavController,
    exerciseType: ExerciseType,
    difficultyLevel: DifficultyLevel
) {
    var currentInput by remember { mutableStateOf("") }
    val exercises = standardExercises[StdExerciseKey(exerciseType, difficultyLevel)] ?: emptyList()
    val inputs = remember { mutableStateListOf<String>() }
    
    if (inputs.isEmpty()) {
        exercises.forEach { _ -> inputs.add("") }
    }
    
    fun handleNumberInput(number: Int) {
        currentInput += number.toString();
    }

    fun handleDelete() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
        }
    }

    fun handleNext() {

    }

    BaseLayout {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Exercises of ${exerciseType.name} with ${difficultyLevel.name} difficulty")
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn() {
                    itemsIndexed(exercises) { index, exercise ->
                        TextField(
                            value = inputs[index],
                            onValueChange = {
                                inputs[index] = it
                            },
                            label = { Text("${exercise.first} + ${exercise.second} =")}
                        )
                    }
                }
            }
            CustomNumericKeyboard(
                onNumberClick = { handleNumberInput(it) },
                onDeleteClick = { handleDelete() },
                onNextClick = { handleNext() }
            )
        }


/*        exercises.forEach { (left, right) ->
            Text(text = "$left + $right = ?")
            TextField(
                value = currentInput,
                onValueChange = {

                },
                readOnly = true
            )
        }*/


    }
}