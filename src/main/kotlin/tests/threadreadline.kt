package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.databind.ObjectMapper
import tests.sout
import kotlin.concurrent.thread



var inputting = false

fun main() {
    val simple = readLine()!!
    println(simple == "")
    thread {
        while( true ) {
            Thread.sleep(1000L)
            if( !inputting)
                println("[task, skkakdkask]")
        }
    }
    while( true ) {
        val comm = readLine()
        when(comm) {
            "t" -> readTask()
        }
    }
}
fun readTask() {
    inputting = true
    println("pls enter: receiver method arguments meta(optional)")
    val header = readLine()
    println("pls enter payload json or nothing:")
    val payload = readLine()
    inputting = false
//    sendTask() {
//        println()
//    }
}

