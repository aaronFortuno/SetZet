package net.estemon.studio.setzet.ui.screens.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.estemon.studio.setzet.R
import net.estemon.studio.setzet.ui.navigation.NavigationRoutes
import net.estemon.studio.setzet.ui.screens.common.BaseLayout

@Composable
fun HomeScreen(navController: NavController) {

    BaseLayout {
        Text(text = "HomeScreen")
        Button(onClick = { navController.navigate(NavigationRoutes.CHOOSE_EXERCISE_SCREEN) }) {
            Text(text = stringResource(id = R.string.btn_choose_activity))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}