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

@Composable
fun LoginScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000) // simulate load time
        navController.navigate(NavigationRoutes.HOME_SCREEN) {
            popUpTo(NavigationRoutes.LOGIN_SCREEN) { inclusive = true } // delete this screen
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.label_login_screen))
    }
}