package isel.leic.tds.tictactoe.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import isel.leic.tds.tictactoe.domain.*
import isel.leic.tds.tictactoe.storage.GamesRepository
import isel.leic.tds.tictactoe.ui.board.BoardView

private typealias StartGameAction = (GameId) -> Unit

/**
 * Composable that defines the application's main window. The application state is represented by the
 * [Game] closed hierarchy.
 *
 * @param repository        The games' repository
 * @param onCloseRequested  The function to be called when the user intends to close the window
 */
@Composable
fun MainWindow(repository: GamesRepository, onCloseRequested: () -> Unit) = Window(
    onCloseRequest = onCloseRequested,
    title = "Tic-Tac-Toe",
    state = WindowState(size = DpSize.Unspecified),
    resizable = false
) {
    val state = remember { mutableStateOf<Game>(GameNotStarted) }
    val currentState = state.value

    val startGameAction = remember { mutableStateOf<StartGameAction?>(null) }
    val currentStartGameAction = startGameAction.value

    fun startGame(id: GameId) {
        println("startGame")
        state.value = (currentState as GameNotStarted).start(repository, Player.CIRCLE, id)
    }

    fun joinGame(id: GameId) {
        println("joinGame")
        state.value = (currentState as GameNotStarted).start(repository, Player.CROSS, id)
    }

    MainWindowMenu(
        currentState,
        onStartRequested = { startGameAction.value = ::startGame },
        onJoinRequested = { startGameAction.value = ::joinGame },
        onRefreshRequested = { state.value = (currentState as GameStarted).refresh() },
        onForfeitRequested = {  } // TODO
    )

    when (currentState) {
        is GameNotStarted -> GameNotStartedContent()
        is GameStarted -> GameStartedContent(
            currentState,
            onMoveRequest = { at -> state.value = currentState.makeMove(at) },
        )
    }

    if (currentStartGameAction != null) {
        GetGameId(
            onGameIdEntered = { gameId -> currentStartGameAction.invoke(gameId); startGameAction.value = null },
            onCancel = { startGameAction.value = null }
        )
    }
}

/**
 * The [MainWindow]'s menu
 */
@Composable
private fun FrameWindowScope.MainWindowMenu(
    state: Game,
    onRefreshRequested: (GameStarted) -> Unit,
    onStartRequested: () -> Unit,
    onJoinRequested: () -> Unit,
    onForfeitRequested: () -> Unit
) = MenuBar {

    data class MenuState(val start: Boolean, val join: Boolean, val refresh: Boolean, val forfeit: Boolean)

    val menuState = MenuState(
        start = state is GameNotStarted,
        join = state is GameNotStarted,
        refresh = state is GameStarted && state.localPlayer != state.board.turn,
        forfeit = state is GameStarted && state.localPlayer == state.board.turn
    )

    Menu("Game") {
        Item("Start", enabled = menuState.start, onClick = onStartRequested)
        Item("Join", enabled = menuState.join, onClick = onJoinRequested)
    }
    Menu("Options") {
        Item("Refresh", enabled = menuState.refresh, onClick = { onRefreshRequested(state as GameStarted) })
        Item("Forfeit", enabled = menuState.forfeit, onClick = { })
    }
}

/**
 * Composable used to specify the [MainWindow] content when the application is in the [GameStarted]
 */
@Composable
private fun GameStartedContent(state: GameStarted, onMoveRequest: (at: Coordinate) -> Unit) {

    val maybeMakeMove = { tileContent: Player?, row: Row, column: Column ->
        if (tileContent == null && state.localPlayer == state.board.turn) {
            onMoveRequest(Coordinate(row, column))
        }
    }

    BoardView(state.board, onTileSelected = maybeMakeMove)
}

/**
 * Composable used to specify the [MainWindow] content when the application is in the [GameNotStarted]
 */
@Composable
private fun GameNotStartedContent() = BoardView(Board(), onTileSelected = { _, _, _ -> })
