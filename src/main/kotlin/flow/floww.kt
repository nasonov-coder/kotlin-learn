package dev.gaodi.fileserver.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.single

suspend fun main() {
    sample().collect{
        println("collect")
        delay(3000)
        println("value $it")
    }
}

fun sample() = flow<Int> {
    generateSequence(0) { it + 1 }.take(3).forEach {
        emit(it)
    }
}