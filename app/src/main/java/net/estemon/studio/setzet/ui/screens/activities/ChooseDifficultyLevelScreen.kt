package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.models.enums.availableExercises
import net.estemon.studio.setzet.ui.navigation.NavigationRoutes
import net.estemon.studio.setzet.ui.navigation.exerciseScreenRoute
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun ChooseDifficultyLevelScreen(
    navController: NavController,
    exerciseType: ExerciseType = ExerciseType.ADDITION,
    onDifficultySelected: (DifficultyLevel) -> Unit
) {
    val levels = availableExercises[exerciseType] ?: emptyList()
    
    BaseLayout {
        Text(text = "Choose your difficulty")
        levels.forEach {level ->
            Button(
                onClick = {
                    navController.navigate(exerciseScreenRoute(exerciseType, level))
                }
            ) {
                Text(text = level.toString())
            }
        }
    }
}