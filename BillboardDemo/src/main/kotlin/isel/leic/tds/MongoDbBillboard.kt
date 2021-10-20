package isel.leic.tds

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.*

/**
 *
 */
class MongoDbBillboard(private val db: MongoDatabase) : Billboard {

    override fun getAllMessages(author: Author): Iterable<Message> =
        db.getCollectionWithId<Message>(author.id).getAll()

    override fun getAllMessages(): Iterable<Message> {
        return db.getRootCollectionsIds().flatMap {
            getAllMessages(Author(it))
        }
    }

    override fun postMessage(message: Message): Boolean =
        db.createDocument(message.author.id, message)
}