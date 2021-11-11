package dev.gaodi.fileserver.tests

import tests.pln
import kotlin.Result

fun main() {
    kek().handle { e: Exception1 -> error("1") }.handle { e: Exception2 -> error("2") }.getOrThrow().pln("done")
    kek2().handle { e: Exception1 -> error("1") }.getOrThrow().pln("done")
}

fun kek(): Result<String> = Result.success("sd")
fun kek2(): Result<String> = Result.failure(Exception3())

class Exception1: Exception()
class Exception2: Exception()
class Exception3: Exception()
inline fun <reified K: Throwable, T> Result<T>.handle(block: (K)->Nothing): Result<T> {
    val e = exceptionOrNull()
    if(e != null && e is K) {
        block(e)
    }
    return this
}