package dev.gaodi.fileserver.delegate.props

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty

suspend fun main(): Unit = coroutineScope {
    with(Kek()) {
        kek1 += async {
            "qwe"
        }
        kek3 += async {
            delay(1)
            yield()
            234
        }
        awaitAll()
        kek1
        println(-kek1)
        delay(30000)
        println(-kek3)
//        println(kek2())

//        val (lol) = kek1

    }

}

class Kek: AwaitClass()
{
    val kek1 by async<String>()
    val kek2 by async<String>()
    val kek3 by async<Int>()

}

class AwaitDelegate<T>(private var backingField: AwaitField<T>) {
    operator fun getValue(thisRef: AwaitClass, property: KProperty<*>): AwaitField<T> {
        return backingField
    }
//    operator fun setValue(thisRef: AwaitClass, property: KProperty<*>, value: AwaitField<T>) {
//        thisRef.deffs += value
//        backingField = value
//    }
}
abstract class AwaitClass {
    private val deffs = mutableListOf<AwaitField<*>>()
    protected fun <T> async(): AwaitDelegate<T> {
        val field = AwaitField<T>()
        deffs += field
        return AwaitDelegate(field)
    }
    protected fun <T> async2(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): AwaitDelegate<T> {
        val field = AwaitField<T>()
        field += GlobalScope.
            async(context, start, block)

        deffs += field
        return AwaitDelegate(field)

    }
    suspend fun awaitAll() {
//        val deffsReady = deffs.filter { it.def != null }
//        deffsReady.map{it.def!!}.awaitAll().zip(deffsReady).forEach { (res, field) ->
//            field as AwaitField<*>
//            field.isSet = true
//            field.value
//            field.value = res
//        }
        deffs.mapNotNull { it.def }.awaitAll()
        deffs.forEach {
            it.await()
        }
    }

}

class AwaitField<T>(
    internal var def: Deferred<T>? = null,
    internal var value: T? = null
) {
    internal var isSet = false
    operator fun plusAssign(def: Deferred<T>) {
        addDeferred(def)
    }
    fun addDeferred(def: Deferred<T>) {
        this.def = def
    }
    internal suspend fun await() {
        if(isSet)
            return
        def?.await()?.also {
            value = it
            isSet = true
            def = null
        }
    }
    operator fun invoke() =
        getValue()

    private fun getValue(): T {
        require(isSet) { "no result" }
        return value!!
    }
    operator fun unaryMinus() = getValue()
    operator fun component1() = getValue()
}