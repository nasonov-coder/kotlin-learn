package z.testsprod.couchbase


class EqNode private constructor(
    val type: Type,
    val value: String?,
    val params: List<EqNode>?,
) {

    override fun toString(): String {
        println("type: $type, value: $value, params: $params")
        return when(type) {
            Type.OPERATION -> {
                when(value) {
                    "and", "or" -> "(" + params!!.joinToString(" $value ") + ")"
                    "eq" -> params!!.joinToString(" = ")
                    else -> TODO()
                }
            }
            else -> value ?: TODO()
        }
    }

    enum class Type {
        OPERATION,
        NAME,
        VALUE,
    }
    enum class Operation {
        AND,
        OR,
        EQ,
    }

    companion object {
//        val regexFun = Regex("^([\\w_]*?)\\((.*)\\)\$")
        fun node(value: String, params: List<EqNode>?) =
            EqNode(Type.OPERATION, value, params)
        fun fieldName(name: String) =
            EqNode(Type.NAME, name, null)
        fun fieldValue(value: String) =
            EqNode(Type.VALUE, value, null)

    }


}
val test = "and(or(eq(kek,1),eq(lol,2)),eq(lel,3))"
val test2 = "eq(1,1),eq(lel,3), eq(lel, 0),or(eq(lel,1),eq(lol,2)"
val test3 = "and(1,2),3"
fun main() {
    Parser(test2).parse("and").let(::println)
}
class Parser(private val string: String) {

    private infix fun ArrayList<EqNode>.flush(buff: ArrayList<Char>) {
        if(buff.isNotEmpty()) {
            this += EqNode.fieldValue(buff.join())
            buff.clear()
        }
    }
    private fun ArrayList<Char>.join() = joinToString("").trim()
    var i = -1
    fun parse(parentOperator: String): EqNode {
        var operation: String
        val params = ArrayList<EqNode>()
        val buff = ArrayList<Char>(10)
        while (i < string.length - 1) {
            i++
            when(val c = string[i]) {
                '(' -> {
                    operation = buff.join()
                    buff.clear()
                    params += parse(operation)
                }
                ')' -> {
                    params flush buff
                    break
                }
                ',' -> {
                    params flush buff
                }
                else -> {
                    buff += c
                }
            }

        }
        return EqNode.node(parentOperator, params)
    }

}

