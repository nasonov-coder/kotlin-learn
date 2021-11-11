package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tests.pln
import tests.sout

suspend fun main(): Unit = coroutineScope {
    suspendCoroutineWithTimeout<Kek>(1000) {
        FakeServer.resumeLater(1, it, Kek(""))
    }.pln()
    suspendCoroutineWithTimeout<Kek>(1000) {
        FakeServer.resumeLater(100, it, Kek(""))
    }.pln()

}
data class Kek(
    val lol: String
)