package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.ui.theme.Sizes
import com.vshyrochuk.tictactoe.ui.theme.TicTacToeTheme

/**
 * Displays a game board as a grid of cells, where each cell's visual representation is determined by its state.
 *
 * The game board is constructed as a two-dimensional grid using a `Column` of `Row`s, where each `Row` represents
 * a horizontal line of cells on the board. Each cell is represented by a `GameCell` composable, which is invoked
 * with the cell's current state, its position on the board (row and column indices), and a callback function to be
 * called when the cell is clicked.
 *
 * @param board A two-dimensional list representing the game board, where each inner list represents a row of cells,
 * and each cell is represented by its state of type `CellState`.
 * @param onCellClicked A lambda function that is invoked when a cell is clicked. It receives the row and column
 * indices of the clicked cell as its parameters.
 */
@Composable
fun GameBoard(board: List<List<CellState>>, onCellClicked: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Black)
            .padding(Sizes.CELL_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, cell ->
                    GameCell(cell, rowIndex, columnIndex, onCellClicked)
                }
            }
        }
    }
}

@Preview
@Composable
private fun EmptyGameBoardPreview() {
    TicTacToeTheme {
        val mockBoard = List(3) { List(3) { CellState.EMPTY} }
        GameBoard(board = mockBoard) { _, _ -> }
    }
}

@Preview
@Composable
private fun FilledGameBoardPreview() {
    TicTacToeTheme {
        val mockBoard = List(3) { rowIndex ->
            List(3) { columnIndex ->
                when ((rowIndex + columnIndex) % 2) {
                    0 -> CellState.X
                    else -> CellState.O
                }
            }
        }
        GameBoard(board = mockBoard) { _, _ -> }
    }
}