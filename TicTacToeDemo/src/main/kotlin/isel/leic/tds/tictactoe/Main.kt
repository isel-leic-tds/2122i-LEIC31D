package isel.leic.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.model.Board
import isel.leic.tds.tictactoe.model.Coordinate
import isel.leic.tds.tictactoe.model.Player
import isel.leic.tds.tictactoe.model.isFree
import isel.leic.tds.tictactoe.ui.BoardView


/**
 * Lecture 14 script
 *
 * step 1 - Create a minimalistic Board representation (immutable)
 *  step 1.1 - Representations for coordinates (Row, Column and Coordinate). Eliminate invalid states and include Int
 *  extensions
 *  step 1.2 - Use a class with an Array of Arrays ensuring that it's an implementation detail (i.e. not visible in the
 *  public contract)
 *    Notes: private constructor; companion object with invoke operator; minimalistic interface: getMoveAt, makeMoveAt,
 *    toList and indexing operator with Row and Column. Remaining operations are added with extension functions.
 *  step 1.3 - Implement extension functions isPositionFree and isTied
 * step 2 - Create a BoardView Composable which receives the board and the onTileSelected event handler
 * step 3 - Revisit the MVC design pattern in this context. Which is which?
 * step 4 - Implement the `equals on empty boards returns true` test. What is wrong here? Use data class for the Board
 * and look at the warning issued by the IDE. Revisit identity and equivalence. Discuss the adequacy of using arrays
 * step 5 - Refactor the Board implementation so that it uses a list instead of the array of arrays making sure that
 * the remainder of the code doesn't break.
 * step 6 - Lets store it on the DB
 *  step 6.1. - Design a DTO. Discuss its purpose.
 */
@Composable
@Preview
fun App() {
    MaterialTheme {
        val board = remember { mutableStateOf(Board()) }
        BoardView(board.value, onTileSelected = { player: Player?, coordinate: Coordinate ->
            if (board.value.isFree(coordinate)) {
                board.value = board.value.makeMove(coordinate)
            }
        })
    }
}

fun main() = application {
    Window(
        state = WindowState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
