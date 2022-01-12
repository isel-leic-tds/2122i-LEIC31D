package isel.leic.tds.tictactoe.storage

import com.mongodb.client.MongoDatabase
import isel.leic.tds.tictactoe.storage.mongodb.createDocument
import isel.leic.tds.tictactoe.storage.mongodb.getCollectionWithId
import isel.leic.tds.tictactoe.storage.mongodb.getDocument
import isel.leic.tds.tictactoe.storage.mongodb.updateDocument

private const val ON_GOING_GAMES_ROOT = "ongoing"

/**
 * Implementation of a [GamesRepository] backed by a MongoDB instance.
 * TODO: handle errors
 */
class MongoDbGamesRepository(private val db: MongoDatabase) : GamesRepository {

    /**
     * Creates a game and returns its corresponding [SharedGameState] representation
     * @param id      the game identifier
     * @param state   the game's initial state
     */
    override suspend fun createGame(id: String, state: SharedGameState): Boolean {
        return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).createDocument(GameInfo(id, state))
    }

    /**
     * Updates on the repository the entry with the given identifier, creating it if it does not exist.
     * @param id    the game identifier
     * @param state the game state
     */
    override suspend fun updateOngoingGame(id: String, state: SharedGameState): Boolean {
        return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).updateDocument(GameInfo(id, state))
    }

    /**
     * Gets the game with the given identifier, or null if it doesn't exist.
     * @param id    the game identifier
     */
    override suspend fun getOngoingGame(id: String): SharedGameState? {
        return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.state
    }
}

/**
 * Defines the contents of documents bearing game state information
 */
private data class GameInfo(val _id: String, val state: SharedGameState)

