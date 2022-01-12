package isel.leic.tds.tictactoe.storage

/**
 * Specifies the contract for repositories of Tic-Tac-Toe games
 * - see Repository pattern: https://www.martinfowler.com/eaaCatalog/repository.html
 */
interface GamesRepository {

    /**
     * Creates a game and returns its corresponding [SharedGameState] representation
     * @param id      the game identifier
     * @param state   the game's initial state
     */
    suspend fun createGame(id: String, state: SharedGameState): Boolean

    /**
     * Updates the game with the given identifier, creating it if it does not exist.
     * @param id    the game identifier
     * @param state the game state
     */
    suspend fun updateOngoingGame(id: String, state: SharedGameState): Boolean

    /**
     * Gets the game with the given identifier, or null if it doesn't exist.
     * @param id    the game identifier
     */
    suspend fun getOngoingGame(id: String): SharedGameState?
}