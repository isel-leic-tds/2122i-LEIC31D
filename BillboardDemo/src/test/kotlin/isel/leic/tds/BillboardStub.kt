package isel.leic.tds

/**
 * Billboard double for testing purposes
 */
class BillboardStub : Billboard {

    private val messages: MutableMap<Author, List<Message>> = mutableMapOf()

    override fun getAllMessages(author: Author) = messages[author] ?: listOf()

    override fun getAllMessages(): Iterable<Message> {
        TODO("Not yet implemented")
    }

    override fun postMessage(message: Message) {
        val authorMessages = messages[message.author]
        messages[message.author] =
            if (authorMessages != null) authorMessages + message
            else listOf(message)
    }
}