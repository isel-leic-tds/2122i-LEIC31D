package isel.leic.tds.storage.mongodb

import com.mongodb.client.MongoDatabase
import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message
import isel.leic.tds.storage.Billboard

/**
 * Implements a billboard using a MongoDB instance. Upon construction the concrete [MongoDatabase] instance may be injected
 * thereby enabling automatic tests with test doubles (https://martinfowler.com/bliki/TestDouble.html)
 *
 * @constructor receives the [MongoDatabase] instance to be used. If no instance is received, it defaults to using a
 * local DB instance.
 */
class MongoDbBillboard(private val db: MongoDatabase) : Billboard {

    /**
     * Posts the [message] to the billboard
     * @param message   the message to be posted
     */
    override suspend fun post(message: Message) {
        db.createDocument<Message>(message.author.id, message)
    }

    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    override suspend fun getAllFrom(author: Author): Iterable<Message> {
        return db.getCollectionWithId<Message>(author.id).getAll()
    }

    /**
     * Gets all messages posted on the billboard, regardless of their author
     * @return  the messages on the billboard
     */
    override suspend fun getAllMessages(): Iterable<Message> {
        return db.getRootCollectionsIds()
            .flatMap { getAllFrom(Author(it)) }
    }

    /**
     * Gets all authors that posted messages on the billboard
     * @return  the authors
     */
    override suspend fun getAllAuthors(): Iterable<Author> {
        return db.getRootCollectionsIds().map { Author(it) }
    }
}
