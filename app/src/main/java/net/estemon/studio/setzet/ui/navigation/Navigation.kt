package net.estemon.studio.setzet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.estemon.studio.setzet.ui.screens.HomeScreen
import net.estemon.studio.setzet.ui.screens.activities.ChooseActivityScreen
import net.estemon.studio.setzet.ui.screens.init.LoginScreen
import net.estemon.studio.setzet.ui.screens.init.SplashScreen

object NavigationRoutes {
    const val SPLASH_SCREEN = "splash"
    const val LOGIN_SCREEN = "login"
    const val HOME_SCREEN = "home"
    const val CHOOSE_ACTIVITY_SCREEN = "chooseActivity"
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
        composable(NavigationRoutes.CHOOSE_ACTIVITY_SCREEN) { ChooseActivityScreen(navController) }
    }
}