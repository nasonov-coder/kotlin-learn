package dev.gaodi.fileserver.newjob

import dev.gaodi.fileserver.benchmarks.I
import dev.gaodi.fileserver.benchmarks.Lazy
import dev.gaodi.fileserver.benchmarks.kek
import dev.gaodi.fileserver.corutinse.benchmark.printTime
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureNanoTime

fun main() {

    val list = (0..1_000_000).map { it }.shuffled()
    val treeMap = TreeMap<Int, Unit>()
    val hashMap = HashMap<Int, Unit>()
    measureNanoTime {
       treeMap.fill(list)
    }.printTime("tree fill")
    measureNanoTime {
        hashMap.fill(list)
    }.printTime("hash fill")
    measureNanoTime {
        treeMap.fetch(list)
    }.printTime("tree get")
    measureNanoTime {
        hashMap.fetch(list)
    }.printTime("hash get")
    measureNanoTime {
        treeMap.fetch(list)
    }.printTime("tree get")
}

fun <K, V> Map<K, V>.fetch(list: List<K>) {
    list.shuffled().forEach {
        this[it]
    }
}
fun <K> MutableMap<K, Unit>.fill(list: List<K>) {
    list.forEach {
        this[it] = Unit
    }
}