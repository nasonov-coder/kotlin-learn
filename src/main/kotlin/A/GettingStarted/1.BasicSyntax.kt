package A.GettingStarted

import java.text.SimpleDateFormat
import java.util.*

fun main() {

    SimpleDateFormat("dd.MM.yyyy").format(Date()).let(::println)

}
fun greet() {
    println("Ur name? ")
    val s = readLine()
    println("Hello, $s")
}
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }

    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}
fun loops() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    val map = hashMapOf(
        "kek".to("lol"),
        "kok" to "lok"
    )
    for (i in map)
        println(i.key + " " + i.value)
}
fun describe(obj: Any) =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        else       -> "Unknown"
    }
fun ranges() {
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }


    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    for (x in 1..10) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
    val range: IntRange = 1..10
    val progression: IntProgression = 1..10 step 2
    val what: IntRange = 1 until list.size
    println("is IntRanges equal: ${range == what}")
    fun kek(iter: Iterable<Int>) {
        for (i in iter)
            println(i)
    }
}
fun collections() {
    val items = setOf("apple", "banana", "kiwifruit", "orange")
    when {
        "watermelon" in items -> println("no way")
        "orange" in items -> println("will be printed")
        "apple" in items -> println("this will not")
    }

    // lambda:
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun `lambda as parameter`() {
    lambdaTest { true }
    lambdaTest("d") { true }
    lambdaTest("d", { true })
    lambdaTest() { true }
    lambdaTest(block = { true })
}
fun lambdaTest(
    s: String = "",
    block: ()-> Boolean,
) {
    block.invoke()
}

fun `iterating map with destructor`() {
    val map = mapOf(1 to "2")
    for (entry in map) {
        var (key, value) = entry
        key = entry.component1()
        value = entry.component2()
        // do something with the key and the value
    }
    for ((key, value) in map) {
        // do something with the key and the value
    }
    map.mapValues { (key, value) -> "$value!" }
    map.mapValues { (_, value) -> "$value!" }
}