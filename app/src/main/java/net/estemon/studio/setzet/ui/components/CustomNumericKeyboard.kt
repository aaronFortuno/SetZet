package net.estemon.studio.setzet.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import net.estemon.studio.setzet.ui.screens.common.btnPadding
import net.estemon.studio.setzet.ui.screens.common.screenPadding

@Composable
fun CustomNumericKeyboard(
    onNumberClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val numbers = (1..9) + 0
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    val btnWidthPx = (screenWidthDp - (screenPadding * 2) - (btnPadding * (numbers.size - 1))) / numbers.size

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            items(numbers) { number ->
                Button(
                    onClick = { onNumberClick(number) },
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(btnPadding.dp)
                        .width(btnWidthPx.dp)
                        .defaultMinSize(
                            minWidth = 0.dp,
                            minHeight = 0.dp
                        )
                ) {
                    Text(
                        text = number.toString(),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Absolute.Right,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Button(
                onClick = { onDeleteClick() },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(btnPadding.dp)
                    .width(((btnWidthPx * 2) + btnPadding).dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }

            Button(
                onClick = { onNextClick() },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(btnPadding.dp)
                    .width(((btnWidthPx * 2) + btnPadding).dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}