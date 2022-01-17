package isel.leic.tds

/**
 * Sum-type for representing command execution results. Execution results are either [ActualResult] or [Exit]
 */
sealed class CommandResult

/**
 * Represents possible results of command execution. A command execution is either successful, and therefore yields a
 * result, or a failure, in which case produces an exception
 */
class ActualResult<T>(private val value: Any?) : CommandResult() {

    private class Failure(val exception: Throwable)

    @Suppress("UNCHECKED_CAST")
    private fun valueAsT() = value as T

    companion object {
        fun <T> failure(exception: Throwable): ActualResult<T> = ActualResult(Failure(exception))
        fun <T> success(result: T): ActualResult<T> = ActualResult(result)
    }

    /**
     * Checks whether this instance corresponds to a failure, that is, an execution that produced an exception
     * @return true if the execution failed, false otherwise
     */
    fun isFailure() = value is Failure

    /**
     * Checks whether this instance corresponds to a successful execution or not, that is, an execution that produced
     * an actual result.
     * @return true if the execution succeeded, false otherwise
     */
    fun isSuccess() = value !is Failure

    /**
     * Performs the given [action] on the actual result if this instance represents [success][ActualResult.isSuccess].
     * @param action    the action to be performed
     * @return this [ActualResult] instance, unchanged
     */
    fun onSuccess(action: (value: T) -> Unit): ActualResult<T> {
        if (isSuccess()) action(valueAsT())
        return this
    }

    /**
     * Performs the given [action] on the actual result if this instance represents [failure][ActualResult.isFailure].
     * @param [action]  the action to be performed
     * @return this [ActualResult] instance, unchanged
     */
    fun onFailure(action: (exception: Throwable) -> Unit): ActualResult<T> {
        if (isFailure()) { action((this.value as Failure).exception) }
        return this
    }

    /**
     * Gets the command result, or null if the execution result was a failure
     */
    fun getResultOrNull(): T? = if (isFailure()) null else valueAsT()
}

/**
 * Singleton for representing the execution of a command that terminates the application
 */
object Exit : CommandResult()

/**
 * Represents procedures that can be used to render command execution results
 */
typealias ResultRenderer<T> = (ActualResult<T>) -> Unit

/**
 * Helper function that executes the given code block (i.e. [producer]) and wraps its result in an [ActualResult]
 * instance
 * @param   producer    the result producer
 * @return  an [ActualResult] representing [success][ActualResult.isSuccess] if [producer] produced a result, an
 * [ActualResult] representing [failure][ActualResult.isFailure] if it produced an exception
 */
fun <T> tryExecute(producer: () -> T ): ActualResult<T> =
    try {
        val res :T = producer()
        ActualResult.success(res)
    }
    catch (e: Exception) {
        ActualResult.failure(e)
    }

