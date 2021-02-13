package tests
    val indent = "   "
fun main() {
    """
        $indent - no indent here 
    """.let(::trimAndPrint)
    """
        $indent - indent here
        kek
    """.let(::trimAndPrint)
    """
        $indent - no indent here
            kek
    """.let(::trimAndPrint)
}
fun trimAndPrint(string: String) {
    println(string.trimIndent())
}