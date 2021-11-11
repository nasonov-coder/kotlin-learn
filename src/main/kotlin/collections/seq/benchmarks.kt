package dev.gaodi.fileserver.collections.seq

import kotlin.system.measureNanoTime

fun main() {
    val sequence = generateSequence(1) { it + 1 }.take(500_000_000) // лишний ноль
    println("created")

//    val list = sequence.toList()
    println("to listed")
//    println("List Map Sum "
//            + measureNanoTime { list.map { it * 2 }.sum() }.pretty())
    println("Sequence Map Sum "
            + measureNanoTime { sequence.sumOf { it * 2 } }.pretty())
    println("Sequence Map Sum "
            + measureNanoTime { sequence.map { it * 2 }.sum() }.pretty())

//    println("List Map Average "
//            + measureNanoTime { list.map { it * 2 }.average() }.pretty())
    println("Sequence Map Average "
            + measureNanoTime { sequence.map { it * 2 }.average() }.pretty())
}

private fun Long.pretty() = this/1_000_000