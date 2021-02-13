package tests

fun paint(x: Aclass) {
    x.paint()
}
fun main() {

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