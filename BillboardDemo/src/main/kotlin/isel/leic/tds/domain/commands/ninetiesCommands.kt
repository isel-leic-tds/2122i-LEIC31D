package isel.leic.tds.domain.commands

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.Message
import isel.leic.tds.storage.Billboard

/**
 * Contract to be supported by all commands (an Object-Oriented style)
 */
fun interface NinetiesCommand {
    /**
     * Executes this command passing it the given parameter
     * @param parameter the commands parameter, or null, if no parameter has been passed
     */
    fun execute(parameter: String?): Result

    /**
     * Overload of invoke operator, for convenience.
     */
    operator fun invoke(parameter: String? = null) = execute(parameter)
}

/**
 * Gets the container bearing the associations between user entered strings and the corresponding command implementation.
 * @param billboard the [Billboard] to be used by all commands
 * @param author    the [Author] instance to be used when posting messages
 * @return the container with the command mappings
 */
fun buildNinetiesCommands(billboard: Billboard, author: Author) : Map<String, NinetiesCommand> {
    return mapOf(
        "EXIT" to ExitCommand(),
        "POST" to PostCommand(billboard, author),
        "GET" to GetCommand(billboard)
    )
}

/**
 * Implementation of the EXIT command
 */
private class ExitCommand : NinetiesCommand {
    override fun execute(parameter: String?) = ExitResult
}

/**
 * Implementation of the POST command
 * @param billboard the [Billboard] instance to be used
 * @param author    the post author
 */
private class PostCommand(
    private val billboard: Billboard,
    private val author: Author
) : NinetiesCommand {

    override fun execute(parameter: String?): ValueResult<Boolean> {
        requireNotNull(parameter)
        return ValueResult(billboard.postMessage(Message(author, parameter)))
    }
}

/**
 * Implementation of the GET command
 * @param billboard the [Billboard] instance to be used
 */
private class GetCommand(private val billboard: Billboard): NinetiesCommand {
    override fun execute(parameter: String?) = ValueResult(
            if(parameter != null) billboard.getAllMessages(Author(parameter))
            else billboard.getAllMessages()
        )
}
