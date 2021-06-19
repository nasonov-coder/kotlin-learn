package dev.gaodi.fileserver.tests.annotations

import tests.sout
@Target(AnnotationTarget.CLASS)
annotation class ClassProperty(val name: String)

@ClassProperty("Kekos")
class Testable
fun getClassProperty(item: Any) {
    item::class.java.annotations.forEach {
        if(it is ClassProperty)
            println(it.name)
    }
}
fun main() {
    getClassProperty(Testable())
}
