package z.dsl
//
//operator fun Int.rangeTo(x: Int) = TimeRange(this, x)
//
//data class TimeRange(val a: Int, val b: Int)
//
//fun main() {
//    println(11_10..11_15)
//    overwatch{
//        game {
//
//        }
//    }
//}
//object overwatch {
//    operator fun invoke(init: OverwatchContext.() -> Unit) = OverwatchContext().init()
//}
//@MyCustomDslMarker
//class OverwatchContext {
//    fun game(init: DataContext.() -> Unit): SchedulingResults {
//        val context = DataContext().apply(init)
//        val dataSet = context.buildDataSet()
//        val scheduler = Scheduler()
//        return scheduler.schedule(dataSet)
//    }
//}
//class GameContext {
//    private val players: List<String>
//}
//@DslMarker
//annotation class MyCustomDslMarker

