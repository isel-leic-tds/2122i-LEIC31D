package isel.leic.tds.tictactoe.storage

import isel.leic.tds.tictactoe.domain.Board
import isel.leic.tds.tictactoe.domain.Player
import isel.leic.tds.tictactoe.domain.TIC_TAC_TOE_TILE_COUNT

/**
 * Defines Tic-tac-toe game state, as stored in the shared repository.
 */
data class SharedGameState(val turn: Player?, val moves: String)

/**
 * Extension function that maps this Board instance to its corresponding [SharedGameState] representation
 */
fun Board.toSharedGameState(): SharedGameState {
    val moves = toList().map {
        when(it) {
            null -> ' '
            Player.CROSS -> 'X'
            Player.CIRCLE -> 'O'
        }
    }.joinToString(separator = "")
    return SharedGameState(turn = this.turn, moves = moves)
}

/**
 * Extension function that parses this String instance to the corresponding list of moves.
 *
 * @return the list of moves decoded from this string.
 */
fun String.toMovesList(): List<Player?> {
    require(this.length == TIC_TAC_TOE_TILE_COUNT)
    return this.map {
        when(it) {
            'X' -> Player.CROSS
            'O' -> Player.CIRCLE
            else -> null
        }
    }
}

/**
 * Extension function that creates a [Board] instance from this [SharedGameState].
 *
 * @return the list of moves decoded from this string.
 */
fun SharedGameState.toBoard() = Board(turn = turn, board = moves.toMovesList())