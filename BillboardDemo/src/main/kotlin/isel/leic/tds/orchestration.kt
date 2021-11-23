package isel.leic.tds

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.commands.ExitCommand
import isel.leic.tds.domain.commands.GetCommand
import isel.leic.tds.domain.commands.NinetiesCommand
import isel.leic.tds.domain.commands.PostCommand
import isel.leic.tds.storage.Billboard
import isel.leic.tds.ui.console.View
import isel.leic.tds.ui.console.getView
import isel.leic.tds.ui.console.postView

/**
 * Represents associations between commands and the corresponding views
 */
data class NinetiesCommandHandler(
    val action: NinetiesCommand,
    val display: View
)

/**
 * Gets the container bearing the associations between user entered strings and the corresponding command handlers.
 * @param billboard the [Billboard] to be used by all commands
 * @param author    the [Author] instance to be used when posting messages
 * @return the container with the command handler mappings
 */
fun buildNinetyHandlers(billboard: Billboard, author: Author): Map<String, NinetiesCommandHandler> {
    return mapOf(
        "EXIT"  to NinetiesCommandHandler(ExitCommand(), { }),
        "POST"  to NinetiesCommandHandler(PostCommand(billboard, author), ::postView),
        "GET"   to NinetiesCommandHandler(GetCommand(billboard), ::getView)
    )
}