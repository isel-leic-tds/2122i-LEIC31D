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
        require(isValidAuthorIdentifier(id))
    }
}

/**
 * Extension function that converts this string to an [Author] instance.
 * @return  the [Author] instance
 * @throws  IllegalArgumentException if this string is not a valid author identifier.
 */
fun String.toAuthor() = Author(this)

/**
 * Extension function that converts this string to an [Author] instance.
 * @return  the [Author] instance or null if this string is not a valid author identifier.
 */
fun String.toAuthorOrNull() = if (isValidAuthorIdentifier(this)) Author(this) else null

/**
 * Checks whether the given string is a valid author identifier.
 * @param   id  the string to be checked
 * @return  true if [id] can be used as an author identifier, false otherwise
 */
private fun isValidAuthorIdentifier(id: String) = id.isNotEmpty() && id.all { !it.isWhitespace() }


/**
 * Extension method used to print this sequence of [Message] instances to the console.
 */
fun Iterable<Message>.print() {
    forEach { println(it) }
}
