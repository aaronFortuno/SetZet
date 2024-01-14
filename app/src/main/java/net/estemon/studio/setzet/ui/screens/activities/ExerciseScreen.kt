package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun ExerciseScreen(
    navController: NavController,
    exerciseType: ExerciseType,
    difficultyLevel: DifficultyLevel
) {

    BaseLayout {
        Text(text = "Exercises of ${exerciseType.name} with ${difficultyLevel.name} difficulty")
    }
}