package isel.leic.tds.domain.commands

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.Message
import isel.leic.tds.storage.Billboard

/**
 * Contract to be supported by all commands. Notice that commands are mere functions =)
 */
typealias Command = (String?) -> Result

/**
 * Used to add logging information to the commands execution
 * @param decorated the command whose execution is to be logged
 */
fun log(decorated: Command): Command = {
        val start = System.currentTimeMillis()
        val result = decorated(it)
        println("Executed in ${System.currentTimeMillis() - start} ms")
        result
    }

/**
 * Implementation of the POST command
 * @param billboard the [Billboard] instance to be used
 * @param author    the post author
 * @param parameter the command's parameter
 */
fun postMessage(billboard: Billboard, author: Author, parameter: String?): ValueResult<Boolean> {
    if (parameter == null) throw CommandException()
    return ValueResult(billboard.postMessage(Message(author, parameter)))
}

/**
 * Implementation of the GET command.
 * @param billboard the [Billboard] instance to be used
 * @param parameter the command's parameter, or null if no parameter has been provided
 */
fun getAllMessages(billboard: Billboard, parameter: String?): ValueResult<Iterable<Message>> {
    return ValueResult(
        if (parameter != null) billboard.getAllMessages(Author(parameter))
        else billboard.getAllMessages()
    )
}

/**
 * Implementation of the EXIT command.
 */
fun exit() = ExitResult

/**
 * Implementation of the USER command.
 */
fun getUserId(author: Author): ValueResult<String> = ValueResult(author.id)
