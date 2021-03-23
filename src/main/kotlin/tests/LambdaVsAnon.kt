package tests

/**
 * anonymous supports return without @ and provides ability to implicitly set return type
 */
fun main() {

    val list = 1..10
    list.forEach(fun(i) {
        if(i % 2 == 0)
            println(i)
        else
            return
        print(" % 2 == 0")

    })
    list.forEach {
        if(it % 2 == 0)
            println(it)
        else
            return@forEach
        print(" % 2 == 0")
    }
    print("done")
}