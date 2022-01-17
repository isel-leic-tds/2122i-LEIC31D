package isel.leic.tds

import GetAuthorId
import MainWindow
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.application
import isel.leic.tds.billboard.Author
import isel.leic.tds.storage.DbMode
import isel.leic.tds.storage.getDBConnectionInfo
import isel.leic.tds.storage.Billboard
import isel.leic.tds.storage.mongodb.MongoDbBillboard
import isel.leic.tds.storage.mongodb.createMongoClient

/**
 * The application orchestration logic, that is, the code for navigating between windows and for resolving global
 * dependencies.
 */
fun main() {

    val dbInfo = getDBConnectionInfo()
    val driver =
        if (dbInfo.mode == DbMode.REMOTE) createMongoClient(dbInfo.connectionString)
        else createMongoClient()

    driver.use {
        val billboard: Billboard = MongoDbBillboard(driver.getDatabase(dbInfo.dbName))
        application {
            MaterialTheme {
                val author = remember { mutableStateOf<Author?>(null) }

                val localUser = author.value
                if (localUser == null) {
                    GetAuthorId(onUserIdEntered = { author.value = it }, ::exitApplication)
                }
                else {
                    MainWindow(author = localUser, billboard = billboard, onClose = ::exitApplication)
                }
            }
        }
    }
}
