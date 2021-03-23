package z.dev.nasonov.neo4j

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.HashSet
@NodeEntity(label = "Dir")
class DirDown: Directory(), DbDown {
    @Relationship(type = "included", direction = Relationship.INCOMING)
    var includes: Set<DirDown> = HashSet()
    override fun toString(): String {
        return "DirDown(id=$id, name=$name, includes=[${includes.joinToString()}])"
    }
}