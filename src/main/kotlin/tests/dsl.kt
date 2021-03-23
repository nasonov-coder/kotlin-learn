package dev.gaodi.fileserver.tests

fun main() {
    Dsl().kek { println(keks) }
    with(Dsl().some) {
    }
    Dsl().some.run {
        println(keks)
    }
}

class Dsl(val some: Some = Some()) {

    fun kek(k: Some.()->Unit): Unit {
        k(some)
    }

}
class Some {
    var keks: Int = 3
}