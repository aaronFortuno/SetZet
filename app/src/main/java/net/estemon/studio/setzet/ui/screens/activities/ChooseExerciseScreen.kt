package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RemoveCircleOutline
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

@Composable
fun ChooseExerciseScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.label_choose_activity_screen))

        Button(
            onClick = { navController.navigate(NavigationRoutes.CHOOSE_EXERCISE_SCREEN) },
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_addition))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = null
            )
        }

        Button(
            onClick = { navController.navigate(NavigationRoutes.CHOOSE_EXERCISE_SCREEN) },
            enabled = false,
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_subtract))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.RemoveCircleOutline,
                contentDescription = null
            )
        }

        Button(
            onClick = { navController.navigate(NavigationRoutes.CHOOSE_EXERCISE_SCREEN) },
            enabled = false,
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_multiply))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }

        Button(
            onClick = { navController.navigate(NavigationRoutes.CHOOSE_EXERCISE_SCREEN) },
            enabled = false,
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_division))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null
            )
        }
    }
}