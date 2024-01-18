package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class CheckState { Unable, Unchecked, Correct, Incorrect }

@Composable
fun TriStateCheckbox(
    state: CheckState,
    onStateChange: (CheckState) -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = when (state) {
            CheckState.Unable -> Icons.Default.MoreVert
            CheckState.Unchecked -> Icons.Default.KeyboardArrowRight
            CheckState.Correct -> Icons.Default.CheckCircle
            CheckState.Incorrect -> Icons.Default.Warning
        },
        contentDescription = null,
        tint = when (state) {
            CheckState.Unable -> Color.Transparent
            CheckState.Unchecked -> Color.Gray
            CheckState.Correct -> Color.Green
            CheckState.Incorrect -> Color.Red
        },
        modifier = modifier
            .then(
                if (state != CheckState.Unable) {
                    Modifier.clickable {
                        onStateChange(
                            when (state) {
                                CheckState.Unchecked -> CheckState.Correct
                                CheckState.Correct -> CheckState.Incorrect
                                CheckState.Incorrect -> CheckState.Unchecked
                                else -> state
                            }
                        )
                    }
                } else Modifier
            )
            .size(40.dp, 40.dp)
    )
}