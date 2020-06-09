package net.ruhamaa.mobile.data

import net.ruhamaa.mobile.data.Result.Success
import java.lang.Error
import java.lang.RuntimeException

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
//    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
//            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Success && data != null

fun <T> T.toSuccess() = Success(this)

fun Result.Error.toErrorMessage() = Message.Raw(exception.message ?: "Unknown error")

fun <T> Result<T>.getOrNull() = if (this is Success) data else null
fun <T> Result<T>.require() = if (this is Success) data else throw RuntimeException("Required result not available")

fun <T, R> Result<T>.map(transformer: (T) -> R): Result<R> = when (this) {
    is Success -> transformer.invoke(data).toSuccess()
    is Result.Error -> Result.Error(exception)
}