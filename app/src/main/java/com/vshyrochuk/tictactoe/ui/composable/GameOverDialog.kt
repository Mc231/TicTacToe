package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vshyrochuk.tictactoe.R
import com.vshyrochuk.tictactoe.model.Player
import com.vshyrochuk.tictactoe.model.description
import com.vshyrochuk.tictactoe.ui.theme.TicTacToeTheme

/**
 * Displays a dialog indicating the game is over, showing the result of the game.
 * The dialog informs the user of the winner or if the game ended in a draw, and provides
 * an option to restart the game.
 *
 * This composable is designed to be called when the game reaches an end state, and it requires
 * the caller to provide the game's outcome and a mechanism to reset the game state.
 *
 * @param winner The [Player] who won the game, or `null` if the game ended in a draw. The `Player` class
 *               is expected to have a `description` property that describes the player in a user-friendly way.
 * @param onDismiss A lambda function that is called when the dialog is dismissed, typically used to reset
 *                  the game state and start a new game.
 */
@Composable
fun GameOverDialog(winner: Player?, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.game_over)) },
        text = {
            Text(
                if (winner != null) stringResource(
                    id = R.string.winner,
                    winner.description
                ) else stringResource(id = R.string.draw)
            )
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(id = R.string.restart))
            }
        }
    )
}

@Preview
@Composable
private fun GameOverDialogPreviewWinnerX() {
    TicTacToeTheme {
        GameOverDialog(winner = Player.X) { }
    }
}

@Preview
@Composable
private fun GameOverDialogPreviewWinnerO() {
    TicTacToeTheme {
        GameOverDialog(winner = Player.O) { }
    }
}

@Preview
@Composable
private fun GameOverDialogPreviewDraw() {
    TicTacToeTheme {
        GameOverDialog(winner = null) { }
    }
}