package isel.leic.tds.storage

import com.mongodb.MongoException
import com.mongodb.client.MongoDatabase
import isel.leic.tds.domain.Author
import isel.leic.tds.domain.Message
import isel.leic.tds.storage.mongodb.createDocument
import isel.leic.tds.storage.mongodb.getAll
import isel.leic.tds.storage.mongodb.getCollectionWithId
import isel.leic.tds.storage.mongodb.getRootCollectionsIds

fun <T> tryBillboardAccess(block: () -> T) =
    try { block() }
    catch (ex: MongoException) { throw BillboardAccessException(ex) }


/**
 * Implementation of a [Billboard] backed by a MongoDB instance.
 */
class MongoDbBillboard(private val db: MongoDatabase) : Billboard {

    /**
     * Gets all messages posted by [author]
     * @param author  the messages' author
     * @return the messages posted by [author]
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    override fun getAllMessages(author: Author): Iterable<Message> =
        tryBillboardAccess { db.getCollectionWithId<Message>(author.id).getAll() }
    /**
     * Gets all messages, regardless of their author
     * @return all messages currently posted on the billboard
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    override fun getAllMessages(): Iterable<Message> =
        tryBillboardAccess {
            db.getRootCollectionsIds().flatMap {
                getAllMessages(Author(it))
            }
        }

    /**
     * Posts the given message to the billboard
     * @param message the message to be posted
     * @return  a boolean value indicating whether the operation succeeded (true) or not (false)
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    override fun postMessage(message: Message): Boolean =
        tryBillboardAccess { db.createDocument(message.author.id, message) }
}