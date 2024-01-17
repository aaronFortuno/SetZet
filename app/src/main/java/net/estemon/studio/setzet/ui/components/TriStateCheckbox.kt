package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class CheckState { Unchecked, Correct, Incorrect }

@Composable
fun TriStateCheckbox(
    state: CheckState,
    onStateChange: (CheckState) -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = when (state) {
            CheckState.Unchecked -> Icons.Default.CheckBoxOutlineBlank
            CheckState.Correct -> Icons.Default.CheckBox
            CheckState.Incorrect -> Icons.Default.IndeterminateCheckBox
        },
        contentDescription = null,
        tint = when (state) {
            CheckState.Unchecked -> Color.Gray
            CheckState.Correct -> Color.Green
            CheckState.Incorrect -> Color.Red
        },
        modifier = modifier
            .clickable {
                onStateChange(
                    when (state) {
                        CheckState.Unchecked -> CheckState.Correct
                        CheckState.Correct -> CheckState.Incorrect
                        CheckState.Incorrect -> CheckState.Unchecked
                    }
                )
            }
            .size(40.dp, 40.dp)
    )
}