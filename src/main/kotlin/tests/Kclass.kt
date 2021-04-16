package tests

import java.text.SimpleDateFormat
import java.util.*

fun paint(x: Aclass) {
    x.paint()
}
fun main() {
    getDateTime().sout()
    val b = Bclass()
    paint(b)
    val c: Bclass = b.apply {
        paint()
    }
}
class Bclass : Aclass() {
    override fun paint() {
        println("bpaint")
    }

}
 open class Aclass {
    fun setPosition(x: Int) {}
    open fun paint() {
        println("apaint")
    }
}
val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    .apply { timeZone = TimeZone.getTimeZone("GMT+3:00") }
private fun getDateTime(): String? {
    try {
        return dateFormat.format(Date())
    } catch (e: Exception) {
        return e.toString()
    }
}