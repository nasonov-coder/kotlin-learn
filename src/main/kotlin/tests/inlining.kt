package dev.gaodi.fileserver.tests

import tests.sout

fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}
fun foo() {
    ordinaryFunction {
       // return // ERROR: cannot make `foo` return here
    }
}
fun main() {
    foo()
    val k = ""
    f {
        k.sout()
    }

}
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros
    }
    return false
}

inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}