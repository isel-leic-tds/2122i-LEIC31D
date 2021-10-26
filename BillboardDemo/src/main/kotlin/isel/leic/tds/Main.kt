package isel.leic.tds

import isel.leic.tds.mongodb.createMongoClient

/**
 * Lecture #6 script
 *
 * Goal: Design a uniform interface for all commands with Unit return type
 * step 1 - using the classic OO approach: NinetiesCommand interface and its top level class implementations
 * step 2 - provide overload for invoke operator (https://kotlinlang.org/docs/operator-overloading.html#invoke-operator)
 * step 3 - using object expressions (https://kotlinlang.org/docs/object-declarations.html)
 * step 4 - using functions and partial function application
 *
 * Goal: Refine the design so that the return type is no longer Unit, thereby making commands also usable in a GUI
 * application (lecture #7 ?)
 * step 5 - Start by dealing with the exit command (enum with OK and EXIT)
 * step 6 - Add support for the returned value: closed hierarchy (sum type) - Result, ExitResult and SuccessResult<T>
 * ...
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

    val driver =
        if (checkEnvironment() == DbMode.REMOTE)
            createMongoClient(System.getenv(ENV_DB_CONNECTION))
        else createMongoClient()

    try {
        // TODO: Improve user interaction to give feedback regarding validity of user id
        val author = readLocalUserInfo()
        val billboard: Billboard = MongoDbBillboard(driver.getDatabase(System.getenv(ENV_DB_NAME)))
        //val dispatcher = buildNinetiesCommands(billboard, author)
        val dispatcher = buildCommands(billboard, author)
        println(dispatcher)

        while (true) {
            val (command, parameter) = readCommand()
            val action = dispatcher[command.uppercase()]
            if (action == null) println("Invalid command")
            else action(parameter)
        }
    }
    finally {
        println("Closing driver ...")
        driver.close();
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
