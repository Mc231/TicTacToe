package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vshyrochuk.tictactoe.model.CellState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameCellTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gameCellDisplaysX() {
        composeTestRule.setContent {
            GameCell(cell = CellState.X, rowIndex = 0, columnIndex = 0) { _, _ -> }
        }

        composeTestRule.onNodeWithTag("Cell: 0,0").assertTextEquals("X")
    }

    @Test
    fun gameCellDisplaysO() {
        composeTestRule.setContent {
            GameCell(cell = CellState.O, rowIndex = 0, columnIndex = 0) { _, _ -> }
        }

        composeTestRule.onNodeWithTag("Cell: 0,0").assertTextEquals("O")
    }

    @Test
    fun gameCellEmptyDoesNotDisplayText() {
        composeTestRule.setContent {
            GameCell(cell = CellState.EMPTY, rowIndex = 0, columnIndex = 0) { _, _ -> }
        }

        composeTestRule.onNodeWithTag("Cell: 0,0").assertExists("Cell must exist")
    }

    @Test
    fun gameCellClickTriggersListener() {
        var clickedRow = -1
        var clickedColumn = -1

        composeTestRule.setContent {
            GameCell(cell = CellState.EMPTY, rowIndex = 1, columnIndex = 1) { rowIndex, columnIndex ->
                clickedRow = rowIndex
                clickedColumn = columnIndex
            }
        }

        composeTestRule.onNodeWithTag("Cell: 1,1").performClick()

        assert(clickedRow == 1 && clickedColumn == 1) { "Cell was not clicked correctly" }
    }
}