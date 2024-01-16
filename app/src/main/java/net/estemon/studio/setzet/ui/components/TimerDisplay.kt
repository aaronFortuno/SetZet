package net.estemon.studio.setzet.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

@Composable
fun TimerDisplay(
    timeLeft: State<Int>
) {
    val minutes = timeLeft.value / 60
    val seconds = timeLeft.value % 60
    val alertColor = if (timeLeft.value <= 30)
        Color.Red else MaterialTheme.colorScheme.onSurface

    Text(
        text = "%02d:%02d".format(minutes, seconds),
        color = alertColor,
        style = MaterialTheme.typography.displayLarge
    )
}