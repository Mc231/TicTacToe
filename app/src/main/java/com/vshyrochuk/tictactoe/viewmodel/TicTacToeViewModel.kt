package com.vshyrochuk.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vshyrochuk.tictactoe.model.TicTacToeState
import com.vshyrochuk.tictactoe.viewmodel.manager.AIMoveManager
import com.vshyrochuk.tictactoe.viewmodel.manager.CheckWinnerImpl
import com.vshyrochuk.tictactoe.viewmodel.manager.ClickManagerImpl
import com.vshyrochuk.tictactoe.viewmodel.manager.ICheckWinnerManager
import com.vshyrochuk.tictactoe.viewmodel.manager.IClickManager
import com.vshyrochuk.tictactoe.viewmodel.manager.IMoveManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting

/**
 * ViewModel class for managing the state and logic of a Tic-Tac-Toe game.
 *
 * This ViewModel encapsulates the game logic, including managing player moves, checking for a winner,
 * and handling AI moves. It uses a [MutableStateFlow] to hold the current game state, allowing the UI to observe
 * and react to changes in the game state.
 *
 * @property checkManager An instance of [ICheckWinnerManager] used to check for a winner after each move.
 * @property clickManager An instance of [IClickManager] used to handle cell click events and update the game state.
 * @property moveManager An instance of [IMoveManager] used to manage AI moves.
 */
class TicTacToeViewModel(
    private val checkManager: ICheckWinnerManager = CheckWinnerImpl,
    private val clickManager: IClickManager = ClickManagerImpl(checkManager),
    private val moveManager: IMoveManager = AIMoveManager
) : ViewModel() {

    private val _state = MutableStateFlow(TicTacToeState())

    /**
     * A [StateFlow] that represents the current state of the Tic-Tac-Toe game.
     * It is exposed as a read-only [StateFlow] to ensure encapsulation of the mutable state.
     */
    val state: StateFlow<TicTacToeState> get() = _state

    init {
        resetGame()
    }

    /**
     * Handles a cell click event initiated by the player.
     *
     * This function delegates the handling of a cell click event to the [clickManager], which updates the game state
     * accordingly. If the game includes an AI component, it may also trigger an AI move in response.
     *
     * @param rowIndex The row index of the clicked cell.
     * @param columnIndex The column index of the clicked cell.
     */
    fun onCellClicked(rowIndex: Int, columnIndex: Int) {
        clickManager.onCellClicked(
            state = _state.value,
            rowIndex = rowIndex,
            columnIndex = columnIndex,
            updateState = { newState -> _state.value = newState },
            triggerAIMove = { aiMakeMove() }
        )
    }

    /**
     * Initiates an AI move.
     *
     * This function is called to trigger an AI move with a slight delay, simulating thinking time. The AI move
     * is handled by the [moveManager], which updates the game state with the chosen move.
     */
    @VisibleForTesting
    fun aiMakeMove() {
        viewModelScope.launch {
            delay(MOVE_DELAY)
            moveManager.makeMove(_state.value.board) { row, col ->
                onCellClicked(row, col)
            }
        }
    }

    /**
     * Resets the game to its initial state.
     *
     * This function is used to start a new game by resetting the game state to its default values.
     */
    fun resetGame() {
        _state.value = TicTacToeState()
    }

    companion object {
        /**
         * The delay in milliseconds before the AI makes a move, simulating thinking time.
         */
        private const val MOVE_DELAY: Long = 500
    }
}

