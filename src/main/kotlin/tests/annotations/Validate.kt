package dev.gaodi.fileserver.tests.annotations

import com.fasterxml.jackson.databind.node.ObjectNode
import dev.gaodi.fileserver.tests.obj
import tests.sout
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers

val dateValidator = fun(value: Any?, param:String): Boolean {
    try {
        simpleDateFormat.parse(value as String)
    } catch (t: java.text.ParseException) {
        throw ValidationException("wrong date, got - $value, expected - ${simpleDateFormat.toPattern()}")
    }
    return true
}
annotation class Validation(val type: Type, val param: String = "") {

    enum class Type(val validator: (value: Any?, param:String)->Boolean, val doc: ObjectNode, propertyTypes: List<KClass<*>>, paramValidation: (param: String)->Boolean = {true}) {
        Date(dateValidator, obj{"type"-"string";"format"-"date"}, listOf(String::class)),
        Regex(dateValidator, obj{"type"-"string";"format"-"date"}, listOf(String::class))
    }
}

fun main() {
    val cla = TestValidate::class
    val memb = cla.declaredMembers
    val props = cla.declaredMemberProperties
    println(cla)
}

class TestValidate(
    @Validation(Validation.Type.Date, "")
    val date:String
)