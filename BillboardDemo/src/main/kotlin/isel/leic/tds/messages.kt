package isel.leic.tds

/**
 * Represents billboard messages.
 * @property author     the author
 * @property content    the message content
 */
data class Message(val author: Author, val content: String) {
    override fun toString() = "${author.id}: $content"
}

/**
 * Represents the information of billboard message authors.
 * Blank characters are not allowed.
 */
data class Author(val id: String) {
    init {
        require(id.isNotEmpty() && id.all { !it.isWhitespace() })
    }
}
