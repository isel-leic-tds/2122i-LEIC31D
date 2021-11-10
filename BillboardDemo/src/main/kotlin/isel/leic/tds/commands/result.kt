package isel.leic.tds.commands

/**
 * Sum type used to represent the execution result of the existing commands
 */
sealed class Result

/**
 * Result produced when the command execution determines that the application should terminate.
 * See https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview
 */
object ExitResult : Result()

/**
 * Result produced when the command execution yields a value
 */
class ValueResult<T>(val data: T) : Result()
