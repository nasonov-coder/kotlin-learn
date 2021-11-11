package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.coroutineContext

suspend fun main(): Unit = runBlocking {
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        log("A done")
    }
    launch {
        channel.send("B1")
        log("B done")
    }
    this.
    launch {
        repeat(3) {
            val x = channel.receive()
            log(x)
        }
    }

}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
