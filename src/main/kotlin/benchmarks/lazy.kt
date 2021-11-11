package dev.gaodi.fileserver.benchmarks

import dev.gaodi.fileserver.corutinse.benchmark.printTime
import kotlin.system.measureNanoTime

const val N = 10000000L
const val I = 10000000
var kek = 0L
val lol =  mutableListOf<Any>()
fun main() {
    measureNanoTime {
        repeat(I) {
            kek += Lazy().kek
        }
    }.printTime("lazy")
    measureNanoTime {
        repeat(I) {
            kek += NotLazy().kek
        }
    }.printTime("notLazy")
    measureNanoTime {
        repeat(I) {
            kek += Lazy().kek
        }
    }.printTime("lazy")
    measureNanoTime {
        repeat(I) {
            lol += Lazy()
        }
    }.printTime("lazy useless")
    measureNanoTime {
        repeat(I) {
            lol += NotLazy()
        }
    }.printTime("notLazy useless")
    measureNanoTime {
        repeat(I) {
            lol += Lazy()
        }
    }.printTime("lazy useless")
    println(kek)
}

class Lazy {
    val kek by lazy { N * N }
}
class NotLazy {
    val kek = N * N
}