package isel.leic.tds.tictactoe.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoardTests {

    @Test
    fun `initial board is empty`() {
        val sut = Board()
        assertTrue(sut.toList().all { it == null })
    }

    @Test
    fun `makeMove on empty board returns a board with that position occupied`() {
        val initialBoard = Board()
        val row = 0.toRow()
        val column = 0.toColumn()
        val sut = initialBoard.makeMove(at = Coordinate(row, column))
        assertTrue(sut.getMove(at = Coordinate(row, column)) != null)
    }

    @Test
    fun `first player to move is circle`() {
        val sut = Board()
        assertEquals(Player.CIRCLE, sut.turn)
    }

    @Test
    fun `second player to move is cross`() {
        val sut = Board().makeMove(Coordinate(0.toRow(), 0.toColumn()))
        assertEquals(Player.CROSS, sut.turn)
    }

    @Test
    fun `equals on empty boards returns true`() {
        val board1 = Board()
        val board2 = Board()

        assertEquals(board1, board2)
    }
}