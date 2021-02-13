package tests

import kotlin.math.pow


fun main() {
    val kekList = listOf(Kek(1), Kek(2), Kek(3))
    println(kekList.map( Kek::getIncremented ))
    kekList.forEach(Kek::pow2)
    kekList.let(::println)
    kekList.forEach{ it.pow(0) }
    kekList.let(::println)

}
data class Kek(var i:Int) {
    fun plus(i: Int) {

    }
    fun getIncremented() = i + 1
    fun pow2() {
        i *= i
    }
    fun pow(int: Int) {
        i = int.toDouble().pow(int).toInt()
    }
}