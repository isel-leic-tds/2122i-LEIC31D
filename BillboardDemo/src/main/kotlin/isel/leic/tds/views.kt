package isel.leic.tds

/**
 * Extension method used to print this sequence of [Message] instances to the console.
 */
fun Iterable<Message>.print() {
    forEach { println(it) }
}

/**
 * A command view is merely a function that renders the command execution result.
 */
typealias View = (input: Any?) -> Unit

/**
 * Gets the container bearing the associations between user entered strings and the functions that display
 * the results of the corresponding commands.
 * @return the container with the command identifier to view mappings
 */
fun buildViews(): Map<String, View> {
    return mapOf(
        "EXIT" to { },
        "POST" to ::postView,
        "GET" to ::getView
    )
}

/**
 * Displays the result of GET command executions
 */
private fun getView(input: Any?) {
    val messages = input as Iterable<Message>
    messages.print()
}

/**
 * Displays the result of POST command executions
 */
private fun postView(input: Any?) {
    val success = input as Boolean
    println(
        if (success) "Message posted!"
        else "Something went wrong"
    )
}