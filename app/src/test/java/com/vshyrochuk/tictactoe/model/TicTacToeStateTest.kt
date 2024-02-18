package com.vshyrochuk.tictactoe.model

import org.junit.Assert.assertEquals
import org.junit.Test

class TicTacToeStateTest {

    @Test
    fun `test default initialization`() {
        val defaultState = TicTacToeState()

        // Test default board initialization
        assertEquals(3, defaultState.board.size)
        defaultState.board.forEach { row ->
            assertEquals(3, row.size)
            row.forEach { cell ->
                assertEquals(CellState.EMPTY, cell)
            }
        }

        // Test default currentCellState
        assertEquals(CellState.X, defaultState.currentCellState)

        // Test default winner
        assertEquals(null, defaultState.winner)

        // Test default showDialog
        assertEquals(false, defaultState.showDialog)
    }

    @Test
    fun `test custom initialization`() {
        val customBoard = List(3) { List(3) { CellState.O } }
        val customCurrentCellState = CellState.O
        val customWinner = CellState.O
        val customShowDialog = true

        val customState = TicTacToeState(
            board = customBoard,
            currentCellState = customCurrentCellState,
            winner = customWinner,
            showDialog = customShowDialog
        )

        // Test custom board
        assertEquals(customBoard, customState.board)

        // Test custom currentCellState
        assertEquals(customCurrentCellState, customState.currentCellState)

        // Test custom winner
        assertEquals(customWinner, customState.winner)

        // Test custom showDialog
        assertEquals(customShowDialog, customState.showDialog)
    }
}
