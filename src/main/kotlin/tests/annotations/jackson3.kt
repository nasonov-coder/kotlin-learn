package dev.gaodi.fileserver.tests.annotations

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

//fun main() {
//    val mapper = jacksonObjectMapper()
//    val json = mapper.readValue<WhereNode>("""{"and":[{"and":[1,2]},{"and":[1,2]}]}""")
//
//    val map: HashMap<*, *>
////    val fields = json.fields()
//
//    println(json)
//}
//@JsonDeserialize(using = WhereNodeDeserializer::class)
//data class WhereNode(val name: String) {
//
//}
//
//class WhereNodeDeserializer: StdDeserializer<WhereNode>(WhereNode::class.java) {
//    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): WhereNode {
//        val json = p!!.codec.readTree<ObjectNode>(p)
//        val iter = json.fields().
//        json.fi
//        for ((key, value) in iter) {
//            it.
//        }
//        if(json.size() != 1)
//            throw Throwable("Size must be 1, size - ${json.size()}")
//
//        return WhereNode("asd")
//    }
//
//}