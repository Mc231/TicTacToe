package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.Player

/**
 * Defines the contract for checking the winner in a grid-based game like Tic-Tac-Toe.
 *
 * This interface provides a method to analyze the game board and determine if there is a winner. Implementations
 * should contain the logic to evaluate the board's state and identify a winning condition based on the game rules.
 */
interface ICheckWinnerManager {

    /**
     * Evaluates the game board to determine if there is a winner.
     *
     * This method should inspect the provided game board and return the winning player if a winning condition
     * is met according to the game rules. If there is no winner yet, it should return null.
     *
     * @param board A two-dimensional list representing the current state of the game board. Each inner list
     *              represents a row, and each element within that list represents a cell on the game board.
     *              Each cell is of type `CellState`, indicating the cell's occupancy.
     * @return The winning `Player` if a winning condition is met, or `null` if there is no winner yet.
     */
    fun checkWinner(board: List<List<CellState>>): Player?
}