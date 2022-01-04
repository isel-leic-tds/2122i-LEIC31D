package isel.leic.tds.tictactoe

import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.storage.DbMode
import isel.leic.tds.tictactoe.storage.MongoDbGamesRepository
import isel.leic.tds.tictactoe.storage.getDBConnectionInfo
import isel.leic.tds.tictactoe.storage.mongodb.createMongoClient
import isel.leic.tds.tictactoe.ui.MainWindow

/**
 * Aula 16 script - TBD
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
