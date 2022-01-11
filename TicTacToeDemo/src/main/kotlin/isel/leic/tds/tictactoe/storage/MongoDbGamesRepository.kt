package isel.leic.tds.tictactoe.storage

import com.mongodb.client.MongoDatabase
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
     * Updates on the repository the entry with the given identifier, creating it if it does not exist.
     * @param id    the game identifier
     * @param state the game state
     */
    override fun updateOngoingGame(id: String, state: SharedGameState): Boolean {
        Thread.sleep(3000)
        return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).updateDocument(GameInfo(id, state))
    }

    /**
     * Gets the game with the given identifier, or null if it doesn't exist.
     * @param id    the game identifier
     */
    override fun getOngoingGame(id: String): SharedGameState? {
        Thread.sleep(3000)
        return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.state
    }
}


/**
 * Defines the contents of documents bearing game state information
 */
private data class GameInfo(val _id: String, val state: SharedGameState)

