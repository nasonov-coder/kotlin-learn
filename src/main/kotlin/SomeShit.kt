package dev.gaodi.fileserver

import tests.sout

fun main() {
    calc(632817)
    calc(99437)
    calc(99436)
}

fun calc(num: Int) {
    val S: Int = num.toString().fold(0) {a, b ->  a+b.toString().toInt()}
    print("$num: sum - $S, var - ")
    println((S mod 4)*10+ (S mod 10) + 1)
}

infix fun Int.mod(b: Int): Int = (this % b)

