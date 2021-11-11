package dev.gaodi.fileserver.corutinse.benchmark

import dev.gaodi.fileserver.netty.byteBuf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.nio.charset.Charset
import java.util.concurrent.Executors
import kotlin.system.measureNanoTime

const val REPEAT = 100000
const val THREADS = 1
const val MULTIPLIER = 1.05
const val RECALC = 3
//const val WAIT = 1000L
suspend fun main() {
//    println("wait time: ${WAIT*2}ns")
    val threadPool = Executors.newFixedThreadPool(THREADS)
    val scope = CoroutineScope(threadPool.asCoroutineDispatcher())

    println("repeats: ${REPEAT}")
    println("per op, ns;plain;coroutines;cor/plain;str.len")
    var str = "k"
    while (true) {
        getPlain(str)
        var plain = getPlain(str)
        var cor = getCor(scope, str)
        repeat(RECALC-1) {
            plain += getPlain(str)
            cor += getCor(scope, str)
        }

        println("${(plain+cor)/ RECALC / REPEAT / 2};${plain/1000};${cor/1000};${cor.toDouble()/plain};${str.length}")
        str += str.substring(0..((str.length * (MULTIPLIER-1)).toInt()))
        if(cor > 20_000_000_000)
            break
    }
    //    measureNanoTime {
//        val threads = (1..THREADS).map {
//            thread {
//                repeat(REPEAT / THREADS) {
//                    calc()
//                }
//            }
//        }
//        threads.forEach(Thread::join)
//    }.printTime("multithreaded")
//    measureNanoTime {
//            repeat(REPEAT / THREADS) {
//                repeat(THREADS) {
//                    scope.launch {
//                        calcSuspend()
//                    }
//                }
//            }
//
//    }.printTime("coroutines multithreaded")
//    measureNanoTime {
//        repeat(REPEAT) {
//            calc()
//        }
//    }.printTime("plain")
//    measureNanoTime {
//        repeat(REPEAT) {
//            scope.launch {
//                calcSuspend(str)
//            }.join()
//        }
//    }.printTime("coroutines singlethreaded")
//    measureNanoTime {
//        repeat(REPEAT) {
//            calc()
//        }
//    }.printTime("plain")
//    measureNanoTime {
//        repeat(REPEAT) {
//            scope.launch {
//                calcSuspend()
//            }.join()
//        }
//    }.printTime("coroutines singlethreaded")
//
//    measureNanoTime {
//        repeat(REPEAT) {
//            calc()
//        }
//    }.printTime("plain")
//    measureNanoTime {
//        repeat(REPEAT) {
//            scope.launch {
//                calcSuspend()
//            }.join()
//        }
//    }.printTime("coroutines singlethreaded")
}

 suspend fun getCor(scope: CoroutineScope, str: String): Long {
    return measureNanoTime {
        repeat(REPEAT) {
            scope.launch {
                calcSuspend(str)
            }.join()
        }
    }
}

 fun getPlain(str: String): Long {
    return measureNanoTime {
        repeat(REPEAT) {
            calc(str)
        }
    }
}
var ops = 0
fun doHeavyLifting(str: String): Boolean {
    return (str+"s").hashCode() == ops++

}
fun calc(str: String) {
    doHeavyLifting(str)
    doHeavyLifting(str)
}
suspend fun calcSuspend(str: String) {
    doHeavyLifting(str)
    delay(0)
    doHeavyLifting(str)
}

internal fun Long.printTime(msg: String) = this.also{ println("$msg: ${this/1_000_000}ms")}

//fun waitNano(nano: Long) {
//    var t = System.nanoTime()
//    t += nano
//    while (System.nanoTime() < t);
//}