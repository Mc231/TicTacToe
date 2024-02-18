package com.vshyrochuk.tictactoe.model

/**
 * Type alias for `CellState` to represent a player in the game.
 * This alias allows the use of `CellState` instances to directly represent players,
 * where each player is associated with a particular cell state (X or O).
 */
typealias Player = CellState

/**
 * Enum representing the possible states of a cell in a tic-tac-toe (or similar) game board.
 * Each cell on the game board can be in one of three states: occupied by player X, occupied by player O, or empty.
 *
 * @property X Represents a cell occupied by player X.
 * @property O Represents a cell occupied by player O.
 * @property EMPTY Represents an unoccupied (empty) cell.
 */
enum class CellState {
    X, O, EMPTY;

    /**
     * Toggles the state of the cell between X and O. If the current state is X, it will become O, and vice versa.
     * The EMPTY state will toggle to X.
     *
     * @return The toggled `CellState`.
     */
    fun toggle(): CellState = if (this == X) O else X
}

/**
 * Extension property to get a human-readable description of the `CellState`.
 * This property can be used to easily convert the state of a cell to a string representation.
 *
 * @return A `String` representation of the `CellState`:
 *         "X" for [CellState.X], "O" for [CellState.O], and an empty string for [CellState.EMPTY].
 */
val CellState.description: String
    get() = when (this) {
        CellState.X -> "X"
        CellState.O -> "O"
        CellState.EMPTY -> ""
    }