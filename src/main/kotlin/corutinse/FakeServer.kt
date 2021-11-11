package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object FakeServer {
    fun <T> resumeLater(delay: Long, cont:Continuation<T>, value: T? = null) {
        GlobalScope.launch {
            delay(delay)
            if( value == null)
                cont.resumeWithException(Exception("null value"))
            else
                cont.resume(value)
        }
    }
}