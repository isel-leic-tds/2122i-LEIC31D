package isel.leic.tds

import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.createDocument
import isel.leic.tds.mongodb.getAll
import isel.leic.tds.mongodb.getCollectionWithId
import isel.leic.tds.mongodb.getRootCollectionsIds

/**
 * Implementation of a [Billboard] backed by a MongoDB instance.
 */
class MongoDbBillboard(private val db: MongoDatabase) : Billboard {

    /**
     * Gets all messages posted by [author]
     * @param author  the messages' author
     * @return the messages posted by [author]
     */
    override fun getAllMessages(author: Author): Iterable<Message> =
        db.getCollectionWithId<Message>(author.id).getAll()

    /**
     * Gets all messages, regardless of their author
     * @return all messages currently posted on the billboard
     */
    override fun getAllMessages(): Iterable<Message> {
        return db.getRootCollectionsIds().flatMap {
            getAllMessages(Author(it))
        }
    }

    /**
     * Posts the given message to the billboard
     * @param message the message to be posted
     * @return  a boolean value indicating whether the operation succeeded (true) or not (false)
     */
    override fun postMessage(message: Message): Boolean =
        db.createDocument(message.author.id, message)
}