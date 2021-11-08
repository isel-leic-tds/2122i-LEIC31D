package isel.leic.tds

import isel.leic.tds.commands.Result
import kotlin.system.exitProcess

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
    override fun execute(parameter: String?) = Result.EXIT
}

/**
 * Implementation of the POST command
 * @param billboard the [Billboard] instance to be used
 * @param author    the post author
 */
private class PostCommand(
    private val billboard: Billboard,
    private val author: Author) : NinetiesCommand {

    override fun execute(parameter: String?): Result {
        if (parameter == null)
            println("POST must receive a non empty message")
        else {
            billboard.postMessage(Message(author, parameter))
            println("Message posted")
        }
        return Result.CONTINUE
    }
}

/**
 * Implementation of the GET command
 * @param billboard the [Billboard] instance to be used
 */
private class GetCommand(private val billboard: Billboard): NinetiesCommand {
    override fun execute(parameter: String?): Result {
        val messages =
            if(parameter != null) billboard.getAllMessages(Author(parameter))
            else billboard.getAllMessages()

        messages.print()
        return Result.CONTINUE
    }
}
