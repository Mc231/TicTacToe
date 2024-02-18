package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vshyrochuk.tictactoe.R
import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.TicTacToeState
import com.vshyrochuk.tictactoe.ui.theme.TicTacToeTheme

/**
 * Displays the Tic Tac Toe game interface, including the game board and a top app bar.
 * This composable also handles the display of a dialog when the game is over.
 *
 * The game state, including the board and the game over condition, is controlled via the [state] parameter.
 * The [resetGame] function is called to reset the game to its initial state, and [onCellClicked] defines
 * the action to be taken when a cell on the game board is clicked.
 *
 * @param state The current state of the Tic Tac Toe game, including the board configuration and game status.
 * @param resetGame A function to be invoked when the game needs to be reset. This is typically called after the game ends.
 * @param onCellClicked A function that takes the row and column indices of the clicked cell and performs the corresponding action.
 * @OptIn Marks this composable as using experimental Material 3 API features, which might change in the future.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeGame(
    state: TicTacToeState,
    resetGame: () -> Unit,
    onCellClicked: (Int, Int) -> Unit,
) {
    if (state.showDialog) {
        GameOverDialog(winner = state.winner, onDismiss = resetGame)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.app_title)) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            GameBoard(board = state.board, onCellClicked = onCellClicked)
        }
    }
}

@Preview
@Composable
private fun TicTacToeGamePreview() {
    val mockBoard = List(3) { rowIndex ->
    List(3) { columnIndex ->
        when ((rowIndex + columnIndex) % 2) {
            0 -> CellState.X
            else -> CellState.O
        }
    }
}
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(board = mockBoard),
            resetGame = { },
            onCellClicked = { _, _ -> })
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverDrawPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true),
            resetGame = { },
            onCellClicked = { _, _ -> })
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverXWinPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true, winner = CellState.X),
            resetGame = { },
            onCellClicked = { _, _ -> })
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverOWinPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true, winner = CellState.O),
            resetGame = { },
            onCellClicked = { _, _ -> })
    }
}
