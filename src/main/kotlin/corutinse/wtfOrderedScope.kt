package dev.gaodi.fileserver.corutinse

import B.Basics.`for loop`
import kotlinx.coroutines.*

/**
 * в coroutineScope все launch запускаются почти сразу же,
 * а coroutineScope это runBlocking в мире корутин
 */
suspend fun main() = coroutineScope {
    val that = this

    launch {
        2.p
    }
    1.p
    heavyCalc()

    3.p
    launch {
        heavyCalc()
        15.p
    }
    4.p
    coroutineScope {
        launch {
            6.p
        }

        5.p
    }
    7.p

    launch {
        9.p
    }
    8.p

    coroutineScope {
        launch {
            11.p
        }

        10.p
    }
    12.p
    launch {
        14.p
    }

    13.p
}
var k = 1
fun heavyCalc() {
    for ( i in 1..10_000_000_00)
        k = k*2 - i
}
var i = 1
val Number.p: Unit
    get() = /*if(this != i) error("wrong, $this is set but $i real") else*/ println("$this - ${i++}")
