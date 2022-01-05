package isel.leic.tds.tictactoe.domain

import isel.leic.tds.tictactoe.storage.GamesRepository
import isel.leic.tds.tictactoe.storage.toBoard
import isel.leic.tds.tictactoe.storage.toSharedGameState
import java.lang.IllegalStateException

/**
 * Sum type used to define the game's state.
 * @see [GameNotStarted]
 * @see [GameStarted]
 */
sealed class Game

/**
 * Singleton that represents games that have not yet been started.
 */
object GameNotStarted : Game() {

    /**
     * Starts a game. The game's plays are published to the given repository.
     *
     * @param repository    the repository to where the game's plays are to be published
     * @param localPlayer   the local player
     * @param gameId        the game identifier
     */
    fun start(repository: GamesRepository, localPlayer: Player, gameId: GameId) =
        GameStarted(repository, gameId, localPlayer, Board())
}

/**
 * Represents started games, whose state is published to the given repository.
 *
 * @param repository    the repository to where the game's state is published (and fetched from)
 * @param localPlayer   the player playing locally
 * @param board         the game board
 */
data class GameStarted(
    private val repository: GamesRepository,
    private val id: GameId,
    val localPlayer: Player,
    val board: Board
) : Game() {

    /**
     * Checks whether it's the local player turn to play
     */
    fun isLocalPlayerTurn() = localPlayer == board.turn

    /**
     * Makes a move, if it's the local player turn.
     * @param at    the coordinates of the play to be made
     * @return the new [GameStarted] instance
     * @throws IllegalStateException if it's not the local player turn to play
     */
    fun makeMove(at: Coordinate) : GameStarted {
        val newState = copy(board = board.makeMove(at))
        repository.updateOngoingGame(id.toString(), newState.board.toSharedGameState())
        return newState
    }

    /**
     * Creates a new instance from the data published to the repository
     */
    fun refresh(): GameStarted {
        val game = repository.getOngoingGame(id.toString())
        return if (game != null) {
            copy(board = game.toBoard())
        } else throw UnreachableSharedGameException(NullPointerException())
    }
}

/**
 * Represents game identifiers.
 * @property value the game identifier (cannot be a string comprised of only whitespace characters).
 */
data class GameId(val value: String) {
    init {
        require(isValidGameIdentifier(value))
    }

    override fun toString() = value
}

/**
 * Checks whether the given string is a valid game identifier.
 * @param   id  the string to be checked
 * @return  true if [id] can be used as game identifier, false otherwise
 */
private fun isValidGameIdentifier(id: String) = id.isNotBlank()

/**
 * Extension function that converts this string to a [GameId] instance.
 * @return  the [GameId] instance or null if this string is not a valid game identifier.
 */
fun String.toGameIdOrNull() = if (isValidGameIdentifier(this)) GameId(this) else null


/**
 * Exception used to represent errors while trying to reach the shared game state
 *
 * @param cause the error's root cause
 */
class UnreachableSharedGameException(cause: Throwable) : Exception(cause)
