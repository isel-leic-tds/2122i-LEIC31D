package isel.leic.tds.ui

import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message
import isel.leic.tds.storage.Billboard
import kotlinx.coroutines.delay

/**
 * Billboard implementation used in dev time to experiment with the UI
 * I'm leaving it in the code base simply for documentation purposes
 */
class FakeBillboard : Billboard {
    override suspend fun post(message: Message) {
        delay(1000)
    }

    override suspend fun getAllFrom(author: Author): Iterable<Message> {
        delay(1000)
        val messagesList = mutableListOf<Message>()
        for (i: Int in 0 .. 20) {
            messagesList.add(Message(author = author, "Message $i content"))
        }
        return messagesList
    }

    override suspend fun getAllMessages(): Iterable<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAuthors(): Iterable<Author> {
        delay(1000)
        val authorsList = mutableListOf<Author>()
        for (i: Int in 0..20) {
            authorsList.add(Author("author_$i"))
        }
        return authorsList
    }
}