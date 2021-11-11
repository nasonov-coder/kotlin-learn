package dev.gaodi.fileserver.netty

import org.neo4j.driver.internal.shaded.io.netty.buffer.ByteBuf
import org.neo4j.driver.internal.shaded.io.netty.buffer.Unpooled

fun byteBuf(buffBuilder: ByteBuf.()->Unit): ByteBuf {
    val buf =
        Unpooled.buffer().apply(buffBuilder)

    val bufSize = buf?.readableBytes() ?: 0
    return Unpooled.buffer(bufSize)
        .writeBytes(buf)
}