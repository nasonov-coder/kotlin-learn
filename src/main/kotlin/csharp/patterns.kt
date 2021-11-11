package dev.gaodi.fileserver.csharp

fun main() {
    println(isLetter('c'))
    println(isLetter('X'))
    println(isLetter('2'))
//    12.switch<Int, Long> {
//        case(this.less(123)) {
//            ""
//        }
//    }

}
fun <T, K> T.switch(block: Switch<T, K>.()->Unit) {

}
typealias SwitchStatement<K> = (Boolean)->K
class Switch<T, K> {
    fun case(boolean: Boolean, block: SwitchStatement<K>) {

    }
}
fun isLetter(char: Char) = char.check {
    inn('a'..'z') || inn('A'..'Z')
}


fun <T> T.check(block: T.()->Boolean) =
    block(this)

fun <T> Comparable<T>.less(that: T) = compareTo(that) < 0
fun <T> Comparable<T>.lessEq(that: T) = compareTo(that) <= 0
fun <T> Comparable<T>.greaterEq(that: T) = compareTo(that) >= 0
fun <T> Comparable<T>.greater(that: T) = compareTo(that) > 0
fun <T> Comparable<T>.eq(that: T) = compareTo(that) == 0
fun <T : Comparable<T>> T.inn(range: ClosedRange<T>) = this in range