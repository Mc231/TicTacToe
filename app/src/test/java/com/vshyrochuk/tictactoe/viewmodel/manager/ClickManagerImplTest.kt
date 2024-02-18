package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import com.vshyrochuk.tictactoe.model.TicTacToeState
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ClickManagerImplTest {

    @MockK
    var checkWinner: ICheckWinnerManager = mockk(relaxed = true)

    private var clickManager: ClickManagerImpl = ClickManagerImpl(checkWinner = checkWinner)

    @Test
    fun `onCellClicked updates state for a valid click`() {
        val initialState = TicTacToeState()
        val updateStateMock = mockk<(TicTacToeState) -> Unit>(relaxed = true)
        val triggerAIMoveMock = mockk<() -> Unit>(relaxed = true)

        every { checkWinner.checkWinner(any()) } returns null

        clickManager.onCellClicked(initialState, 0, 0, updateStateMock, triggerAIMoveMock)

        verify(exactly = 1) { updateStateMock(any()) }
    }

    @Test
    fun `onCellClicked does not update state for a click on non-empty cell`() {
        val board = List(3) { List(3) { CellState.X } }
        val initialState = TicTacToeState(board = board)
        val updateStateMock = mockk<(TicTacToeState) -> Unit>(relaxed = true)
        val triggerAIMoveMock = mockk<() -> Unit>(relaxed = true)

        clickManager.onCellClicked(initialState, 0, 0, updateStateMock, triggerAIMoveMock)

        verify(exactly = 0) { updateStateMock(any()) }
        verify(exactly = 0) { triggerAIMoveMock() }
    }

    @Test
    fun `onCellClicked triggers AI move for O player`() {
        val initialState = TicTacToeState(currentCellState = CellState.X)
        val updateStateMock = mockk<(TicTacToeState) -> Unit>(relaxed = true)
        val triggerAIMoveMock = mockk<() -> Unit>(relaxed = true)

        every { checkWinner.checkWinner(any()) } returns null

        // Simulate X player making a valid move
        clickManager.onCellClicked(initialState, 0, 0, updateStateMock, triggerAIMoveMock)

        // Verify AI move is triggered
        verify(exactly = 1) { triggerAIMoveMock() }
    }

    @Test
    fun `onCellClicked sets showDialog to true for a win condition`() {
        val initialState = TicTacToeState()
        val updateStateMock = mockk<(TicTacToeState) -> Unit>(relaxed = true)
        val triggerAIMoveMock = mockk<() -> Unit>(relaxed = true)

        // Simulate a winning condition after the click
        every { checkWinner.checkWinner(any()) } returns CellState.X

        clickManager.onCellClicked(initialState, 0, 0, updateStateMock, triggerAIMoveMock)

        // Verify updateState is called with showDialog set to true
        verify { updateStateMock(match { newState -> newState.showDialog }) }
    }

    @Test
    fun `onCellClicked sets showDialog to true for a draw condition`() {
        // Create a nearly full board with just one empty cell that will be clicked
        val almostFullBoard = List(3) { rowIndex ->
            List(3) { colIndex ->
                when {
                    rowIndex == 2 && colIndex == 2 -> CellState.EMPTY
                    rowIndex % 2 == 0 -> CellState.X
                    else -> CellState.O
                }
            }
        }
        val initialState = TicTacToeState(board = almostFullBoard, currentCellState = CellState.X)
        val updateStateMock = mockk<(TicTacToeState) -> Unit>(relaxed = true)
        val triggerAIMoveMock = mockk<() -> Unit>(relaxed = true)

        // No winner at this stage
        every { checkWinner.checkWinner(any()) } returns null

        // Click the last empty cell
        clickManager.onCellClicked(initialState, 2, 2, updateStateMock, triggerAIMoveMock)

        // Verify updateState is called with showDialog set to true due to the draw
        verify { updateStateMock(match { newState -> newState.showDialog }) }
    }
}