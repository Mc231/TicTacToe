package com.vshyrochuk.tictactoe.viewmodel.manager

import com.vshyrochuk.tictactoe.model.CellState
import kotlin.random.Random

object AIMoveManager : IMoveManager {
    override fun makeMove(board: List<List<CellState>>, onMoveMade: (Int, Int) -> Unit) {
        val emptyCells = mutableListOf<Pair<Int, Int>>()

        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cell ->
                if (cell == CellState.EMPTY) {
                    emptyCells.add(Pair(rowIndex, columnIndex))
                }
            }
        }

        if (emptyCells.isNotEmpty()) {
            val (row, col) = emptyCells[Random.nextInt(emptyCells.size)]
            onMoveMade(row, col)
        }
    }
}