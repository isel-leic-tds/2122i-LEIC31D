package isel.leic.tds

import kotlin.test.Test
import kotlin.test.assertEquals

class CommandsTests {

    @Test
    fun `getAllMessages from an author returns his messages`() {
        val billboardStub = BillboardStub()
        val author = Author("testAuthor")
        billboardStub.postMessage(Message(author, "Test"))
        billboardStub.postMessage(Message(Author("other"), "Other test"))
        val messages = getMessagesFrom(billboardStub, author)
        assertEquals(1, messages.toList().size)
    }
}