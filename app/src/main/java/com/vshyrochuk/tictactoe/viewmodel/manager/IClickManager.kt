package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.TicTacToeState

/**
 * Defines the contract for handling cell click events in a game like Tic-Tac-Toe.
 *
 * This interface provides a method to respond to clicks on individual cells of the game board. Implementations
 * should include logic to update the game state based on the cell that was clicked, determine if the game has
 * reached a conclusion (win or draw), and potentially trigger an AI move if applicable.
 */
interface IClickManager {

    /**
     * Handles a click event on a specific cell of the game board.
     *
     * This method is called when a player clicks on a cell within the game board. It should handle the logic
     * for updating the game state based on the clicked cell, including checking for and processing a winning move
     * or a draw. If the game is set up to play against an AI, and it is the AI's turn after the player's move,
     * this method should also trigger the AI move.
     *
     * @param state The current state of the game, including the game board, the current player, and any other relevant state.
     * @param rowIndex The row index of the clicked cell.
     * @param columnIndex The column index of the clicked cell.
     * @param updateState A callback function that accepts an updated [TicTacToeState] and applies it to the game.
     *                    This function is used to update the game state after the cell click has been processed.
     * @param triggerAIMove A callback function that triggers an AI move. This should be called if the game includes an AI
     *                      opponent and it is the AI's turn after the player's move.
     */
    fun onCellClicked(
        state: TicTacToeState,
        rowIndex: Int,
        columnIndex: Int,
        updateState: (TicTacToeState) -> Unit,
        triggerAIMove: () -> Unit
    )
}
