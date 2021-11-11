package dev.gaodi.fileserver.tests.annotations

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.gaodi.fileserver.dev.equal.service.Empty
import kotlin.reflect.KClass


fun route(path: String): RouteBuilder<Empty, Empty, Empty, Empty, Empty> {
    return RouteBuilder<Empty, Empty, Empty, Empty, Empty>()
}
@Suppress("UNCHECKED_CAST")
class RouteBuilder<Args : Any,
        Payload : Any,
        Rej: Any,
        ResPayload : Any,
        Res: Any> {
    inner class Types {
        lateinit var args: KClass<Args>
    }
    lateinit var args: KClass<Args>
    inline fun <reified T: Any> args(): RouteBuilder<T, Payload, Rej,  ResPayload, Res> {
        this as RouteBuilder<T, Payload, Rej,  ResPayload, Res>
        args = T::class
        return this
    }
    inline fun <reified T: Any> args(on: RouteBuilder<T, Payload, Rej,  ResPayload, Res>.()-> Unit) {
        this as RouteBuilder<T, Payload, Rej,  ResPayload, Res>
        args = T::class
        println(args)
        on(this)
    }
    val k: String
        get() = ""
}
val mapper = jacksonObjectMapper()
fun main() {
    route("a")
        .args<Int>()
        .args<Int>
    {
        println(args)
    }
}
/*
inline fun <
            reified Args : Any,
            reified Payload : Any,
            reified Rej: RespMessage,
            reified ResPayload : Any,
            reified Res: RespMessage
    >
 */