package dev.gaodi.fileserver.tests.annotations

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.starProjectedType

annotation class AnnotatedProp

class Foo {
    @AnnotatedProp
    var foo: Boolean? = null
    var bar: String? = null
}

fun main(args: Array<String>) {

    // Process annotated properties declared in class Foo.
    Foo::class.declaredMemberProperties.filter {
        it.findAnnotation<AnnotatedProp>() != null
    }.forEach {
        println("Name: ${it.name}")
        println("Nullable: ${it.returnType.isMarkedNullable}")
        println("Type: ${it.returnType.classifier!!.starProjectedType}")
    }

}