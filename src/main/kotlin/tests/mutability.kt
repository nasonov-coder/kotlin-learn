package tests
import kotlinx.coroutines.*


fun main() {
    `coroutine int`()
    `coroutine myClass`()
}
/**
 * prints 1
 * kotlin passes int by-value
 */
fun `coroutine int`() {
    var i = 1
    printWithDelay(i)
    Thread.sleep(500L)
    i = 2
    println("changed. new value: $i")
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}
/**
 * prints 2
 */
fun `coroutine myClass`() {
    val i = myClass(1)
    printWithDelay(i)
    Thread.sleep(500L)
    i.int = 2
    println("changed. new value: $i")
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

fun increment(int: Int) {
    // error: Val cannot be reassigned
    //int += 1
}
fun decrement(int: Int) {

}
fun printWithDelay(any: Any) {
    GlobalScope.launch { // launch a new coroutine in background and continue
        println("entered coroutine. waiting... value $any")
        Thread.sleep(1000L)
        println("exit coroutine. value $any")
    }
}
data class myClass(
    var int: Int
)