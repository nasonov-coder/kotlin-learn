package dev.gaodi.fileserver.bugs

import tests.sout
import java.util.*

inline fun <reified T: Enum<T>> kek(enumSet: EnumSet<T>) {
    try {
        println(enumValues<T>())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
    val enumSet1 = EnumSet.allOf(SimpleEnum::class.java)
    kek(enumSet1)
    listOf<EnumSet<*>>(enumSet1).forEach {
        kek(it) // RUNTIME ERROR!!!
    }

    val enumSet2 = EnumSet.allOf(SimpleEnum2::class.java)
    listOf(enumSet1, enumSet2).forEach {
//        kek(it) // COMPILE TIME ERROR!!!
    }
}

enum class SimpleEnum {
    One, Two, Three
}
enum class SimpleEnum2 {
    One, Two, Three
}