package B.Basics

import kotlin.system.measureNanoTime

fun main() {
    `loops benchmark`()
}
fun `for loop`() {
    for( k: Long in Coll())
        print(k)
    for (i in arrayListOf(1)) // look for iterator in ArrayList
    Coll().iterator()
}
class Coll {
     operator fun iterator() = Iter()
}
class Iter {
    operator fun next() = 1L
    operator fun hasNext() = false
}
/**
 * for and while have similar results
 */
fun `loops benchmark`() {
    for (j in 1..10) {
        ("for1:  " + measureNanoTime {
            for (i in 0..100_000_000)
                calc(i * j)
        }).run(::println)
        ("while: " + measureNanoTime {
            var i = 0
            while( i < 100_000_000) {
                i++
                calc(i * j)
            }
        }).run(::println)
        ("for2:  " + measureNanoTime {
            for (i in 0..100_000_000)
                calc(i * j)
        }).run(::println)
    }
}
fun calc(i: Int) {
    calco = i * 2L
}
var calco: Long = 0