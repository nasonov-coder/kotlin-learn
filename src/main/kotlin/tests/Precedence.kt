package dev.gaodi.fileserver.tests

class Precedence {
    operator fun String.unaryMinus(): Kek {
        return Kek()
    }
    fun test() {
        (-"")()
    }

}
class Kek {
    operator fun invoke() {
        println("invoke")
    }
}

fun main() {
    Precedence().test()
}