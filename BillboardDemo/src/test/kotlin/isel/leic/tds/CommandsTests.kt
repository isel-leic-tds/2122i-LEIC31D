package isel.leic.tds

import BillboardStub
import kotlin.test.Test
import kotlin.test.assertEquals

class CommandsTests {

    @Test
    fun `getAllMessages from an author returns his messages`() {
        val billboardStub = BillboardStub()
        val knownAuthor = billboardStub.aTestAuthor
        val messages = getMessagesFrom(billboardStub, knownAuthor)
        assertEquals(expected = 1, actual = messages.toList().size)
        assertEquals(expected = billboardStub.messages[knownAuthor], actual = messages)
    }
}