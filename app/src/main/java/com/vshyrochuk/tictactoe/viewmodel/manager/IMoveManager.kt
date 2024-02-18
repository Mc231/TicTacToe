package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState

/**
 * Defines the contract for managing moves within a game that involves a grid-like board, such as Tic-Tac-Toe.
 *
 * This interface provides a method to make a move on the game board, given the current state of the board.
 * Implementations of this interface should encapsulate the logic for determining the next move based on the current
 * state of the game board and any applicable game rules or AI algorithms.
 *
 * @see CellState for the possible states of each cell in the board.
 */
interface IMoveManager {

    /**
     * Initiates and executes a move on the given game board.
     *
     * Implementations of this method should calculate the position (row and column) for the next move based on the
     * current state of the board and any other relevant criteria (such as the current player or AI strategy).
     * Once the move position is determined, the `onMoveMade` callback is invoked with the row and column indices
     * of the chosen move.
     *
     * @param board A two-dimensional list representing the current state of the game board, where each inner list
     *              represents a row, and each element within that list represents a cell on the game board.
     *              Each cell is of type `CellState`, indicating the cell's occupancy.
     * @param onMoveMade A callback function to be invoked when the move has been determined. It accepts two parameters:
     *                   the row and column indices of the cell where the move is made.
     */
    fun makeMove(board: List<List<CellState>>, onMoveMade: (Int, Int) -> Unit)
}
