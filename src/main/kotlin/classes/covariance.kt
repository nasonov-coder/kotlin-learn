package dev.gaodi.fileserver.classes

import tests.sout

fun interface CovarianceOut<out T> {
    fun get() : T
    //set is prohibited
}
fun interface CovarianceIn<in T> {
    //get is prohibited

    fun set(t: T)

}

open class Super
open class Base: Super()
class Sub: Base()

fun main() {


    val covarianceIn: CovarianceIn<Base> = CovarianceIn<Super> { _: Super ->  }
    covarianceIn.set(Sub())
//    prohibited
//    covarianceIn.set(Super())

    val covarianceOut: CovarianceOut<Base> = CovarianceOut<Sub> { Sub() }
    val s1: Super = covarianceOut.get()
//    prohibited
//    val s2: Sub = covarianceOut.get()
}





//    CovarianceIn<Int> { println(it) }.set(123)
//    CovarianceOut<Int> { 321 }.get().sout()