package isel.leic.tds.tictactoe.storage

/**
 * Represents the supported execution modes:
 *  LOCAL   - The database server is running locally
 *  REMOTE  - The database server is running remotely
 */
enum class DbMode { LOCAL, REMOTE }

/**
 * Represents the information required to connect to the database.
 *
 * @property mode               the DB execution mode (local or remote)
 * @property dbName             the database name
 * @property connectionString   the connection string to be used, or null if the DB is running locally
 */
data class DBConnectionInfo(
    val mode: DbMode,
    val dbName: String,
    val connectionString: String? = null,
)

/**
 * Environment variables
 */
private const val ENV_DB_NAME = "MONGO_DB_NAME"
private const val ENV_DB_CONNECTION = "MONGO_DB_CONNECTION"

/**
 * Gets the information to connect to the database.
 * @return the DB connection information
 */
fun getDBConnectionInfo(): DBConnectionInfo {
    val dbName = System.getenv(ENV_DB_NAME)
    requireNotNull(dbName) {
        "You must specify the environment variable $ENV_DB_NAME"
    }

    val connectionString = System.getenv(ENV_DB_CONNECTION)
    return DBConnectionInfo(
        if(connectionString != null) DbMode.REMOTE else DbMode.LOCAL,
        dbName,
        connectionString
    )
}
