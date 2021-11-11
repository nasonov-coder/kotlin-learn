package dev.gaodi.fileserver.classes

import dev.gaodi.fileserver.corutinse.benchmark.printTime
import kotlin.system.measureNanoTime

sealed class AB(val isA: Boolean)
class A: AB(true)
class B: AB(false)

fun main() {
    val map = (1..10000000).mapIndexed { index, i ->
        if( i%2 ==0) A() else B()
    }
    measureNanoTime {
        map.forEach {
            when(it) {
                is A -> doSmth()
                is B -> doSmth()
            }
        }
    }.printTime("is")
    measureNanoTime {
        map.forEach {
            if (it.isA) {
               doSmth()
            } else {
                doSmth()
            }
        }
    }.printTime("field")
    measureNanoTime {
        map.forEach {
            when(it) {
                is A -> doSmth()
                is B -> doSmth()
            }
        }
    }.printTime("is")
}
fun doSmth() {}