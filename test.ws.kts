runBlocking {
    val a = async {
        ""
    }
    println(a.await())
}