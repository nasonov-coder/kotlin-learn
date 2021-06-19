package dev.gaodi.fileserver.tests

fun interface KekInt {
    fun lol(i:InterfaceSAM.()->Unit): Unit
}
fun interface LolInt {
    fun lol(i:Int, j: Int): Unit
}
class InterfaceSAM {
    val kek = 1
}
fun main() {
    val obj = KekInt { InterfaceSAM().apply(it); println("lol")}
    obj.lol { println("kek$kek"); }
    val kokj = LolInt { _, _ ->}

    val sum = MathOperation {a,b -> a+b}
    val sub = MathOperation {a,b -> a-b}
}

fun interface MathOperation {
    fun calc(i: Int, j: Int): Int
}