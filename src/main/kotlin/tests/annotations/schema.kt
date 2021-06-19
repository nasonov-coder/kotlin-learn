package dev.gaodi.fileserver.tests.annotations

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.treeToValue
import dev.gaodi.fileserver.tests.obj
import tests.sout

fun main() {
    val mapper = jacksonObjectMapper()
    val generator = JsonSchemaGenerator(mapper)
    generator.generateSchema(DependencySchema::class.java)

    println(mapper.writeValueAsString(generator.generateSchema(DependencySchema::class.java)))

    mapper.treeToValue<DependencySchema>(obj{"lol"-"02.2020"})?.lol.sout()


}
class DependencySchema {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    var lol: String? = "null"
}
