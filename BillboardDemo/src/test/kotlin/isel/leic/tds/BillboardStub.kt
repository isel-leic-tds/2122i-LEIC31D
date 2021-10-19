import isel.leic.tds.Author
import isel.leic.tds.Billboard
import isel.leic.tds.Message
import isel.leic.tds.toAuthor

/**
 * Test double implementation of [Billboard]
 */
class BillboardStub : Billboard {

    val aTestAuthor = "a_test_author".toAuthor()
    private val anotherTestAuthor = "another_test_author".toAuthor()

    val messages: MutableMap<Author, Iterable<Message>> = mutableMapOf(
        aTestAuthor to listOf(Message(aTestAuthor, "Hi"), Message(aTestAuthor, "Hi again")),
        anotherTestAuthor to listOf(Message(anotherTestAuthor, "Hi all")),
    )

    override fun postMessage(message: Message) {
        val authorMessages = messages[message.author] ?: emptyList()
        messages[message.author] = authorMessages + message
    }

    override fun getAllMessages(author: Author) = messages[author] ?: emptyList()

    override fun getAllMessages() = messages.values.flatten()

}