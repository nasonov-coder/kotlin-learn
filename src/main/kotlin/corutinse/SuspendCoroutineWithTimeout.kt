package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit
import kotlin.coroutines.*

suspend inline fun <T> suspendCoroutineWithTimeout(
    timeout: Long, unit: TimeUnit = TimeUnit.MILLISECONDS,
    crossinline block: (Continuation<T>) -> Unit
) = withTimeout(timeout) {
    suspendCoroutine(block = block)
}