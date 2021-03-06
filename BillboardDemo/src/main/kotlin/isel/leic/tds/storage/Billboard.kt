package isel.leic.tds.storage

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.Message

class BillboardAccessException(cause: Exception): Exception(cause)

/**
 * The billboard contract
 */
interface Billboard {

    /**
     * Gets all messages posted by [author]
     * @param author  the messages' author
     * @return the messages posted by [author]
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    fun getAllMessages(author: Author): Iterable<Message>

    /**
     * Gets all messages, regardless of their author
     * @return all messages currently posted on the billboard
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    fun getAllMessages(): Iterable<Message>

    /**
     * Posts the given message to the billboard
     * @param message the message to be posted
     * @return  a boolean value indicating whether the operation succeeded (true) or not (false)
     * @throws [BillboardAccessException] when unable to reach the underlying DB
     */
    fun postMessage(message: Message): Boolean
}