package dev.gaodi.fileserver.study


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

fun main() {
    rsa(19, 13, "насонов дмитрий")
}

fun rsa(p: Int, q: Int, name: String) {
    println("1. q: $q, p: $p")
    val nameNumbered = name.map { letters.find(it.toUpperCase()).num }
    println("2. ${nameNumbered.joinToString()}")

    val N = q * p
    println("3. n: $N")
    val fn = (p - 1) * (q - 1)
    println("4. f(n): $fn")
    val range = (2..fn).filter { it != p && it != q }.shuffled()
    val Kotk = range.find { gcd(fn, it) == 1 } ?: error("no Kotk")
    println("5. Kotk : $Kotk")
    val Ksek = (Kotk.toBigInteger().pow(fn-1) % fn.toBigInteger()).toInt()
    println("6. Ксек: $Ksek")
    val cipher = cipher(nameNumbered, Kotk, N)
    println("7. ${cipher.joinToString()}")
    println("   ${cipher.print()}")
    val decipher = cipher(cipher, Ksek, N)
    println("8. ${decipher.print()}")



}

fun cipher(numbers: List<Int>, key: Int, N: Int): List<Int>
    = numbers.map { (it.toBigInteger().pow(key) % N.toBigInteger()).toInt() }

fun gcd(a: Int, b: Int): Int {
    var a = a
    var b = b
    while (b != 0) {
        val tmp = a % b
        a = b
        b = tmp
    }
    return a
}