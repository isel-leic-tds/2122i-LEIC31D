package isel.leic.tds

import com.google.cloud.firestore.Firestore
import isel.leic.tds.firestore.getAllDocuments
import isel.leic.tds.firestore.getService

/**
 * Class that implements a billboard supported by a Firestore database.
 */
class FirestoreBillboard(private val service: Firestore = getService()) : Billboard {

    /**
     * Gets all messages posted by [author]
     * @param author  the messages' author
     * @return the messages posted by [author]
     */
    override fun getAllMessages(author: Author): Iterable<Message> {
        return service.getAllDocuments(author.id).map {
            Message(
                author = author,
                content = it.getString("content") ?: ""
            )
        }
    }

    /**
     * Gets all messages, regardless of their author
     * @return all messages currently posted on the billboard
     */
    override fun getAllMessages(): Iterable<Message> {
        TODO()
    }

    /**
     * Posts the given message to the billboard
     * @param message the message to be posted
     */
    override fun postMessage(message: Message) {
        TODO()
    }
}