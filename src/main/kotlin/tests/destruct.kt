package dev.gaodi.fileserver.tests

import java.io.Serializable

fun main() {
    val (a,b,c) = ret()
}

fun ret() =
    des(1,"",3)

fun <A, B> des(first: A, second: B)
        = Pair(first, second)
fun <A, B, C> des(first: A, second: B, third: C)
        = Triple(first, second, third)
fun <A, B, C, D> des(first: A, second: B, third: C, fourth: D)
        = Quadruple(first, second, third, fourth)
fun <A, B, C, D, E> des(first: A, second: B, third: C, fourth: D, fifth: E)
        = Quintuple(first, second, third, fourth, fifth)


/**
 * Created by nalcalag on 09/02/2019.
 *
 * Represents a quartet of values
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Quadruple exhibits value semantics
 *
 * @param A type of the first value.
 * @param B type of the second value.
 * @param C type of the third value.
 * @param D type of the fourth value.
 * @property first First value.
 * @property second Second value.
 * @property third Third value.
 * @property fourth Fourth value.
 */
data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {

    /**
     * Returns string representation of the [Quadruple] including its [first], [second], [third] and [fourth] values.
     */
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

/**
 * Converts this quadruple into a list.
 */
fun <T> Quadruple<T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth)


/**
 * Created by nalcalag on 09/02/2019.
 *
 * Represents a quartet of values
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Quadruple exhibits value semantics
 *
 * @param A type of the first value.
 * @param B type of the second value.
 * @param C type of the third value.
 * @param D type of the fourth value.
 * @param E type of the fifth value.
 * @property first First value.
 * @property second Second value.
 * @property third Third value.
 * @property fourth Fourth value.
 * @property fifth Fifth value.
 */
data class Quintuple<out A, out B, out C, out D, out E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
) : Serializable {

    /**
     * Returns string representation of the [Quintuple] including its [first], [second], [third], [fourth] and [fifth] values.
     */
    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}