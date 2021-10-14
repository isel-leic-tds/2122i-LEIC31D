package isel.leic.tds

import isel.leic.tds.firestore.checkEnvironment

/**
 * The application entry point
 */
fun main() {

    checkEnvironment()
    val author = readLocalUserInfo()

    while (true) {
        val (command, parameter) = readCommand()
        when (command) {
            "GET" -> getMessagesFrom(author).print()
            "POST" -> TODO()
            else -> println("Invalid command")
        }
    }
}

/**
 * Reads from the console the local user information.
 * @return the [Author] instance with the local user information
 */
fun readLocalUserInfo(): Author {
    print("Please enter your id: ")
    return Author(readln().trim())
}

/**
 * Reads a line from the console and parses it to obtain the corresponding command.
 * @return a pair bearing the command text and its parameter
 */
private fun readCommand(): Pair<String, String?> {
    print("> ")
    val input = readln()
    val command = input.substringBefore(delimiter = ' ')
    val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
    return Pair(command.trim(), if (argument.isNotBlank()) argument else null)
}

/**
 * Extension method used to print this sequence of [Message] instances to the console.
 */
private fun Iterable<Message>.print() {
    forEach { println(it) }
}

/**
 * Let's use this while we don't get to Kotlin v1.6
 */
private fun readln() = readLine()!!

