package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AIMoveManagerTest {

    private var onMoveMadeMock: (Int, Int) -> Unit = mockk(relaxed = true)

    @Test
    fun `makeMove calls onMoveMade with valid indices when there are empty cells`() {
        val board = listOf(
            listOf(CellState.X, CellState.O, CellState.EMPTY),
            listOf(CellState.EMPTY, CellState.X, CellState.O),
            listOf(CellState.O, CellState.X, CellState.EMPTY)
        )

        AIMoveManager.makeMove(board, onMoveMadeMock)

        verify(atLeast = 1) { onMoveMadeMock(any(), any()) }
    }

    @Test
    fun `makeMove does not call onMoveMade when there are no empty cells`() {
        val board = listOf(
            listOf(CellState.X, CellState.O, CellState.X),
            listOf(CellState.X, CellState.X, CellState.O),
            listOf(CellState.O, CellState.X, CellState.O)
        )

        AIMoveManager.makeMove(board, onMoveMadeMock)
        verify(exactly = 0) { onMoveMadeMock(any(), any()) }
    }
}