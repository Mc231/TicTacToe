package com.vshyrochuk.tictactoe.ui.composable

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vshyrochuk.tictactoe.R
import com.vshyrochuk.tictactoe.model.Player
import com.vshyrochuk.tictactoe.model.description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameOverDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun gameOverDialog_ShowsWinner() {
        val winner = Player.X


        composeTestRule.setContent {
            GameOverDialog(winner = winner, onDismiss = {})
        }

        composeTestRule.onNodeWithText(
            context.getString(
                R.string.winner,
                winner.description
            )
        ).assertExists()
    }

    @Test
    fun gameOverDialog_ShowsDraw() {
        composeTestRule.setContent {
            GameOverDialog(winner = null, onDismiss = {})
        }

        composeTestRule.onNodeWithText(context.getString(R.string.draw))
            .assertExists()
    }

    @Test
    fun gameOverDialog_DismissWorks() {
        var dismissed = false

        composeTestRule.setContent {
            GameOverDialog(winner = null, onDismiss = { dismissed = true })
        }

        composeTestRule.onNodeWithText(context.getString(R.string.restart))
            .performClick()

        assert(dismissed) { "Dialog was not dismissed" }
    }
}
