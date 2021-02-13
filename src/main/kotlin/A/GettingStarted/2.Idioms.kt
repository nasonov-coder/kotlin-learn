package A.GettingStarted

import tests.Buyer
import java.io.File
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths

//fun main() {
//    `Execute if not null`()
//}
/**
 * DTO - Data Transfer Object
 *
 * POJO - Plain Old Java Object ~ JavaBean
 *
 * POCO - Plain Old CLR Object
 *
 * provides a Customer class with the following functionality:
 * - getters (and setters in case of vars) for all properties
 * - equals()
 * - hashCode()
 * - toString()
 * - copy()
 * - component1(), component2(), …, for all properties ()
 * @see <a href="https://kotlinlang.org/docs/reference/data-classes.html">Data classes</a>
 */
data class Customer(var name: String, val email: String, val num: Int) {

}

fun main() {
    val c1 = Customer("1","2", 3)
    val c2 = Customer("1","2", 3)
    print("" == "")
    val (x, y) = Customer("1","2", 3)
    val b = Buyer()
    b.setCustomer(c1)


}
/**
 * it - keyword for lambda with only 1 argument
 */
fun dataClass() {
    val c = Customer("kek", "lol", 1)
    c?.name = "lol"
    c!!.component3()
}
fun lambda() {
    val list = listOf("kek", "lol")
    val positives = list.filter { it.isNotEmpty() }
    val lam1 = { x: Int -> print(x > 0) }
    val lam2 = { x: Int, y: Int -> print(x > y) }
    lam1(1)
    fun kek(predicate: (Int) -> Unit) {
        val internalValue = 1
        predicate(internalValue)
    }
    kek { print(it) }
}
fun CheckingElementPresenceInACollection() {
    val emailsList = listOf("1", "2")
    if ("john@example.com" in emailsList) {  }

    if ("jane@example.com" !in emailsList) {  }
    val m = mapOf(1 to "2")
}
fun `if not null shorthand`() {
    val files = File("Test").listFiles()

    println(files?.size)
}
fun `if not null and else shorthand`() {
    val files = File("Test").listFiles()

    println(files?.size ?: "empty")
}
fun `Executing a statement if null`() {
    val values = mapOf("name" to "lel")
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")
}
fun `Execute if not null`() {
    val value: Customer? = Customer("lol","kek", 1)

    // todo inlineOnly
    println(value?.let {
        it.name += "__"
        it.name + it.email
    })
    // do the same
    println(value?.let kek@{ lel ->
        lel.name += "__"
        return@kek lel.name + lel.email
    })
}
fun `Builder-style usage of methods that return Unit`() {
    //let, run, with, apply, and also - scope functions
    arrayOfMinusOnes(10).apply {
        set(1,2)

    }
}
fun `Java 7's try with resources`() {
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}
fun `Convenient form for a generic function that requires the generic type information`() {
    //  public final class Gson {
//     ...
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ...

       // inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
}
fun `Consuming a nullable Boolean`(b: Boolean?) {
    if (b == true) {

    } else {
        // `b` is false or null
    }
}
fun `Swapping two variables`() {
    var a = 1
    var b = 2
    a = b.also { b = a }
}
fun `TODO() Marking code as incomplete`() {
    //Kotlin's standard library has a TO-DO() function that will always throw a NotImplementedError. Its return type is Nothing so it can be used regardless of expected type. There's also an overload that accepts a reason parameter:
    //
    fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")
    //IntelliJ IDEA's kotlin plugin understands the semantics of TO-DO() and automatically adds a code pointer in the TO-DO tool window.
}
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}