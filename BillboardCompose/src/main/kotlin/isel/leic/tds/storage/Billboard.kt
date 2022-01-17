package isel.leic.tds.storage

import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message

/**
 * The billboard contract
 */
interface Billboard {
    /**
     * Posts the [message] to the billboard
     * @param message   the message to be posted
     */
    suspend fun post(message: Message)

    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    suspend fun getAllFrom(author: Author): Iterable<Message>

    /**
     * Gets all messages posted on the billboard, regardless of their author
     * @return  the messages on the billboard
     */
    suspend fun getAllMessages(): Iterable<Message>

    /**
     * Gets all authors that posted messages on the billboard
     * @return  the authors
     */
    suspend fun getAllAuthors(): Iterable<Author>
}