package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *  https://stackoverflow.com/a/59152712
 */
fun main() = runBlocking {
    launch {
        println("1")
    }
    println("1.5")
    coroutineScope {
        launch {
            println("2")
        }

        println("3")
    }
    println("3.5")
    coroutineScope {
        launch {
            println("4")
        }

        println("5")
    }
    println("5.5")
    launch {
        println("6")
    }
    for (i in 7..100) {
        println(i.toString())
    }

    println("101")
}
