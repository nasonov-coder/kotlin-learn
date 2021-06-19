package H.MoreLanguageConstructs

//let, run, with, apply, and also
fun main() {
    println(`scope let`())
    println(`scope run`())
    KKClass().also(::println).apply(::println).toKek()
    KKClass().apply {
        testFunRef()
        testMyLet()
    }

}
fun `scope let`(): Int {
    return KKClass().let {
        it.toKek()
        return@let it.int
    }
}
fun `scope also`(): KKClass {
    return KKClass().also {
        it.toKek()
        it.int
    }
}
fun `scope run`():Int {
    return KKClass().run {
        // wont work:
        // println(it)
        // it is not defined
        toKek()
        int
    }
}
fun `scope apply`(): KKClass {
    return KKClass().apply {
        // wont work:
        // println(it)
        // it is not defined
        toKek()
        int
    }
}
fun `scope with`():Int {
    return with(KKClass()) {
        // wont work:
        // println(it)
        // it is not defined

        toKek()
        int
    }
}
// todo takeIf and takeUnless


class KKClass(
    val int: Int = 1
) {
    fun toKek() {

    }
    fun toLol(k: KKClass) {
        println(k)
    }
    fun testFunRef() {
        this.let(this::toLol)
        let(this::toLol)
        let(::toLol)
    }
    fun myLet(block: (KKClass) -> Unit):Unit {
        block.invoke(this)
    }
    fun testMyLet() {
        myLet(::toLol)
    }

}