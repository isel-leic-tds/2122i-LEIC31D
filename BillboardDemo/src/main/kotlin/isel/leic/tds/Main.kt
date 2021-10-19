package isel.leic.tds

import isel.leic.tds.firestore.checkFirestoreEnvironment

/**
 * Lecture #5 script
 *
 * step 1 - talk about the adoption of MongoDB, instead of using Firestore. Talk about the tools and JDK version
 * requirements.
 * step 2 - show dependency to KMongo and file mongodb/utils.kt
 * step 3 - remove firestore remnants from the demo (dependency, utils file, FirestoreBillboard class)
 * step 4 - implement Billboard for MongoDB (MongoDbBillboard class) and reiterate the merits of the approach
 * step 5 - implement remaining commands and tests
 * step 6 - evaluate current solution with respect to testability
 * step 7 - design a uniform interface for all commands: Sum type for results (lecture # 6?)
 */

/**
 * The application entry point.
 *
 * The application supports the following commands:
 * GET - Gets all billboard messages
 * GET <author> - Gets all messages from <author>
 * POST <message_content> - Post the given message to the billboard
 * EXIT - Ends the application
 */
fun main() {

    checkFirestoreEnvironment()
    val author = readLocalUserInfo()
    val billboard: Billboard = FirestoreBillboard()

    while (true) {
        val (command, parameter) = readCommand()
        when (command) {
            "GET" -> getMessagesFrom(billboard, author).print()
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

