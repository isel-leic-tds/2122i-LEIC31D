package isel.leic.tds

import isel.leic.tds.domain.commands.ExitResult
import isel.leic.tds.domain.commands.ValueResult
import isel.leic.tds.storage.*
import isel.leic.tds.storage.mongodb.createMongoClient
import isel.leic.tds.ui.console.readCommand
import isel.leic.tds.ui.console.readLocalUserInfo

/**
 * Lecture #10 script
 *
 * Goal: Finishing up the Billboard console application design. For real! =)
 * step 0 - revisit the current solution structure
 * step 1 - Revisit the function based approach (with partial function application) and refactor it to use the new
 * result representation.
 *  step 1.1 - Talk about partial application using a simple example (multiply and multiplyBy2)
 *  step 1.2 - Revisit the function based approach for the commands' implementations
 * step 2 - Handle errors in commands (i.e. Invalid arguments). Create "error views" for the commands.
 * step 3 - Make minor cosmetic improvements:
 *  step 3.1 - Factor out tryBillboardAccess function in MongoDbBillboard
 *  step 3.2 - Use MongoClient's use method (inherited from Closeable) in main
 *  step 3.3 - Use ifBlank in readCommand function (userInput module)
 * step 4 - Testing our design in the light of the Open-Closed Principle
 *  step 4.1 - Add the user command, which displays the id of the current user
 *  step 4.2 - Add logging of command execution time:
 *      - With the Decorator design pattern (nineties commands)
 *      - With function composition (function based commands)
 * step 5 - Finish up by writing some more tests, including integration tests.
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

    val dbInfo = getDBConnectionInfo()
    val driver =
        if (dbInfo.mode == DbMode.REMOTE) createMongoClient(dbInfo.connectionString)
        else createMongoClient()

    try {
        val billboard: Billboard = MongoDbBillboard(driver.getDatabase(dbInfo.dbName))
        val author = readLocalUserInfo()
        val dispatcher = buildNinetyHandlers(billboard, author)
        //val dispatcher = buildCommands(billboard, author)

        while (true) {
            val (command, parameter) = readCommand()
            val handler = dispatcher[command]
            if (handler == null) println("Invalid command")
            else {
                when (val result = handler.action(parameter)) {
                    is ExitResult -> break
                    is ValueResult<*> -> handler.display(result.data)
                }
            }
        }
    }
    catch (e: BillboardAccessException) {
        println("An unknown error occurred while trying to reach the database. " +
                if (dbInfo.mode == DbMode.REMOTE) "Check your network connection."
                else "Is your local database started?")
    }
    finally {
        println("Closing driver ...")
        driver.close()
    }
}