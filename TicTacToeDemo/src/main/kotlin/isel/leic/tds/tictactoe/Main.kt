package isel.leic.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.model.Board
import isel.leic.tds.tictactoe.model.Coordinate
import isel.leic.tds.tictactoe.model.Player
import isel.leic.tds.tictactoe.ui.BoardView


/**
 * Lecture 15 script
 *
 * step 1 - Lets store the game state on to the DB
 *      step 1.1 - Design a DTO. Discuss its purpose and compare it with the domain model entities.
 *        - DTO: https://www.baeldung.com/java-dto-pattern and https://martinfowler.com/eaaCatalog/dataTransferObject.html
 *        - Domain Model: https://martinfowler.com/eaaCatalog/domainModel.html
 *      step 1.2 - Create the games' repository abstraction, starting with the update operation.
 *        - Repository - https://www.martinfowler.com/eaaCatalog/repository.html
 *      step 1.3 - Copy environment.kt and utils.kt files used previously in the Billboard Demo. Underline upsert use
 *      in replaceOne replace options
 *      step 1.4 - Implement the games' repository abstraction to store the ongoing game in a local MongoDB
 * step 2 - Create the game's main window, with a menu containing a refresh option
 *      step 2.1 - Refine the repository to support the operation for getting an ongoing game
 *      step 2.2 - Identify the possible Application states: "Game Not Started" and "Playing Game"
 *          Define these states starting with an enum class and then use a closed hierarchy.
 *      step 2.3 - Define the MainWindowMenu composable
 *      step 2.4 - Define one composable for each state, orchestrated by the MainWindow Composable
 */
@Composable
@Preview
fun App() {
    MaterialTheme {
        val board = remember { mutableStateOf(Board()) }

        fun maybeMakeMove(tileContent: Player?, coordinate: Coordinate) {
            if (tileContent == null) {
                board.value = board.value.makeMove(coordinate)
            }
        }

        BoardView(board.value, onTileSelected = ::maybeMakeMove)
    }
}

fun main() = application {
    Window(
        state = WindowState(size = DpSize.Unspecified),
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
