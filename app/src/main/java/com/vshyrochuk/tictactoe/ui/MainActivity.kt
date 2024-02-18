package com.vshyrochuk.tictactoe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vshyrochuk.tictactoe.ui.composable.TicTacToeGame
import com.vshyrochuk.tictactoe.viewmodel.TicTacToeViewModel
import com.vshyrochuk.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TicTacToeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
    }

    private fun setContent() {
        setContent {
            TicTacToeTheme {
                val state by viewModel.state.collectAsState()
                TicTacToeGame(
                    state = state,
                    resetGame = viewModel::resetGame,
                    onCellClicked = viewModel::onCellClicked
                )
            }
        }
    }
}

