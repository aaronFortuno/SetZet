package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomNumericKeyboard(
    onNumberClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val numbers = (1..9) + 0

    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            for (number in numbers) {
                Button(onClick = { onNumberClick(number) }) {
                    Text(text = number.toString())
                }
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { onDeleteClick() }) {
                Icon(imageVector = Icons.Default.Backspace, contentDescription = null)
            }

            Button(onClick = { onNextClick() }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}