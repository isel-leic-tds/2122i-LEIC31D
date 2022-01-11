package isel.leic.tds.tictactoe

import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.storage.DbMode
import isel.leic.tds.tictactoe.storage.MongoDbGamesRepository
import isel.leic.tds.tictactoe.storage.getDBConnectionInfo
import isel.leic.tds.tictactoe.storage.mongodb.createMongoClient
import isel.leic.tds.tictactoe.ui.MainWindow

/**
 * Lecture #18 script
 *
 * step 1 - Move artificial delays (Thread sleeps) to mongodb.utils functions
 * step 2 - Fix error related to the threading model: I/O must be removed from the UI thread
 *      step 2.1 - Add suspend modifier and withContext to mongodb.utils functions
 *      step 2.2 - Talk about Kotlin's qualified this (https://kotlinlang.org/docs/this-expressions.html)
 *      step 2.3 - Add suspend modifier to the repository functions (suspending functions are colored, remember?)
 *      step 2.4 - Present building block rememberCoroutineScope()
 * step 3 - Add support to periodic auto-refresh
 *      step 3.1 - Gentle discussion of push and pull communication models
 *      step 3.2 - Change state machine so that the game is created when it is started
 *      step 3.3 - Present the @Composable LaunchedEffect building block (using a simpler example)
 *      step 3.4 - Auto-refresh for-the-win =)
 */

/**
 * The application's entry point
 */
fun main() {

    val dbInfo = getDBConnectionInfo()
    val driver =
        if (dbInfo.mode == DbMode.REMOTE) createMongoClient(dbInfo.connectionString)
        else createMongoClient()

    driver.use {
        val gamesRepository = MongoDbGamesRepository(it.getDatabase(dbInfo.dbName))
        application(exitProcessOnExit = false) {
            MainWindow(gamesRepository, ::exitApplication)
            println("After MainWindow first composition")
        }
        println("After application scope end")
    }
    println("Main ends")
}
