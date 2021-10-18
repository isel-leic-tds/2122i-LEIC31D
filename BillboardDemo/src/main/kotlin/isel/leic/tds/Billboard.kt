package isel.leic.tds

interface Billboard {

    /**
     * Gets all messages posted by [author]
     * @param author  the messages' author
     * @return the messages posted by [author]
     */
    fun getAllMessages(author: Author): Iterable<Message>

    /**
     * Gets all messages, regardless of their author
     * @return all messages currently posted on the billboard
     */
    fun getAllMessages(): Iterable<Message>

    /**
     * Posts the given message to the billboard
     * @param message the message to be posted
     */
    fun postMessage(message: Message)
}