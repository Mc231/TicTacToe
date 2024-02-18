package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.TicTacToeState

class ClickManagerImpl(private val checkWinner: ICheckWinnerManager) :
    IClickManager {
    override fun onCellClicked(
        state: TicTacToeState,
        rowIndex: Int,
        columnIndex: Int,
        updateState: (TicTacToeState) -> Unit,
        triggerAIMove: () -> Unit
    ) {
        if (state.board[rowIndex][columnIndex] == CellState.EMPTY && state.winner == null) {
            val newBoard = state.board.mapIndexed { rIndex, row ->
                row.mapIndexed { cIndex, cell ->
                    if (rIndex == rowIndex && cIndex == columnIndex && cell == CellState.EMPTY) {
                        state.currentCellState
                    } else {
                        cell
                    }
                }
            }

            val newCurrentPlayer = state.currentCellState.toggle()
            val newWinner = checkWinner.checkWinner(newBoard)
            val newState = state.copy(
                board = newBoard,
                currentCellState = newCurrentPlayer,
                winner = newWinner
            )

            updateState(newState)

            if (newState.currentCellState == CellState.O && newWinner == null) {
                triggerAIMove()
            }

            val isDraw = newState.board.all { row -> row.all { it != CellState.EMPTY } }
            if (newState.winner != null || isDraw) {
                updateState(newState.copy(showDialog = true))
            }
        }
    }
}