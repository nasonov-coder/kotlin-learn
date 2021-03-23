package dev.gaodi.fileserver

import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.kotlin.treeToValue
import java.io.File
import java.lang.IllegalArgumentException


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
        inline fun < reified K > fromJson(string: String): K {
            return mapper.readValue(string)
        }
        inline fun < reified K > fromJson(file: File): K {
            return mapper.readValue(file)
        }
        inline fun < reified K > fromNode(jsonNode: TreeNode): K {
            return mapper.treeToValue(jsonNode) ?: throw IllegalArgumentException()
        }
    }

}