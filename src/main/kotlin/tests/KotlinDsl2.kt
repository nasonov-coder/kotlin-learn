package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import tests.sout
import java.util.*

val objectMapper2 = ObjectMapper()

fun json(build: JsonObjectBuilder2.() -> Unit): JsonNode {
    return JsonObjectBuilder2().json(build)
}

class JsonObjectBuilder2 {
    private val deque: Deque<ObjectNode> = ArrayDeque()

    fun json(build: JsonObjectBuilder2.() -> Unit): JsonNode {
        deque.push(objectMapper.createObjectNode())
        this.build()
        return deque.pop()
    }

    infix fun <T> String.to(value: T) {
        // wrap value into json block if it is a lambda
        val wrapped: JsonNode = when (value) {
            is Function0<*> -> json { value.invoke() }
            is Array<*> -> objectMapper.createArrayNode().apply { value.forEach { add(objectMapper.valueToTree<JsonNode>(it)) } }
            else -> objectMapper.valueToTree<JsonNode>(value)
        }

        deque.peek().set<JsonNode>(this, wrapped)
    }
}


fun main() {
    json {
        "kek" to {
            "lol" to "kek"
            "er" to arrayOf({"lol"})
        }
    }.sout()
}