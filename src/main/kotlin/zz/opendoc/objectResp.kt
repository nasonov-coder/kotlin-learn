package dev.gaodi.fileserver.zz.opendoc

import dev.gaodi.fileserver.dev.equal.service.Empty
import tests.sout
import kotlin.reflect.full.findAnnotation

object Resp {
    val resp = object : @Descc("234") NotFound<Int> {
        override val notFoundDesc = Desc("")
    }
}

@Descc("lol")
interface NotFoundEmpty: NotFound<Empty>

interface NotFound<T> {
    val notFoundDesc: Desc
        get() = Desc("Not Found")

    fun notFound(value: T) {

    }
}

fun NotFound<Empty>.notFound() {notFound(Empty())}
fun main() {
    //Resp.resp.notFound()
    Resp.resp.notFoundDesc.sout()
    val r = Resp.resp
    val sup = r::class.supertypes.first()

    println(sup.findAnnotation<Descc>())
}

data class Desc(
    @Descc("asd") val desc: String
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
annotation class Descc(val desc: String)