package isel.leic.tds.tictactoe.domain

/**
 * Represents a Tic-Tac-Toe board. Instances are immutable.
 *
 * @property side   The number of tiles per board side
 * @property turn   The next player to move, or null if the game has already ended
 */
data class Board internal constructor(
    val side: Int = TIC_TAC_TOE_SIDE,
    val turn: Player? = Player.CIRCLE,
    private val board: List<Player?> = buildEmptyBoard()
) {
    companion object {
        operator fun invoke() = Board()
    }

    operator fun get(row: Row, column: Column): Player? = this[Coordinate(row, column)]

    operator fun get(coordinate: Coordinate): Player? = getMove(coordinate)

    /**
     * Gets the move at the given coordinates.
     */
    fun getMove(at: Coordinate): Player? = board[at.toIndex()]

    /**
     * Makes a move at the given coordinates and returns the new board instance.
     *
     * @param at    the board's coordinate
     * @throws IllegalArgumentException if the position is already occupied
     * @throws IllegalStateException if the game has already ended
     * @return the new board instance
     */
    fun makeMove(at: Coordinate): Board {
        require(getMove(at) == null)
        checkNotNull(turn)
        return Board(
            turn = turn.other,
            board = board.mapIndexed { index, player ->
                if (index == at.toIndex()) turn
                else player
            }
        )
    }

    /**
     * Converts the board to a list of player moves.
     *
     * @return the list of player moves. The list size is always TIC_TAC_TOE_TILE_COUNT and empty board tiles are
     * represented with null.
     */
    fun toList(): List<Player?> = board
}

/**
 * Helper function used to build an empty Tic-Tac-Toe board
 */
private fun buildEmptyBoard(): List<Player?> =
    List(size = TIC_TAC_TOE_TILE_COUNT, init = { null })

