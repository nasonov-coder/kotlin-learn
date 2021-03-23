package dev.gaodi.fileserver.dev.equal.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlin.reflect.KClass


fun main() {
    val ser = service {
        method<Doc.Get, Empty>("doc/get") {
            with(arguments) {
                println("$index/_doc/$id?$query")
            }
        }
        method<Doc.Get, JsonNode>("doc/test") {
            with(arguments) {
                println("$index/_doc/$id?$query")

            }
            println(payload)
        }
    }
    //tests
    ser.call("doc/get", Doc.Get("lol", "sad", "asd"))
    ser.call("doc/test", Doc.Get("lol", "sad", "asd"), """{"q":1}""")
}


abstract class Doc {
    class Get(val query: String, val index: String, val id: String)
    class Search(val query: String, val index: String)
}







//////////////////// start of my classes

fun service(function: Service.() -> Unit): Service {
    val serv = Service()
    function(serv)
    return serv
}
@ContextDsl
class Service {
    val methods: MutableMap<String, Method> = mutableMapOf()
    class Method(
        val callback: Task<*, *>.()->Unit,
        val argType: KClass<*>,
        val payloadType: KClass<*>
    )


    fun call(method: String, args: Any, payload: String = "") {
        val meth = methods[method]!!
        val mapper = jacksonObjectMapper()
        mapper.registerKotlinModule()
//        val argsJson = mapper.writeValueAsString(args)
//        val arguments = mapper.treeToValue(mapper.readTree(argsJson), meth.argType.java)
        val pay = if( meth.payloadType != Empty::class)
            mapper.treeToValue(mapper.readTree(payload), meth.payloadType.java)
        else
            Empty()
        meth.callback.invoke(
            Task(
                method,
                args,
                "anchor",
                UserBean("g", "n", "s"),
                pay
            )
        )
    }
}
inline fun <reified ArgType, reified PayloadType> Service.method(method: String, noinline callback: Task<ArgType, PayloadType>.()->Unit) {
    methods[method] = Service.Method(
        callback as Task<*, *>.() -> Unit,
        ArgType::class,
        PayloadType::class
    )
}
@ContextDsl
class Task<ArgType, PayloadType>(
    val method: String,
    val arguments: ArgType,
    val anchor: Any,
    val sender: UserBean,
    val payload: PayloadType
) {
    fun resolve(code: String, message: String, payload: Any? = null) {
        println("$code: $message, $payload")
    }
    fun reject(code: String, message: String, payload: Any? = null) {
        println("$code: $message, $payload")
    }
    fun error(code: String, message: String, payload: Any? = null) {
        println("$code: $message, $payload")
    }

}
data class UserBean(
    val group: String,
    val name: String,
    val session: String,
)
class Empty
class Binary
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
public annotation class ContextDsl