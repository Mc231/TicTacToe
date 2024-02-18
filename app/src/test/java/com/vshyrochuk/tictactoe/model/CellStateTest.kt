package com.vshyrochuk.tictactoe.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CellStateTest {

    @Test
    fun `test toggle from X to O`() {
        val currentState = CellState.X
        val toggledState = currentState.toggle()
        assertEquals(CellState.O, toggledState)
    }

    @Test
    fun `test toggle from O to X`() {
        val currentState = CellState.O
        val toggledState = currentState.toggle()
        assertEquals(CellState.X, toggledState)
    }

    @Test
    fun `test toggle from EMPTY to X`() {
        val currentState = CellState.EMPTY
        val toggledState = currentState.toggle()
        assertEquals(CellState.X, toggledState)
    }

    @Test
    fun `test description for X`() {
        val state = CellState.X
        assertEquals("X", state.description)
    }

    @Test
    fun `test description for O`() {
        val state = CellState.O
        assertEquals("O", state.description)
    }

    @Test
    fun `test description for EMPTY`() {
        val state = CellState.EMPTY
        assertEquals("", state.description)
    }
}
