package com.vshyrochuk.tictactoe.viewmodel

import com.vshyrochuk.tictactoe.model.TicTacToeState
import com.vshyrochuk.tictactoe.viewmodel.manager.ICheckWinnerManager
import com.vshyrochuk.tictactoe.viewmodel.manager.IClickManager
import com.vshyrochuk.tictactoe.viewmodel.manager.IMoveManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class TicTacToeViewModelTest {

    @MockK
    private lateinit var checkManager: ICheckWinnerManager

    @MockK
    private lateinit var clickManager: IClickManager

    @MockK
    private lateinit var moveManager: IMoveManager

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        // Initialize mocks
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())

        // Mock behaviors
        every { clickManager.onCellClicked(any(), any(), any(), any(), any()) } answers {
            val newState = TicTacToeState() // Simulate a new state
            val updateStateLambda = arg<(TicTacToeState) -> Unit>(3)
            updateStateLambda(newState)
        }


        coEvery { moveManager.makeMove(any(), any()) } answers {
            val callback = arg<(Int, Int) -> Unit>(1)
            callback(1, 1) // Simulate AI making a move at position (1, 1)
        }

        viewModel = TicTacToeViewModel(checkManager, clickManager, moveManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test ViewModel initialization`() {
        // Verify initial state
        assert(viewModel.state.value == TicTacToeState())
    }

    @Test
    fun `test onCellClicked updates state correctly`() {
        // Perform a cell click
        viewModel.onCellClicked(0, 0)

        // Verify that clickManager was called with the correct parameters
        verify { clickManager.onCellClicked(any(), eq(0), eq(0), any(), any()) }
    }

    @Test
    fun `test aiMakeMove triggers AI move`() = runTest {
        // Trigger an AI move
        viewModel.aiMakeMove()

        // Advance time until all coroutines are idle
        advanceUntilIdle()

        // Verify that clickManager is called as a result of AI move
        coVerify { clickManager.onCellClicked(any(), eq(1), eq(1), any(), any()) }
    }

    @Test
    fun `test resetGame resets the game state`() {
        // Change the state to simulate a game in progress
        viewModel.onCellClicked(0, 0)

        // Reset the game
        viewModel.resetGame()

        // Verify the state is reset to the initial state
        assert(viewModel.state.value == TicTacToeState())
    }
}