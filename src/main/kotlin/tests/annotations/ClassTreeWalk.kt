package dev.gaodi.fileserver.tests.annotations

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

class Top(
    val middle1: Middle,
    val middle2: Middle
) {
    class Middle(
        val bottom: Bottom
    ) {
        class Bottom(
            @ReqSize(1)
            val double: String
        )
    }
}

fun main() {
   getClassMembers(Top::class)
   //getClassMembers(Top.Middle.Bottom::class)
}
fun getClassMembers(item: KClass<*>) {
    item.memberProperties.forEach {
        println(it)
        getClassMembers(it.returnType.jvmErasure)
    }
}

@Target(AnnotationTarget.FIELD)
annotation class ReqSize(val size: Int)

