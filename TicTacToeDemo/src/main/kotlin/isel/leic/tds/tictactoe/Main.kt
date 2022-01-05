package isel.leic.tds.tictactoe

import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.storage.DbMode
import isel.leic.tds.tictactoe.storage.MongoDbGamesRepository
import isel.leic.tds.tictactoe.storage.getDBConnectionInfo
import isel.leic.tds.tictactoe.storage.mongodb.createMongoClient
import isel.leic.tds.tictactoe.ui.MainWindow

/**
 * Lecture 16 script
 *
 * step 1 - Guided tour to the current code base
 *      step 1.1 - Justify approach to host the mutable state at the top-level Composable. Underline the single
 *      mutation point.
 *      step 1.2 - Describe the solution's composable functions and revisit Composable scopes and their purpose
 *      step 1.3 - Criticize the current solution
 * step 2 - Refactor the solution to include repository aware domain artefacts
 *      step 2.1 - Refactor application's state to make it a domain artefact. Rename the closed hierarchy types to
 *      Game, GameNotStarted and GameStarted
 *      step 2.2 - Rename storage.GameState to SharedGameState, thereby clarifying its purpose
 *      step 2.3 - Move domain logic to its place: the Game sum type
 *          isLocalPlayerTurn, makeMove, startGame and refresh
 * step 3 - Add support for requesting the user the game's name, verifying its existence
 *      step 3.1 - Create the Dialog composable. Discuss presentation state versus application state, thereby justifying
 *      the use of remember state on the Dialog (as opposed to the approach for the game state)
 *      step 3.2 - Add user input sanitization, to prevent empty game identifiers
 *          - Remember: parse, don't validate
 * step 4 - Alert students for the eventual bug in Compose compiler (is it a bug? must report it)
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
        application {
            MainWindow(gamesRepository, ::exitApplication)
        }
    }
}
