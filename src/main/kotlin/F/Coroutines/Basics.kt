package F.Coroutines

import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.suspendCoroutine

fun main() {
//    `susp cor`()
}

fun `my first coroutine`() {
    val job = GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }

    job.cancel()
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}
suspend fun `susp cor`(){
    val kek: String = suspendCoroutine { cont ->
        cont.resumeWith(Result.success("kek"))
    }
    println(kek)
}
fun `file reading`(){
    val f = File("/Users/dmitry/Desktop/out.txt")
}