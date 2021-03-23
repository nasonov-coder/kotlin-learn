package z.dev.nasonov.neo4j

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.HashSet
@NodeEntity(label = "Dir")
class DirUp: Directory(), DbUp {
    @Relationship(type = "included", direction = Relationship.OUTGOING)
    var parents: Set<DirUp> = HashSet()
    override fun toString(): String {
        return "DirUp(id=$id, name=$name, parents=[${parents.joinToString()}])"
    }
}