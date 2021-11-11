package dev.gaodi.fileserver.corutinse

import kotlinx.coroutines.*

/**
 *
 */
fun main() = runBlocking(/*Dispatchers.Default*/) {
    launch {
        heavyCalc()
        4.p
    }
    1.p
    launch {
        delay(1)
        6.p
    }
    2.p
    coroutineScope {
        launch {
            5.p
        }

        3.p
    }
    7.p

    launch {
        10.p
    }
    8.p

    coroutineScope {
        launch {
            11.p
        }

        9.p
    }
    12.p
    launch {
        14.p
    }

    13.p
}
