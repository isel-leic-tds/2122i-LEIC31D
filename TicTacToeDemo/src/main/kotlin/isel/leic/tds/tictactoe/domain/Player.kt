package isel.leic.tds.tictactoe.domain

/**
 * Enumeration type used to represent the game's players.
 */
enum class Player {
    CIRCLE, CROSS;

    /**
     * The other player
     */
    val other: Player
        get() = if (this == CIRCLE) CROSS else CIRCLE
}
