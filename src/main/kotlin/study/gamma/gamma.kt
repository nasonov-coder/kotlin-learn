package study.gamma

import java.lang.Exception

val letters = ('А'..'Я').toMutableList().apply {
    add(' ')
}.mapIndexed { i, v -> Letter( i+1, v)}
data class Letter(
    val num: Int,
    val char: Char
)
fun List<Letter>.find(num: Int) = find { it.num == (num - 1) % 33 + 1 }  ?: throw Exception("wrong number: $num")
fun List<Letter>.find(char: Char) = find { it.char == char }  ?: throw Exception("wrong letter: $char")
fun List<Int>.print() = map { num -> letters.find(num).char }.joinToString("")

const val T0 = 7
const val A = 9
const val M = 64

fun main() {
    gamma(4,4, "насонов дмитрий")
}
fun gamma(digit1: Int, digit2: Int, name: String) {
    with(0..10) {
        require(digit1 in this)
        require(digit2 in this)
    }
    val C = digit1 + digit2
    println("шифруем строку: $name")
    println("1. T0=$T0, A=$A, C=$C, M=$M")
    val nameNumbered = name.map { letters.find(it.toUpperCase()).num }
    println("2. ${nameNumbered.joinToString()}")


    val cipherGamma = mutableListOf(T0)
    for( i in 1..nameNumbered.size) {
        cipherGamma += (cipherGamma[i-1] * A + C) % M
    }
    println("2. ${cipherGamma.joinToString()}")
    println("3. ${nameNumbered.joinToString { it.toString(2) }}")
    println("   ${cipherGamma.joinToString { it.toString(2) }}")
    val cipher = applyGamma(nameNumbered, cipherGamma)
    println("4. ${cipher.joinToString { it.toString(2) }}")
    println("5. ${cipher.joinToString()}")
    println("   ${cipher.print()}")

    print("6. ${applyGamma(cipher, cipherGamma).print()}")

}

fun applyGamma(numbers: List<Int>, cipherGamma: List<Int>): List<Int> {
    return numbers.mapIndexed{ k, v ->
        v xor cipherGamma[k]
    }
}