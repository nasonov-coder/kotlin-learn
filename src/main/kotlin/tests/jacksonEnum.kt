package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import tests.sout

fun main() {
    val mapper = jacksonObjectMapper().apply {
        enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    mapper.treeToValue(obj { "value"-"lol"; "enum"-En::class.java.enumConstants.map {it.name.toLowerCase(); it.str; val kek = it; it} }, ObjEnum::class.java).sout()
}
data class ObjEnum(val value: En, val enum: Any)

enum class En(val str: String) {
    Lol("asd")
}