package isel.leic.tds

import isel.leic.tds.mongodb.createMongoClient

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
 *
 * Execution is parameterized through the following environment variables:
 * - MONGO_DB_NAME, bearing the name of the database to be used
 * - MONGO_DB_CONNECTION, bearing the connection string to the database server. If absent, the application
 * uses a local server instance (it must be already running)
 */
fun main() {

    val author = readLocalUserInfo()

    val driver =
        if (checkEnvironment() == DbMode.REMOTE)
            createMongoClient(System.getenv(ENV_DB_CONNECTION))
        else createMongoClient()

    val billboard: Billboard = MongoDbBillboard(driver.getDatabase(System.getenv(ENV_DB_NAME)))

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

/**
 * Environment variables
 */
private const val ENV_DB_NAME = "MONGO_DB_NAME"
private const val ENV_DB_CONNECTION = "MONGO_DB_CONNECTION"

/**
 * Represents the supported execution modes:
 *  LOCAL   - The database server is running locally
 *  REMOTE  - The database server is running remotely
 */
private enum class DbMode { LOCAL, REMOTE }

/**
 * Verifies the execution environment to determine the execution mode
 * @return the execution mode
 */
private fun checkEnvironment(): DbMode {
    requireNotNull(System.getenv(ENV_DB_NAME)) {
        "You must specify the environment variable $ENV_DB_NAME"
    }

    return if (System.getenv(ENV_DB_CONNECTION) != null) DbMode.REMOTE
    else DbMode.LOCAL
}