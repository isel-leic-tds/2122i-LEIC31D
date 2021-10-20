package isel.leic.tds


typealias Command = (String?) -> Unit

fun buildCommands(): Map<String, Command> {
    return mapOf("EXIT" to { })
}

private fun postMessage(billboard: Billboard, author: Author, parameter: String?) {
    if (parameter != null) billboard.postMessage(Message(author, parameter))
    else println("POST command requires a parameter")
}

private fun getAllMessages(billboard: Billboard, parameter: String?) {
    if (parameter != null) billboard.getAllMessages(Author(parameter))
    else billboard.getAllMessages()
}
