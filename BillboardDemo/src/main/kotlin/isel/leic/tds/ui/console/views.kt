package isel.leic.tds.ui.console

import isel.leic.tds.domain.Message

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
 * Displays the result of GET command executions
 */
fun getView(input: Any?) {
    val messages = input as Iterable<Message>
    messages.print()
}

/**
 * Displays the result of POST command executions
 */
fun postView(input: Any?) {
    val success = input as Boolean
    println(
        if (success) "Message posted!"
        else "Something went wrong"
    )
}