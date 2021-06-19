import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MyTest: Spek({
    println("this is the root")
    group("some group") {
        println("some group")
        test("some test") {
            println("some test")
        }
    }

    group("another group") {
        println("another group")
        test("another test") {
            println("another test")
        }
    }
})
object MyTest2: Spek({
    println("this is the root")
    group("some group") {
        println("some group")
        test("some test") {
            println("some test")
        }

    }

    group("another group") {
        println("another group")
        test("another test") {
            println("another test")
        }
    }
})

abstract class FirstTest

object first: FirstTest()