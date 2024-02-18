package com.vshyrochuk.tictactoe.model

/**
 * Represents the state of a Tic-Tac-Toe game at a given moment.
 *
 * This data class encapsulates all the necessary information to describe a Tic-Tac-Toe game's current state,
 * including the game board layout, the player whose turn it is, the winner (if any), and whether a dialog should be shown.
 *
 * @property board A two-dimensional list representing the game board, where each inner list represents a row,
 *                 and each element within that list represents a cell on the game board. Each cell is of type `CellState`,
 *                 which can be X, O, or EMPTY, indicating the cell's occupancy.
 * @property currentCellState The `CellState` representing the current player's turn. This is either X or O, indicating
 *                            which player's turn it is to place their mark on the board.
 * @property winner An optional `CellState` indicating the winner of the game, if there is one. This can be X, O, or `null`
 *                  if the game has not yet been won. A `null` value indicates that the game is either ongoing or has ended in a draw.
 * @property showDialog A boolean value indicating whether a dialog should be shown, for example, to announce the game's outcome.
 *                      This could be used to show a dialog when the game ends, either by a win or a draw.
 */
data class TicTacToeState(
    val board: List<List<CellState>> = List(3) { List(3) { CellState.EMPTY } },
    val currentCellState: CellState = CellState.X,
    val winner: CellState? = null,
    val showDialog: Boolean = false
)
