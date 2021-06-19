package dev.gaodi.fileserver.tests.annotations

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.fasterxml.jackson.module.kotlin.readValue
import dev.gaodi.fileserver.tests.obj
import tests.sout
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat

val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy").apply { isLenient = false }

data class TestRequest(
    val date: DateValidator
)
fun main() {
    val mapper = jacksonObjectMapper()
    val eqDate = mapper.treeToValue<TestRequest>(obj{"date"-"01.02.2020"}).sout()
    mapper.writeValueAsString(eqDate).sout()
}



open class Validator<T> constructor(val value: T, val validator:(T)->Boolean, val doc:ObjectNode) {
    init {
        try {
            if(!validator(value))
                throw ValidationException("invalid value")
        } catch (t: ValidationException) {
            throw t
        } catch (t: Throwable) {
            t.printStackTrace()
            throw ValidationException("error: unhandled exception during validation")
        }
    }
    @JsonValue
    var field: T = value

    override fun toString(): String {
        return field.toString()
    }
}
class ValidationException(msg: String): Throwable(msg)

class DateValidator @JsonCreator constructor (string: String): Validator<String>(string, {
    try {
        println("start")
        simpleDateFormat.parse(it).sout()
        println("ok")
    } catch (t: java.text.ParseException) {
        throw ValidationException("wrong date, got - $string, expected - ${simpleDateFormat.toPattern()}")
    }
    true
},  obj{"type"-"string";"format"-"date"})







class EqDate @JsonCreator constructor(string: String) {
    init {
        try {
            println("start")
            simpleDateFormat.parse(string).sout()
            println("ok")
        } catch (t: Throwable) {
            throw IllegalArgumentException("wrong date")
        }
    }
    @JsonValue
    var date: String = string

    override fun toString(): String {
        return "EqDate(date=$date)"
    }
}