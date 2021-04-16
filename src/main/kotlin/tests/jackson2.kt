package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.JsonNode
import dev.gaodi.fileserver.Noder
import tests.sout


@JsonFormat(shape = Shape.ARRAY)
@JsonPropertyOrder("aString", "bString", "content", "cInt")
internal class Foo {
    class FooContent {
        var some: String? = null
        var goes: String? = null
        override fun toString(): String {
            return "FooContent(some=$some, goes=$goes)"
        }
    }

    @JsonFormat(shape = Shape.STRING)
    var aString: String? = null

    @JsonFormat(shape = Shape.STRING)
    var bString: String? = null
    var content: FooContent? = null
    @JsonFormat(shape = Shape.NUMBER)
    var cInt: Int? = null
    override fun toString(): String {
        return "Foo(aString=$aString, bString=$bString, content=$content, cInt=$cInt)"
    }
}

fun main() {
    val json = """
        ["anchor-sign",[["receiver_conn_id","sender_conn_id"],["meta1","meta2"],"anchor"], {"a":1,"b":2} ]
    """.trimIndent()
    val mapper = Noder.mapper
//    val obj = mapper.readValue<AuthPipeline<Call>>(json)
    val obj = Noder.fromJson<AuthMsg<JsonNode>>(json)
    mapper.writeValueAsString(obj).sout()
    println(obj)
//    println(obj.call)

//    val j: String = obj.call
//    println(j::class.java)
    val obj2: AuthMsg<Call> = obj.replaceCall(Noder.fromNode(obj.call))
    obj2.sout()
}

//["anchor-sign",[[receiver_conn_id,sender_conn_id],[meta],"anchor"], call ]
@JsonFormat(shape = Shape.ARRAY)
//@JsonPropertyOrder("anchorSign", "head", "call")
data class AuthMsg<CallSchema>(
    @JsonFormat(shape = Shape.STRING)
    val anchorSign: String,
    val head: Head,
    val call: CallSchema,
){
    @JsonFormat(shape = Shape.ARRAY)
//    @JsonPropertyOrder("conn", "meta", "anchor")
    data class Head(
        val conn: Conn,
        @JsonFormat(shape = Shape.ARRAY)
        val meta: List<String>,
        @JsonFormat(shape = Shape.STRING)
        val anchor: String,
    ) {
        @JsonFormat(shape = Shape.ARRAY)
//        @JsonPropertyOrder("receiverConnId", "senderConnId")
        data class Conn(
            @JsonFormat(shape = Shape.STRING)
            val receiverConnId: String,
            @JsonFormat(shape = Shape.STRING)
            val senderConnId: String,
        )
    }
    fun <CallSchema>replaceCall(call: CallSchema): AuthMsg<CallSchema> {
        return AuthMsg(anchorSign, head.copy(), call)
    }
}
data class Call (
    val a: Number,
    val b: Number
)


@JsonFormat(shape = Shape.ARRAY)
//@JsonPropertyOrder("anchorSign", "head", "call")
data class TaskMsg<CallSchema>(
    @JsonFormat(shape = Shape.STRING)
    val anchorSign: String,
    val head: Head,
    val call: CallSchema,
){
    @JsonFormat(shape = Shape.ARRAY)
//    @JsonPropertyOrder("conn", "meta", "anchor")
    data class Head(
        val conn: Conn,
        @JsonFormat(shape = Shape.ARRAY)
        val meta: List<String>,
        @JsonFormat(shape = Shape.STRING)
        val anchor: String,
    ) {
        @JsonFormat(shape = Shape.ARRAY)
//        @JsonPropertyOrder("receiverConnId", "senderConnId")
        data class Conn(
            @JsonFormat(shape = Shape.STRING)
            val receiverConnId: String,
            @JsonFormat(shape = Shape.STRING)
            val senderConnId: String,
        )
    }
    fun <CallSchema>replaceCall(call: CallSchema): TaskMsg<CallSchema> {
        return TaskMsg(anchorSign, head.copy(), call)
    }

}

//
//data class Msg<CallSchema, HeadSchema>(
//    @JsonFormat(shape = Shape.STRING)
//    val anchorSign: String,
//    val head: HeadSchema,
//    val call: CallSchema
//) {
//    fun <T, CallSchema> T.replaceCall(call: CallSchema): TaskMsg<CallSchema> {
//        return TaskMsg(anchorSign, head, call)
//    }
//}