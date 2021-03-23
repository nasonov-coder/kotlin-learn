package tests

import dev.gaodi.fileserver.Noder
import kotlin.reflect.KClass

fun main() {
//    val result = listOf("", "")
//    val jsonObject = Json
//    jsonObject.put(row.getString("id"), row)
//    val mapper = ObjectMapper()
//    mapper.createObjectNode();
//    val node = mapper.valueToTree<JsonNode>(jsonObject, )
      Noder.fromJson<Keks>("""{"lol":"File"}""").sout("kek")
    JsonTypes.getType<DirC>().sout()
    val f = FileC()
    f.toJson().sout()

}
enum class JsonTypes(val kClass: KClass<out Gen>) {
    File(FileC::class), Dir(DirC::class);

    companion object {
        inline fun <reified T> getType(): JsonTypes? {
            values().forEach {
                if (it.kClass == T::class)
                    return it
            }
            return null
        }
    }

}
data class Keks(
    val lol: JsonTypes
)
inline fun <reified T: Gen>toKek() {
    JsonTypes.values().forEach {
        println("$it " + (it.kClass == T::class))
    }
}
fun <T> T.sout(): T = apply(::println)
fun <T> T.sout(msg: String): T {
    println("$msg: $this")
    return this
}
open class Gen: Noder() {
    val keks: String = "asdasda"
}
class FileC: Gen() {
    val lols: String = "adasdasdasdasdasd"
}
class DirC: Gen()
