package isel.leic.tds.billboard

/**
 * Represents billboard messages
 * @property author   the message author
 * @property content  the message content
 */
data class Message(val author: Author, val content: String) {
    override fun toString() = "${author.id}: $content"
}

/**
 * Represents billboard message's authors.
 * @property id the author identifier (cannot be a string comprised of only whitespace characters).
 */
data class Author(val id: String) {
    init {
        require(isValidAuthorIdentifier(id))
    }
}

/**
 * Checks whether the given string is a valid author identifier.
 * @param   id  the string to be checked
 * @return  true if [id] can be used as an author identifier, false otherwise
 */
private fun isValidAuthorIdentifier(id: String) = id.isNotBlank()

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
