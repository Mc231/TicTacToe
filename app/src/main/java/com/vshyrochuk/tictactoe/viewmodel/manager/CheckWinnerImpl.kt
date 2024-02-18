package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.Player

object CheckWinnerImpl: ICheckWinnerManager {
    override fun checkWinner(board: List<List<CellState>>): Player? {
        // Check horizontal lines
        for (row in board) {
            if (row[0] != CellState.EMPTY && row[0] == row[1] && row[0] == row[2]) {
                return row[0]
            }
        }

        // Check vertical lines
        for (col in 0..2) {
            if (board[0][col] != CellState.EMPTY && board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
                return board[0][col]
            }
        }

        // Check diagonals
        if (board[0][0] != CellState.EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != CellState.EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2]
        }

        // No winner
        return null
    }
}

