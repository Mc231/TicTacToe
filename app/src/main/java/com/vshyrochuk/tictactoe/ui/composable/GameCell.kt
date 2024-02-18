package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.description
import com.vshyrochuk.tictactoe.ui.theme.Sizes
import com.vshyrochuk.tictactoe.ui.theme.TicTacToeTheme

private const val CELLS_PER_ROW = 3

/**
 * Represents a single cell within the Tic Tac Toe game board. This composable displays the cell's
 * state and handles cell clicks. The cell adjusts its size based on the screen dimensions to maintain
 * a consistent appearance across different devices.
 *
 * The cell's content (X, O, or empty) is centered and animated to appear or disappear when the cell's
 * state changes. The animation includes fading and scaling effects for a more dynamic user experience.
 *
 * @param cell The current state of this cell, represented by a [CellState] value.
 * @param rowIndex The row index of this cell within the game board grid.
 * @param columnIndex The column index of this cell within the game board grid.
 * @param onCellClicked A callback function that is invoked when this cell is clicked. The row and
 *        column indices of the clicked cell are provided as parameters to the callback.
 */
@Composable
fun GameCell(
    cell: CellState,
    rowIndex: Int,
    columnIndex: Int,
    onCellClicked: (Int, Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val cellSize = minOf(screenWidth, screenHeight) / CELLS_PER_ROW
    val fontSize = cellSize / CELLS_PER_ROW

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .testTag("Cell: $rowIndex,$columnIndex")
            .size(Sizes.CELL_SIZE)
            .padding(Sizes.CELL_PADDING)
            .background(Color.White)
            .clickable { onCellClicked(rowIndex, columnIndex) }
    ) {
        AnimatedVisibility(
            visible = cell != CellState.EMPTY,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Text(
                text = cell.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = fontSize.value.sp),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewX() {
    TicTacToeTheme {
        GameCell(cell = CellState.X, 0, 0) { _, _ -> }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewO() {
    TicTacToeTheme {
        GameCell(cell = CellState.O, 0, 0) { _, _ -> }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewEmpty() {
    TicTacToeTheme {
        GameCell(cell = CellState.EMPTY, 0, 0) { _, _ -> }
    }
}