package isel.leic.tds

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.Message
import isel.leic.tds.storage.Billboard
import isel.leic.tds.ui.console.print
import kotlin.system.exitProcess

/**
 * Contract to be supported by all commands. Notice that commands are mere functions =)
 */
typealias Command = (String?) -> Unit

/**
 * Gets the container bearing the associations between user entered strings and the corresponding command implementation.
 * @param billboard the [Billboard] to be used by all commands
 * @param author    the [Author] instance to be used when posting messages
 * @return the container with the command mappings
 */
fun buildCommands(billboard: Billboard, author: Author): Map<String, Command> {
    return mapOf(
        "EXIT" to { exit() },
        "POST" to { postMessage(billboard, author, it) },
        "GET"  to { getAllMessages(billboard, it)}
    )
}

/**
 * Implementation of the POST command
 * @param billboard the [Billboard] instance to be used
 * @param author    the post author
 * @param parameter the command's parameter
 */
private fun postMessage(billboard: Billboard, author: Author, parameter: String?) {
    if (parameter != null) billboard.postMessage(Message(author, parameter))
    else println("POST command requires a parameter")
}

/**
 * Implementation of the GET command.
 * @param billboard the [Billboard] instance to be used
 * @param parameter the command's parameter, or null if no parameter has been provided
 */
private fun getAllMessages(billboard: Billboard, parameter: String?) {
    val messages =
        if (parameter != null) billboard.getAllMessages(Author(parameter))
        else billboard.getAllMessages()
    messages.print()
}

/**
 * Implementation of the EXIT command.
 */
private fun exit() {
    exitProcess(0)
}
