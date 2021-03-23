package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import tests.sout
fun main() {
    obj {
        "obj" to obj {

        }
        "ar" to ar[1,2,3]
        "num" to 1
        "str" to "str"
        "null" to null
        "bool" to false
        "arWithObj" to ar[1, 2, obj{"lol" to 1}]

    }.sout()
    obj {
        "obj"-obj {

        }
        "ar"-ar[1,2,3]
        "num"-1
        "str"-"str"
        "null"-null
        "bool"-false
        "arWithObj"-ar[1, 2, obj{"lol"-1}]
        "arOfObj"-ar[obj{}, obj{}]
    }.sout()
    obj {
        "obj"{

        }
        "ar"[1, 2, 3]
        "num"-1
        "str"-"str"
        "null"-null
        "bool"-false
        "arWithObj"[1, 2, obj{"lol"-1}]
        "arOfObj"[obj{}, obj{}]

    }.sout()
    o {
        "obj" {

        }
        "ar" [1, 2, 3]
        "num"-1
        "str"-"str"
        "null"-null
        "bool"-false
        "arWithObj"[1, 2, o{"lol"-1}]
        "arOfObj"[o{}, o{}]

    }.sout()
    obj {
        "kek"-ar[1,2,3, {"num" to 1}]
    }.sout()


}
@JsonMarker
class JsonObjectBuilder private constructor() {
    companion object {
        fun create(func: JsonObjectBuilder.()->Unit)
            = JsonObjectBuilder().apply(func).node
    }
    private val node: ObjectNode = objectMapper.createObjectNode()

    infix fun String.to(any: Any?) {
        val _node = objectMapper.valueToTree<JsonNode>(any)
        node.set<JsonNode>(this, _node)
    }
    operator fun String.minus(any: Any?) {
        val _node = objectMapper.valueToTree<JsonNode>(any)
        node.set<JsonNode>(this, _node)
    }
    operator fun String.get(vararg any: Any?) {
        val ar = objectMapper.createArrayNode()
        any.forEach {
            ar.add(objectMapper.valueToTree<JsonNode>(it))
        }
        node.set<JsonNode>(this, ar)
    }
    operator fun String.invoke(func: JsonObjectBuilder.()->Unit) {
        node.set<JsonNode>(this, create(func))
    }
}
@JsonMarkerColor
fun obj(func: JsonObjectBuilder.()->Unit): JsonNode = JsonObjectBuilder.create(func)
@JsonMarkerColor
fun o(func: JsonObjectBuilder.()->Unit): JsonNode = JsonObjectBuilder.create(func)

@JsonMarkerColor
object ar {
    @JsonMarkerColor
    operator fun get(vararg any: Any?): ArrayNode? {
        val ar = objectMapper.createArrayNode()
        any.forEach {
            val value = if( it is Function0<*> ) {
                obj { it.invoke() }
            } else {
                it
            }
            ar.add(objectMapper.valueToTree<JsonNode>(value))
        }
        return ar
    }
}


val objectMapper = ObjectMapper()

@Target(AnnotationTarget.CLASS)
@DslMarker
annotation class JsonMarker
@DslMarker
annotation class JsonMarkerColor