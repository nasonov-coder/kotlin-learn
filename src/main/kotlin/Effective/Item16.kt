package Effective

/**
 * Properties should represent state, not behavior
 */
class Item16 {
}

val Int.ms: Int
    get() = this * 1_000
val Int.mc: Int
    get() = this * 1_000_000
fun main() {
    println(1.ms)
}