package dev.gaodi.fileserver.ignat

import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import tests.Kek
import java.io.File

open class Noder {
    fun toJson():String {
        return mapper.writeValueAsString(this)
    }
    fun toNode(): JsonNode {
        return mapper.valueToTree(this)
    }
    override fun toString(): String {
        return toJson()
    }
    companion object {
        val mapper = jacksonObjectMapper()
        init {
            mapper.registerKotlinModule()
        }
        inline fun < reified K > fromJson(string: String, clazz: Class<*>): K {
            return mapper.treeToValue(mapper.readTree(string), K::class.java)
        }
        inline fun < reified K > fromJson(file: File): K {
            return mapper.treeToValue(mapper.readTree(file), K::class.java)
        }
        inline fun < reified K > fromNode(jsonNode: TreeNode): K {
            return mapper.treeToValue(jsonNode, K::class.java)
        }
    }
}

fun main() {
    val list = listOf<Int>(1,2,3)
}