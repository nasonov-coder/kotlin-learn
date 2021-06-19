package dev.gaodi.fileserver.zz.opendoc

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import dev.gaodi.fileserver.tests.obj
import tests.sout
import kotlin.properties.Delegates
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

class IntDelegate {
    var value by Delegates.notNull<Int>()
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        this.value = value
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun int() = IntDelegate()

class Request {
    var i:Int by int()
    lateinit var kek: String
}

fun main() {
    jacksonObjectMapper().treeToValue<Request>(obj { })
//    Request().i = 1
//    Request::class.declaredMemberProperties.sout()
}