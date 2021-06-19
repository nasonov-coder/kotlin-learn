package dev.gaodi.fileserver.tests.work

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.intellij.lang.annotations.Language

val mapper =  jacksonObjectMapper()
fun main() {
    @Language("JSON") val str = """
    {
        "and": [
          {
            ">": [
              123456,
              {"field": "path.to.field"}
            ]
          },
          {
            "not_in": [
              {"field": "path.to.field"},
              ["A", "B", "C"]
            ]
          },
          {
            "and": [
              {
                ">": [
                  123456,
                  {"field": "path.to.field"}
                ]
              },
              {
                "not_in": [
                  {"field": "path.to.field"},
                  ["d"]
                ]
              }
            ]
          } 
        ]
    }
    """
   println(Where(mapper.readTree(str)))

}



sealed class WhereNode(private val str: String) {
    sealed class Operation(str: String): WhereNode(str) {
        class Logical(operator: String, operands: List<WhereNode>): Operation(
            "(${operands.joinToString(" $operator ")})"
        )
        class Math(operator: String, operands: List<WhereNode>): Operation(
            "(${operands[0]} $operator ${operands[1]})"
        )
        class Inclusion(operator: String, operands: List<WhereNode>): Operation(
            "(${operands[0]} $operator ${operands[1]})"
        )
    }
    sealed class Value(str: String): WhereNode(str) {
        class Numb(numb: String): Value(numb)
        class Str(escapedValue: String): Value(escapedValue)
        class Array(map: List<WhereNode>) : Value("[${map.joinToString(",")}]")
        class Null: Value("null")
        class Bool(bool: Boolean): Value(bool.toString())
    }

    class Field(escapedPath: String): WhereNode(escapedPath)

    override fun toString(): String {
        return str
    }
}

enum class Operator(vararg list: String){
    Math(">"),
    Logical("and"),
    Inclusion("in", "not_in");

    private val map: Map<String, String> = list.associate{ it to it.replace('_', ' ').toUpperCase()}
    companion object {
        fun find(string: String): Pair<Operator, String>? {
            Operator.values().forEach {
                if (it.map.containsKey(string))
                    return Pair(it, it.map[string]!!)
            }
            return null
        }
    }

}
class Where(json: JsonNode) {
    val params = mapper.createObjectNode()
    val query = Parser("root").parse(json)
    private var counter = 1

    inner class Parser(val path: String) {
        fun parse(node: JsonNode): WhereNode {
            return when(node) {
                is ArrayNode ->
                    WhereNode.Value.Array(node.mapIndexed{k, v -> Parser("$path[$k]").parse(v) })
                is TextNode ->
                    WhereNode.Value.Str(escapeString(node.asText()))
                is NumericNode ->
                    WhereNode.Value.Numb(node.asText())
                is BooleanNode ->
                    WhereNode.Value.Bool(node.booleanValue())
                is NullNode ->
                    WhereNode.Value.Null()
                is ObjectNode -> {
                    val fields = node.toList()
                    if(fields.size != 1)
                        error("size != 1, size = ${fields.size}")
                    val (key, value) = fields[0]
                    if(key == "field")
                        WhereNode.Field(escapePath(value.asText()))
                    else if( key == "value")
                        TODO()
                    else {
                        val rawOperands = value as? ArrayNode
                            ?: error("operands must be array, key - $key")
                        val (operatorType, operator) = Operator.find(key)
                            ?: error("unknown operator $key")
                        val operands = rawOperands.map { Parser("$path.$key").parse(it) }
                        try {
                            checkOperands(operatorType, operands)
                        } catch (e: Throwable) {
                            error(e.message ?: "", ".$key")
                        }

                        when(operatorType) {
                            Operator.Math -> WhereNode.Operation.Math(operator, operands)
                            Operator.Inclusion -> WhereNode.Operation.Inclusion(operator, operands)
                            Operator.Logical -> WhereNode.Operation.Logical(operator, operands)
                        }
                    }
                }
                else -> error("unknown node type")
            }
        }
        @WhereError
        fun error(msg: String, additionalPath: String = ""): Nothing {
            throw WhereException("$msg at $path$additionalPath")
        }
    }

    private fun checkOperands(operator: Operator, operands: List<WhereNode>) {
        when(operator) {
            Operator.Math -> {
                require(operands.size == 2) {
                    "only 2 operands supported"

                }
                require(operands.all { it is WhereNode.Value.Numb || it is WhereNode.Field }) {
                    "operands should be number or field"
                }
            }
            Operator.Logical -> {
                require(operands.size >= 2) {
                    "there should be more than 1 operand"

                }
            }
            Operator.Inclusion -> {
                require(operands.size == 2) {
                    "only 2 operands supported"
                }
                with(operands[1]) {
                    require(this is WhereNode.Value.Array || this is WhereNode.Field) {
                        "second operand should be array or field"
                    }
                }
            }
        }
    }

    private fun escapeString(string: String): String {
        val name = "param${counter++}"
        params.put(name, string)
        return "\$$name"
    }
    private fun escapePath(string: String, prefix: String = "`doc`.")
            = prefix + string.split('.').joinToString(".") { "`${it.replace("`", "``")}`" }

    override fun toString(): String {
        return "WhereParser(params=$params, root=$query)"
    }

}
fun ObjectNode.toList(): List<Pair<String, JsonNode>> {
    return this.fields().asSequence().map{ it.key!! to it.value }.toList()
}

@DslMarker
annotation class WhereError

class WhereException(msg: String): IllegalStateException(msg)