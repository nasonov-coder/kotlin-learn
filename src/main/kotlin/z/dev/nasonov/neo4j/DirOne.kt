package z.dev.nasonov.neo4j


import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.HashSet
@NodeEntity(label = "Dir")
class DirOne: Directory(), DbOne {
    @Relationship(type = "included", direction = Relationship.OUTGOING)
    var parents: Set<DirOne> = HashSet()
    @Relationship(type = "included", direction = Relationship.INCOMING)
    var includes: Set<DirOne> = HashSet()
    private fun toStringSingle(): String {
        return "DirOne(id=$id, name=$name)"
    }
    override fun toString(): String {
        return "DirOne(id=$id, name=$name, parents=[${parents.joinToString{ it.toStringSingle()}}], includes=[${includes.joinToString{ it.toStringSingle()}}])"
    }
}