package z.dev.nasonov.neo4j

import org.neo4j.ogm.annotation.Relationship
import java.util.HashSet

abstract interface DbDepth

interface DbOne: DbDepth {
//    var parents: Set<DbOne>
//    var includes: Set<DbOne>
}
interface DbUp: DbDepth {
//    var parents: Set<DbUp>
}
interface DbDown: DbDepth {
//    var includes: Set<DbDown>
}
interface DbSolo: DbDepth {
//    var includes: Set<DbDown>
}