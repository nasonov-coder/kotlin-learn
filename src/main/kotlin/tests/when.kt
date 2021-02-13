package tests

fun main() {
    val c = '('
    when( c ) {
        '(', ',' ->
            print(1)
        '(' -> print(2)
    }
}