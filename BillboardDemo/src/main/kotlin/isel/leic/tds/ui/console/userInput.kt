package isel.leic.tds.ui.console

import isel.leic.tds.domain.Author
import isel.leic.tds.domain.toAuthorOrNull

/**
 * Reads from the console the local user information.
 * @return the [Author] instance with the local user information
 */
fun readLocalUserInfo(): Author {
    while (true) {
        print("Please enter your id: ")
        val author = readln().trim().toAuthorOrNull()
        if (author == null) println("Error: user ids cannot contain whitespace characters")
        else return author
    }
}

/**
 * Reads a line from the console and parses it to obtain the corresponding command.
 * @return a pair bearing the command text and its parameter
 */
fun readCommand(): Pair<String, String?> {
    print("> ")
    val input = readln()
    val command = input.substringBefore(delimiter = ' ')
    val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
    return Pair(command.trim().uppercase(), if (argument.isNotBlank()) argument else null)
}

/**
 * Let's use this while we don't get to Kotlin v1.6
 */
fun readln() = readLine()!!

