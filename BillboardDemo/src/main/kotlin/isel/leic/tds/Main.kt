package isel.leic.tds

import isel.leic.tds.domain.commands.ExitResult
import isel.leic.tds.domain.commands.ValueResult
import isel.leic.tds.storage.*
import isel.leic.tds.storage.mongodb.createMongoClient
import isel.leic.tds.ui.console.readCommand
import isel.leic.tds.ui.console.readLocalUserInfo

/**
 * Lecture #9 script (Finishing up the Billboard console application design)
 *
 * Goal: Finishing up the Billboard console application design.
 * step 0 - talk about the current solution structure
 * step 1 - The association between user entered strings and commands and views doesn't respect the DRY principle. The
 * knowledge of which string corresponds to which command is repeated. Let's fix this!
 * step 2 - Error handling: errors deserve to be modeled.
 * step 3 - Revisit the function based approach (with partial function application) and refactor it to use the new
 * result representation.
 * step 4 - Revisit DB data model and related code. Implement a couple of e2e tests.
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