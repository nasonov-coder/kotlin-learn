package H.MoreLanguageConstructs

//let, run, with, apply, and also
fun main() {
    println(`scope let`())
    println(`scope run`())
    KClass().also(::println).apply(::println).toKek()
    KClass().apply {
        testFunRef()
        testMyLet()
    }

}
fun `scope let`(): Int {
    return KClass().let {
        it.toKek()
        return@let it.int
    }
}
fun `scope also`(): KClass {
    return KClass().also {
        it.toKek()
        it.int
    }
}
fun `scope run`():Int {
    return KClass().run {
        // wont work:
        // println(it)
        // it is not defined
        toKek()
        int
    }
}
fun `scope apply`(): KClass {
    return KClass().apply {
        // wont work:
        // println(it)
        // it is not defined
        toKek()
        int
    }
}
fun `scope with`():Int {
    return with(KClass()) {
        // wont work:
        // println(it)
        // it is not defined

        toKek()
        int
    }
}
// todo takeIf and takeUnless


class KClass(
    val int: Int = 1
) {
    fun toKek() {

    }
    fun toLol(k: KClass) {
        println(k)
    }
    fun testFunRef() {
        this.let(this::toLol)
        let(this::toLol)
        let(::toLol)
    }
    fun myLet(block: (KClass) -> Unit):Unit {
        block.invoke(this)
    }
    fun testMyLet() {
        myLet(::toLol)
    }

}