package dev.gaodi.fileserver.delegate.props

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty

suspend fun main(): Unit = coroutineScope {
    val kek = object: IAsync by asyncDelegate() {
        val kek1 by async {
            ""
        }
    }
    kek.awaitAll()
    kek.kek1
}
interface IAsync {
    suspend fun awaitAll()
//    fun <T> async(): AwaitDelegate2<T>
    fun <T> async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): AwaitDelegate2<T>

}
fun CoroutineScope.asyncDelegate(): IAsync =
    object :IAsync {
        private val scope = this@asyncDelegate
        private val deffs = mutableListOf<AwaitField<*>>()
//        override fun <T> async(): AwaitDelegate2<T> {
//            val field = AwaitField<T>()
//            deffs += field
//            return AwaitDelegate2(field)
//        }
        override fun <T> async(
            context: CoroutineContext,
            start: CoroutineStart,
            block: suspend CoroutineScope.() -> T
        ): AwaitDelegate2<T> {
            val field = AwaitField<T>()
            field += scope.async(context, start, block)

            deffs += field
            return AwaitDelegate2(field)

        }
        override suspend fun awaitAll() {
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

class AwaitDelegate2<T>(private var backingField: AwaitField<T>) {
    operator fun getValue(thisRef: IAsync, property: KProperty<*>): T {
        return backingField()
    }
//    operator fun setValue(thisRef: AwaitClass, property: KProperty<*>, value: AwaitField<T>) {
//        thisRef.deffs += value
//        backingField = value
//    }
}
