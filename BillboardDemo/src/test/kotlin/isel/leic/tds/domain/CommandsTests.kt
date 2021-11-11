package isel.leic.tds.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class CommandsTests {

    @Test
    fun `getAllMessages from an author returns his messages`() {
        val billboardStub = BillboardStub()
        val knownAuthor = billboardStub.aTestAuthor
        val messages = billboardStub.getAllMessages(knownAuthor)
        assertEquals(expected = 2, actual = messages.toList().size)
        assertEquals(expected = billboardStub.messages[knownAuthor], actual = messages)
    }
}