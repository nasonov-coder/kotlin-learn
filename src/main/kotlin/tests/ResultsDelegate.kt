package dev.gaodi.fileserver.tests

//class Res<T>(some: T): {}
class Res(some: Keklik, error: String): IKeklik by some, IResult by Result(error){}

interface IResult {
    var error: String
}
class Result(override var error: String): IResult
interface IKeklik {
    val a: String
}
open class Keklik(override val a: String): IKeklik {

}
object KOK: IResult, Keklik("") {
    override var error: String = ""
}

fun main() {
    Res(Keklik("sad"), "errr").apply {
        println(a)
        println(error)
    }
}