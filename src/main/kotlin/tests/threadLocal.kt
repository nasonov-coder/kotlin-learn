package dev.gaodi.fileserver.tests

import tests.sout
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.getOrSet
import kotlin.concurrent.thread

fun main() {
    thread {
        println("1")
        println("value: " + local.get().count)
        println("2")

    }
    thread {
        println("3")
        println("value: " + local.get().count)
        println("4")

    }
}

val local: ThreadLocal<Counter> = ThreadLocal.withInitial { Counter() }

class Counter {
    val count = counter
    init {
        counter++
        "created! $count".sout()
    }

    companion object {
        var counter: Int = 0
    }
}