package isel.leic.tds.tictactoe

import isel.leic.tds.tictactoe.model.Board
import kotlin.test.*

class BoardTests {

    @Test
    fun `equals on empty boards returns true`() {
        assertEquals(Board(), Board())
    }
}