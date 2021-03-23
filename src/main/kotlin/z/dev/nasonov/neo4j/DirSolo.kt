package z.dev.nasonov.neo4j

import org.neo4j.ogm.annotation.NodeEntity

@NodeEntity(label = "Dir")
class DirSolo: Directory(), DbSolo {
    override fun toString(): String {
        return "DirSolo(id=$id, name=$name])"
    }
}