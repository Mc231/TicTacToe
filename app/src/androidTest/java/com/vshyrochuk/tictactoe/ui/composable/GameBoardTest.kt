package com.vshyrochuk.tictactoe.ui.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vshyrochuk.tictactoe.model.CellState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameBoardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gameBoardFilledCorrectly() {
        val size = 3
        val initialBoard = List(size) { List(size) { CellState.EMPTY } }

        composeTestRule.setContent { GameBoard(board = initialBoard) { _, _ -> } }

        composeTestRule.waitForIdle()

        for (rowIndex in 0 until size) {
            for (columnIndex in 0 until size) {
                val cellTag = "Cell: $rowIndex,$columnIndex"
                composeTestRule.onNodeWithTag(cellTag).assertExists("Cell at position ($rowIndex, $columnIndex) should exist")
            }
        }
    }
}