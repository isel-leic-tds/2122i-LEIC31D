package isel.leic.tds.tictactoe.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import isel.leic.tds.tictactoe.model.*
import isel.leic.tds.tictactoe.storage.GamesRepository
import isel.leic.tds.tictactoe.storage.toBoard
import isel.leic.tds.tictactoe.storage.toGameState
import isel.leic.tds.tictactoe.ui.board.BoardView
import java.lang.IllegalStateException

/**
 * Composable that defines the application's main window. The application state is represented by the
 * [ApplicationState] closed hierarchy.
 *
 * @param repository        The games' repository
 * @param onCloseRequested  The function to be called when the user intends to close the window
 */
@Composable
fun MainWindow(repository: GamesRepository, onCloseRequested: () -> Unit) = Window(
    onCloseRequest = onCloseRequested,
    title = "Tic-Tac-Toe",
    state = WindowState(size = DpSize.Unspecified),
) {
    val state = remember { mutableStateOf<ApplicationState>(GameNotStartedState) }

    fun makeMove(at: Coordinate): GameStartedState =
        with(state.value as GameStartedState) {
            val newState = copy(board = board.makeMove(at))
            repository.updateOngoingGame("demo_game", newState.board.toGameState())
            newState
        }

    fun startGame(localPlayer: Player): GameStartedState =
        GameStartedState(localPlayer = localPlayer, Board(turn = Player.CIRCLE))

    fun refreshGame(): GameStartedState {
        val game = repository.getOngoingGame("demo_game")
        val currentState = state.value
        return if (game != null && currentState is GameStartedState) {
            currentState.copy(board = game.toBoard())
        } else throw IllegalStateException()
    }

    val currentApplicationState = state.value
    MainWindowMenu(currentApplicationState,
        onRefreshRequested = { state.value = refreshGame() },
        onStartRequested = { state.value = startGame(Player.CIRCLE) },
        onJoinRequested = { state.value = startGame(Player.CROSS) }
    )

    when (currentApplicationState) {
        is GameNotStartedState -> GameNotStartedContent()
        is GameStartedState -> GameStartedContent(
            currentApplicationState,
            onMoveRequest = { at -> state.value = makeMove(at) },
        )
    }
}

/**
 * The [MainWindow]'s menu
 */
@Composable
private fun FrameWindowScope.MainWindowMenu(
    state: ApplicationState,
    onRefreshRequested: (GameStartedState) -> Unit,
    onStartRequested: () -> Unit,
    onJoinRequested: () -> Unit
) = MenuBar {

    data class MenuState(val start: Boolean, val join: Boolean, val refresh: Boolean, val forfeit: Boolean)

    val menuState = MenuState(
        start = state is GameNotStartedState,
        join = state is GameNotStartedState,
        refresh = state is GameStartedState && state.localPlayer != state.board.turn,
        forfeit = state is GameStartedState && state.localPlayer == state.board.turn
    )

    Menu("Game") {
        Item("Start", enabled = menuState.start, onClick = onStartRequested)
        Item("Join", enabled = menuState.join, onClick = onJoinRequested)
    }
    Menu("Options") {
        Item("Refresh", enabled = menuState.refresh, onClick = { onRefreshRequested(state as GameStartedState) })
        Item("Forfeit", enabled = menuState.forfeit, onClick = { })
    }
}

/**
 * Composable used to specify the [MainWindow] content when the application is in the [GameStartedState]
 */
@Composable
private fun GameStartedContent(state: GameStartedState, onMoveRequest: (at: Coordinate) -> Unit) {

    val maybeMakeMove = { tileContent: Player?, row: Row, column: Column ->
        if (tileContent == null && state.localPlayer == state.board.turn) {
            onMoveRequest(Coordinate(row, column))
        }
    }

    BoardView(state.board, onTileSelected = maybeMakeMove)
}

/**
 * Composable used to specify the [MainWindow] content when the application is in the [GameNotStartedState]
 */
@Composable
private fun GameNotStartedContent() = BoardView(Board(), onTileSelected = { _, _, _ -> })

/**
 * Sum type used to define the application's state.
 * @see [GameNotStartedState]
 * @see [GameNotStartedState]
 */
private sealed class ApplicationState

/**
 * Represents the application state when a game has not yet been started
 */
private object GameNotStartedState : ApplicationState()

/**
 * Represents the application state when a game has not yet been started
 */
private data class GameStartedState(val localPlayer: Player, val board: Board) : ApplicationState()
