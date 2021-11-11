package dev.gaodi.fileserver.collections.map

import kotlin.reflect.KProperty


fun main() {
    val lol = (1..2).map {
        3..4
    }.flatten()
    println(lol)
}
