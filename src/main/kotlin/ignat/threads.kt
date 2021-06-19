package dev.gaodi.fileserver.ignat

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.concurrent.thread

//private fun readMsgUart() {
//    val file = File(uart0)
//    thread {
//        while (!Thread.interrupted()) {
//            try {
//                val lines = file.readLines()
//                doSomethingWithLines(lines)
//            } catch (e: Throwable) {
//                e.printStackTrace()
//            }
//        }
//    }
//}
//
//
//fun doSomethingWithLines(lines: List<String>) {}
//
//
//
//private fun readMsgUart2() {
//    val inStream = BufferedInputStream(openFileInput(uart0))
//    val bufferedReader = InputStreamReader(inStream, "UTF-8")
//
//    threadLoop {
//        val lines = bufferedReader.readLines()
//        doSomethingWithLines(lines)
//    }
//}
//
//fun threadLoop(block:()->Unit) {
//    thread {
//        while (!Thread.interrupted()) {
//            try {
//                block()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}

//class TestClient(private val client: Client, private val identity: Identity) {
//    fun task(method: String, args: JsonNode, payload: Any?): EqResult {
//        return try {
//            runBlocking {
//               val res = client.task(identity, method, args, payload)
//                EqResult(ResultTypes.Res)
//            }
//        } catch (e: Rej) {
//            EqResult(ResultTypes.Res, e.code,e.message)
//        } catch (e: Err) {
//            EqResult(ResultTypes.Res, e.code,e.message)
//        }
//    }
//}
//data class EqResult(val type: ResultTypes, val code: String, val message: String, val payload: Any? = null) {
//    fun getPayloadIfRes() = payload
//    fun throwIfNotRes() = throw Throwable()
//    fun isRes(): Boolean {
//        return type == ResultTypes.Res
//    }
//    fun isRej() = type == ResultTypes.Rej
//    fun isErr() = type == ResultTypes.Err
//}
//enum class ResultTypes {
//    Err, Res, Rej
//}