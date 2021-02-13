package B.Basics

import java.sql.Time
import kotlin.random.Random

fun main() {
    arrays()
}

fun `literals`() {
    val decimal: Int = 100_000
    val hexadecimal: Int = 0xA
    val binary: Int = 0b100
    val double1: Double = 123.5
    val double2: Double = 123.5e10
    val float: Float = 123.4f
    val decimalLong: Long = 100_000L
    val hexadecimalLong: Long = 0xAL
    val binaryLong: Long = 0b100L
    println("$decimal $hexadecimal $binary")
}
fun `literals 2`() {

    val b: Byte = 1 // OK, literals are checked statically
// val i: Int = b // ERROR
    val i1: Int = b.toInt()
}
fun `Division of integers`(){
    val x = 5 / 2
//println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
    println(x == 2)

    val y = 5L / 2
    println(y == 2L)

    //To return a floating-point type, explicitly convert one of the arguments to a floating-point type.
    val z = 5 / 2.toDouble()
    println(z == 2.5)
}
fun `primitive array`(){
    val a: Array<Int> // Integer[] a = null;
    val b: IntArray // int[] b = null;
}
fun `bool`() {
    val myTrue: Boolean = true // boolean
    val myFalse: Boolean = false //boolean
    val boolNull: Boolean? = null //Boolean

    println(myTrue || myFalse)
    println(myTrue && myFalse)
    println(!myTrue)
    println(!(boolNull?:false))
}
/**
 * On the JVM platform, numbers are stored as primitive types: int, double, and so on. Exceptions are cases when you create a nullable number reference such as Int? or use generics. In these cases numbers are boxed in Java clases Integer, Double, and so on.
 */
fun `jvm basic types check`() {
    val a: Int = 1 //int
    var b: Int = 2 //int
    var c: Int? = getIntOrNull() //Integer
    var d: Int? = 3//Integer
    var e: Int? = null//Integer
    val f: Int? = 3//Integer
    var g: Int = 4 //int

    println("$a, $b, $c, $d, $e, $f, ${g.toChar()}")
}
fun getIntOrNull(): Int? =
     if(Random(1).nextInt() % 2 == 0)
         1
    else
        null


fun `arrays`(){
    val ar = arrayOf(1, 2, 3)
    val arOfNulls: Array<Any?> = arrayOfNulls(3)
    println(ar)
    println(arOfNulls)
    println(ar[0])
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }
    var arr = IntArray(5) { it * 1 }
    val x = arrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    val y: IntArray = intArrayOf(1, 2, 3)
    y[0] = y[1] + y[2]
}