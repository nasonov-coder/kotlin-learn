package dev.gaodi.fileserver.tests.annotations

fun main() {
    val c = Resp::class.java.enumConstants
}

enum class Resp(val code: String, val message: String) {
    NoHits("200", "No hits"),
    Conflict("40*", "Conflict")
}
/*
    - path: doc/count
      payload: ~
      args: ~
      res:
        size: number
      rej:
        - code: 200
          message: No hits
        - code: 403
          message: Conflict

 */





class Args(
    val number: Number
)
/*
 {
    coords: [12.342423423,12.324234234]
 }
 */
class Payload(
    val coords: List<Number>
)
class Res(
    @Desc("count of list")
    val size: Number
)






annotation class Desc(val desc: String)













fun <Args, Payload, Res, Rej>task(path: String, func: TaskPipe<Res, Rej>.()->Unit){}
class TaskPipe<Res, Rej> {
    fun res(el: Res) {

    }
    fun rej(el: Rej) {

    }
}
