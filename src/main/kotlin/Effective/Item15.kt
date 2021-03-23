package Effective

/**
 * Consider referencing receivers explicitly
 */
class Item15 {
}
class Node(val name: String) {
    fun makeChild(childName: String) = create("$name.$childName").apply{ println("created $name")}

    private fun create(name: String): Node? = Node(name)
}

fun main() {
    val node = Node("parent")
    node.makeChild("child")
}