package z.dev.nasonov.neo4j

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
open class Directory {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String? = null

    override fun toString(): String {
        return "Dir(id=$id, name=$name)"
    }
}