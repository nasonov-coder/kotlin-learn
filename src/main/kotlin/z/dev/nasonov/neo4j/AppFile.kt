package z.dev.nasonov.neo4j

import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.Session
import org.neo4j.ogm.session.SessionFactory


fun main() {
    Db.configuration = Configuration.Builder()
        .uri("bolt://localhost")
        .credentials("neo4j", "Eng7pt143?nc")
        .build()
//    val sessionFactory = SessionFactory(configuration, "z.dev.nasonov.neo4j.DirUp", "z.dev.nasonov.neo4j.DirDown")
//    val session: Session = sessionFactory.openSession()
//    val root = Dir("root").apply {
//        includes += Dir("lvl1").apply {
//            includes += Dir("lvl2")
//        }
//    }
//    session.save(root)
//    session.load(Dir::class.java, 5L, 10).run(::println)
//    session.load(Dir::class.java, 3L, -1).run(::println)
//    var lvl1 = session.load(DirUp::class.java, 3L, -1).apply(::println)
//    session.load(DirDown::class.java, 3L, -1).run(::println)
    //lvl1.name = "lvl1.1"
//    session.save(lvl1)
//    val lvl1Up: DirUp = Db.loadUp(3L)
//    println(lvl1Up)
//    val lvl1Down: DirDown = Db.loadDown(3L)
//    println(lvl1Down)

    val rootCypher = """MATCH (p:Dir)
 WHERE NOT (p)-[:included]->()
 RETURN p"""
    Db.Down.query<DirDown>(rootCypher).run(::println)
    Db.Solo.load<DirSolo>(8L).apply(::println)
    Db.One.load<DirOne>(6L).apply(::println)
//        Db.sessionDown.loadAll(DirDown::class.java, Filter("name", ComparisonOperator.EQUALS, "root"), 0).apply(::println)
//    Db.sessionUp.query(DirUp::class.java, rootCypher, mapOf<String, Any>()).apply(::println)
    println("done")
}


object Db {
    lateinit var configuration: Configuration

    abstract class Depth<K: DbDepth> {
        inline fun <reified T: K> t(): String {
            return T::class.java.name
        }
        abstract val session: Session
        open val MAX_DEPTH = 100
        inline fun <reified T: K> load(id: Long): T {
            return session.load(T::class.java, id, MAX_DEPTH)
        }
        inline fun <reified T: K> query(cypher: String, params: Map<String, Any?> = mapOf()): MutableIterable<T>? {
            return session.query(T::class.java, cypher, params)
        }

    }
    object Up: Depth<DbUp>() {

        override val session: Session = SessionFactory(configuration, t<DirUp>()).openSession()
    }
    object Down: Depth<DbDown>() {

        override val session: Session = SessionFactory(configuration, t<DirDown>()).openSession()
    }
    object Solo: Depth<DbSolo>() {

        override val MAX_DEPTH: Int = 0
        override val session: Session = SessionFactory(configuration, t<DirSolo>()).openSession()
    }
    object One: Depth<DbOne>() {
        override val MAX_DEPTH: Int = 1
        override val session: Session = SessionFactory(configuration, t<DirOne>()).openSession()
    }
}

