package dev.gaodi.fileserver.classes

fun main() {
    val strLen = object: (String)->Result<Int> {
        override fun invoke(p1: String): Result<Int> {
            return Result.success(p1.length)
        }

    }
    strLen("").getOrThrow()
}