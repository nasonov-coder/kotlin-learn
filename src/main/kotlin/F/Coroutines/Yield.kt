package dev.gaodi.fileserver.F.Coroutines

import dev.gaodi.fileserver.corutinse.p
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main():Unit = runBlocking {
    launch {
        1.p
    }
    launch {
        yield()
        3.p
    }
    launch {
        2.p
    }
}