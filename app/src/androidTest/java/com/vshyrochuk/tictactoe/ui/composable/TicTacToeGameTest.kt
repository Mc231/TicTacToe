package com.vshyrochuk.tictactoe.ui.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vshyrochuk.tictactoe.model.Player
import com.vshyrochuk.tictactoe.model.TicTacToeState
import com.vshyrochuk.tictactoe.R
import com.vshyrochuk.tictactoe.model.description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TicTacToeGameTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun gameOverDialog_ShowsCorrectly_WhenGameEnds() {
        val gameState = TicTacToeState(showDialog = true, winner = Player.X)

        composeTestRule.setContent {
            TicTacToeGame(state = gameState, resetGame = {  }) { _, _ -> }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.game_over)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.winner, Player.X.description)).assertIsDisplayed()
    }

    @Test
    fun gameBoard_CellClick_IsHandled() {
        var clickedRow = -1
        var clickedColumn = -1

        val gameState = TicTacToeState()

        composeTestRule.setContent {
            TicTacToeGame(state = gameState, resetGame = { }) { rowIndex, columnIndex ->
                clickedRow = rowIndex
                clickedColumn = columnIndex
            }
        }

        composeTestRule.onNodeWithTag("Cell: 0,0").performClick()
        assert(clickedRow == 0 && clickedColumn == 0) { "Cell click was not handled correctly" }
    }
}