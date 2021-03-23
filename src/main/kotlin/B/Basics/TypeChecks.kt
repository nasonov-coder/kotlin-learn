package B.Basics

fun main() {
    `Type erasure and generic type checks`()
}

fun `simple is`() {
    val obj: Any = ""
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) { // same as !(obj is String)
        print("Not a String")
    } else {
        print(obj.length)
    }
}

fun `smart cast`() {
    val x: Any = ""
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }
}

fun `cast`() {
    val y: Any? = stringOrNull()
    //unsafe cast
    val x: String? = y as String?
    //safe cast
    val z: String? = y as? String // there is smart cast: y as String? threw no exception, so y - String?

}

fun stringOrNull(): Any? = "123"


fun `Type erasure and generic type checks`() {
    val something = getList()
    if (something is List<*>) {
        something.forEach { println(it is Int) } // The items are typed as `Any?`
    }
    // if (something is List<Int>) { } - error: Cannot check for instance of erased type: List<Int>
}
fun getList(): List<*> = listOf(1, 2, 3)

fun `unchecked casts`() {
    //https://kotlinlang.org/docs/typecasts.html#unchecked-casts
    TODO()
}