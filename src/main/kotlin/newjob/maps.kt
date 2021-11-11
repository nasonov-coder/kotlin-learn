package dev.gaodi.fileserver.newjob

import tests.pln
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val treeMap = TreeMap<String, String>()
    val hashMap = HashMap<Koks, String>()
    hashMap[Koks(1)] = "v1"
    hashMap[Koks(2)] = "v2"
    hashMap[Koks(3)] = "v3"
    hashMap[Koks(4)] = "v4"
    println(hashMap.entries.joinToString { "" + it.key.kek + "=" + it.value })
    val list = ArrayList<String>()
    list.add("123")
    list.add("123")
    list.add("124")

    list.toSet().pln()
    val kek = HashSet<String>(list)
}

class Koks(val kek: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Koks

        //if (kek != other.kek) return false

        return true
    }

    override fun hashCode(): Int {
        return kek % 2
    }
}