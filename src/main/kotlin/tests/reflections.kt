package tests

import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

val x = 1

fun main() {
    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length))
    println(::x.get())
    println(::x.name)
    sayMyName(::x)
    feedMyFun(String::length)
}

fun sayMyName(kProperty: KProperty<Any>) {
    println(kProperty.name)
}
fun sayMyName(kProperty0: KProperty0<Int>) {
    println(kProperty0.name)
}
fun feedMyFun(kProperty1: KProperty1<String, Int>) {
    feedMyYourFun(::feedMyFun)
    sayMyName(kProperty1)
}

fun feedMyYourFun(kFunction1: KFunction1<KProperty1<String, Int>, Unit>) {
}
