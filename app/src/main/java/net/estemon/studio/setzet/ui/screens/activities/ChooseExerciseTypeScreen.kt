package net.estemon.studio.setzet.ui.screens.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
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
import net.estemon.studio.setzet.models.enums.ExerciseType
import net.estemon.studio.setzet.ui.navigation.chooseDifficultyRoute

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
            onClick = { navController.navigate(chooseDifficultyRoute(ExerciseType.ADDITION)) },
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_addition))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }

        Button(
            onClick = { navController.navigate(chooseDifficultyRoute(ExerciseType.SUBTRACTION)) },
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_new_subtract))
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }

        Button(
            onClick = { navController.navigate(chooseDifficultyRoute(ExerciseType.MULTIPLICATION)) },
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
            onClick = { navController.navigate(chooseDifficultyRoute(ExerciseType.DIVISION)) },
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