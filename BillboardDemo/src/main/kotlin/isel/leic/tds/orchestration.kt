package isel.leic.tds

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.commands.*
import isel.leic.tds.storage.Billboard
import isel.leic.tds.ui.console.View
import isel.leic.tds.ui.console.getView
import isel.leic.tds.ui.console.postErrorView
import isel.leic.tds.ui.console.postView

/**
 * Represents associations between commands and the corresponding views
 */
data class NinetiesCommandHandler(
    val action: NinetiesCommand,
    val display: View,
    val errorDisplay: (CommandException) -> Unit = { }
)

/**
 * Represents associations between commands and the corresponding views
 */
data class CommandHandler(
    val action: Command,
    val display: View,
    val errorDisplay: (CommandException) -> Unit = { }
)

/**
 * Gets the container bearing the associations between user entered strings and the corresponding command handlers.
 * @param billboard the [Billboard] to be used by all commands
 * @param author    the [Author] instance to be used when posting messages
 * @return the container with the command handler mappings
 */
fun buildNinetyHandlers(billboard: Billboard, author: Author): Map<String, NinetiesCommandHandler> {
    return mapOf(
        "EXIT"  to NinetiesCommandHandler(action = ExitCommand(), display = { }),
        "POST"  to NinetiesCommandHandler(
            action = PostCommand(billboard, author),
            display = ::postView,
            errorDisplay = ::postErrorView
        ),
        "GET"   to NinetiesCommandHandler(GetCommand(billboard), ::getView)
    )
}

/**
 * Gets the container bearing the associations between user entered strings and the corresponding command handlers.
 * @param billboard the [Billboard] to be used by all commands
 * @param author    the [Author] instance to be used when posting messages
 * @return the container with the command handler mappings
 */
fun buildCommands(billboard: Billboard, author: Author): Map<String, CommandHandler> {

    return mapOf(
        "EXIT" to CommandHandler( action = { exit() }, display = { } ),
        "POST" to CommandHandler(
            action = { postMessage(billboard, author, it) },
            display = ::postView,
            errorDisplay = ::postErrorView
        ),
        "GET"  to CommandHandler( action = { getAllMessages(billboard, it) }, display = ::getView)
    )
}

