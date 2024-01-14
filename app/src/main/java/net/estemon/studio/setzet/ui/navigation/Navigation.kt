package net.estemon.studio.setzet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.ui.screens.activities.ChooseDifficultyLevelScreen
import net.estemon.studio.setzet.ui.screens.init.HomeScreen
import net.estemon.studio.setzet.ui.screens.activities.ChooseExerciseScreen
import net.estemon.studio.setzet.ui.screens.activities.ExerciseScreen
import net.estemon.studio.setzet.ui.screens.init.LoginScreen
import net.estemon.studio.setzet.ui.screens.init.SplashScreen

object NavigationRoutes {
    const val SPLASH_SCREEN = "splash"
    const val LOGIN_SCREEN = "login"
    const val HOME_SCREEN = "home"
    const val CHOOSE_EXERCISE_TYPE_SCREEN = "chooseActivity"
    const val CHOOSE_DIFFICULTY_LEVEL_SCREEN = "chooseDifficulty/{exerciseType}"
}

fun chooseDifficultyRoute(exerciseType: ExerciseType): String {
    return "chooseDifficulty/${exerciseType.name}"
}

fun exerciseScreenRoute(exerciseType: ExerciseType, difficultyLevel: DifficultyLevel): String {
    return "exercise/${exerciseType.name}/${difficultyLevel.name}"
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.SPLASH_SCREEN
    ) {
        composable(NavigationRoutes.SPLASH_SCREEN) { SplashScreen(navController) }
        composable(NavigationRoutes.LOGIN_SCREEN) { LoginScreen(navController) }
        composable(NavigationRoutes.HOME_SCREEN) { HomeScreen(navController) }
        composable(NavigationRoutes.CHOOSE_EXERCISE_TYPE_SCREEN) { ChooseExerciseScreen(navController) }
        composable(
            route = NavigationRoutes.CHOOSE_DIFFICULTY_LEVEL_SCREEN,
            arguments = listOf(navArgument("exerciseType") {
                type = NavType.StringType
                defaultValue = ExerciseType.ADDITION.name
            })
        ) { backStackEntry ->
            val exerciseType = backStackEntry.arguments?.getString("exerciseType") ?: ExerciseType.ADDITION.name
            val exerciseTypeEnum = ExerciseType.valueOf(exerciseType)
            ChooseDifficultyLevelScreen(
                navController,
                exerciseTypeEnum,
                onDifficultySelected = { level ->
                    val route = exerciseScreenRoute(exerciseTypeEnum, level)
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = "exercise/{exerciseType}/{difficultyLevel}",
            arguments = listOf(
                navArgument("exerciseType") { type = NavType.StringType },
                navArgument("difficultyLevel") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val exerciseTypeArg = backStackEntry.arguments?.getString("exerciseType")
            val difficultyLevelArg = backStackEntry.arguments?.getString("difficultyLevel")

            val exerciseType = ExerciseType.valueOf(exerciseTypeArg ?: ExerciseType.ADDITION.name)
            val difficultyLevel = DifficultyLevel.valueOf(difficultyLevelArg ?: DifficultyLevel.PRI1.name)

            ExerciseScreen(navController, exerciseType, difficultyLevel)
        }
    }
}