import dev.gaodi.fileserver.flow.sample
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

val channel = Channel<Unit>()
thread {
    runBlocking {
        sample().collect {
            channel.receive()
            println(it)
        }
    }
}
println("ready")
thread {
    runBlocking {
        channel.send(Unit)
    }
}



