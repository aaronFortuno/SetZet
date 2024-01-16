package net.estemon.studio.setzet.ui.screens.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import net.estemon.studio.setzet.R
import net.estemon.studio.setzet.ui.navigation.NavigationRoutes
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(500) // simulate load time
        navController.navigate(NavigationRoutes.LOGIN_SCREEN) {
            popUpTo(NavigationRoutes.SPLASH_SCREEN) { inclusive = true } // delete this screen
        }
    }

    BaseLayout {
        Text(text = stringResource(id = R.string.label_splash_screen))
    }
}