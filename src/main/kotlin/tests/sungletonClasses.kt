package dev.gaodi.fileserver.tests

import tests.sout

fun main() {
    val list = mutableListOf<Any>()
    for( i in 1..2)
        list += object {

        }
    list += object {

    }

    list.forEach {
        it::class.java.sout()
    }
    val kek = object {
        val lol = 123
    }
    kek.lol
}
class Sungleton {
    val kek = 123
}