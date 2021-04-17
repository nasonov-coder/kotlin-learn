package dev.gaodi.fileserver.tests

import tests.sout

fun main() {
    val s = "Hello, there.Ж"
    val b: ByteArray = s.toByteArray(Charsets.US_ASCII)
    val c: ByteArray = s.toByteArray(Charsets.UTF_8)
    println("b: ${b.size}, c: ${c.size}")

    "path.to.valu`e".escapePath().sout()
}

fun String.escapePath(prefix: String = "`doc`.")
    = prefix + this.split('.').joinToString(".") { "`${it.replace("`", "``")}`" }

