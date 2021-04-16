package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import tests.sout

fun main() {
    val m = jacksonObjectMapper()
    val ob = obj {
        "bot"{
            "kek"-"asd"
        }
    }
    m.treeToValue<Top>(ob).sout()
}

data class Top(val bot: Bottom)
data class Bottom(val kek: String)