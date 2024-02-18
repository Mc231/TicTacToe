package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CheckWinnerImplTest {

    @Test
    fun `checkWinner detects horizontal wins`() {
        for (row in 0..2) {
            val board = List(3) { rowIndex ->
                if (rowIndex == row) listOf(CellState.X, CellState.X, CellState.X)
                else listOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)
            }

            val winner = CheckWinnerImpl.checkWinner(board)
            assertEquals(CellState.X, winner)
        }
    }

    @Test
    fun `checkWinner detects vertical wins`() {
        for (col in 0..2) {
            val board = List(3) { rowIndex ->
                List(3) { colIndex ->
                    if (colIndex == col) CellState.O else CellState.EMPTY
                }
            }

            val winner = CheckWinnerImpl.checkWinner(board)
            assertEquals(CellState.O, winner)
        }
    }

    @Test
    fun `checkWinner detects diagonal wins`() {
        val diagonal1 = List(3) { index -> List(3) { if (it == index) CellState.X else CellState.EMPTY } }
        val diagonal2 = List(3) { index -> List(3) { if (it == 2 - index) CellState.O else CellState.EMPTY } }

        val winnerDiagonal1 = CheckWinnerImpl.checkWinner(diagonal1)
        val winnerDiagonal2 = CheckWinnerImpl.checkWinner(diagonal2)

        assertEquals(CellState.X, winnerDiagonal1)
        assertEquals(CellState.O, winnerDiagonal2)
    }

    @Test
    fun `checkWinner returns null when there is no winner`() {
        val noWinnerBoard = listOf(
            listOf(CellState.X, CellState.O, CellState.X),
            listOf(CellState.O, CellState.O, CellState.X),
            listOf(CellState.X, CellState.X, CellState.O)
        )

        val winner = CheckWinnerImpl.checkWinner(noWinnerBoard)
        assertEquals(null, winner)
    }
}
