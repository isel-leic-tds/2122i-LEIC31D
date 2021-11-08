package isel.leic.tds

import isel.leic.tds.commands.Result
import isel.leic.tds.mongodb.createMongoClient

/**
 * Lecture #7 script (Single Responsibility Principle)
 *
 * Goal: Complete the billboard console application
 * step 1 - review the solution so far. Identify the solutions elements' and reassert their nature and purpose:
 *  - Data and application specific behaviour (a.k.a. domain model and domain logic), I/O (storage), user interface.
 * step 2 - Make the same exercise for the project assignment
 * step 3 - revisit the domain logic implemented with a classical Object-Oriented approach (NinetiesCommand hierarchy)
 * step 4 - Critical analysis: the DRY principle is not being observed in the NinetiesCommand hierarchy
 *  - implement invoke operator at the NinetiesCommand interface, thereby eliminating the repetition
 * step 5 - Refactor the OOP approach to use, instead of named implementations,
 *  - object expressions (https://kotlinlang.org/docs/object-declarations.html)
 *  - SAM conversions, by turning the NinetiesCommand interface into a SAM interface (https://kotlinlang.org/docs/fun-interfaces.html)
 * step 6 - Refine the design so that the return type is no longer Unit, thereby making commands also usable in a GUI
 * (and testable, and respectful of the Single Responsibility Principle
 * step 6.1 - Start by dealing with the exit command (enum with CONTINUE and EXIT)
 * step 6.2 - Add support for the returned value: closed hierarchy (sum type) - Result, ExitResult and SuccessResult<T>
 *  - (https://kotlinlang.org/docs/sealed-classes.html)
 * step 7 - Revisit the function based approach (with partial function application)
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

        val dispatcher = buildNinetiesCommands(billboard, author)
        //val dispatcher = buildCommands(billboard, author)
        println(dispatcher)

        while (true) {
            val (command, parameter) = readCommand()
            val action = dispatcher[command.uppercase()]
            if (action == null) println("Invalid command")
            else {
                val result = action(parameter)
                if (result == Result.EXIT)
                    break
            }
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
