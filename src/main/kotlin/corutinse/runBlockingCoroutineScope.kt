package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.*

suspend fun main() {
    println("runBlocking:")
    rb()
    println("coroutineScope:")
    cs()
}

fun rb() = runBlocking {
    delay(1)
    launch {
//        Thread.sleep(1000)
        delay(10)
        println("1")
    }
    println("1.1")

    launch {
        delay(10)
        println("2")
    }
    println("2.1")

    launch {
        delay(10)
        println("3")
    }
}

suspend fun cs() = coroutineScope {
    launch {
        Thread.sleep(1000)
        delay(1)
        println("1")
    }
    println("1.1")
    launch {
        delay(1)
        println("2")
    }
    println("2.1")

    launch {
        delay(1)
        println("3")
    }
}
