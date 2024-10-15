package org.example

open class OutputDataStreamDecorator(
    private val stream: OutputDataStream
): OutputDataStream {
    override fun writeByte(data: UByte) = stream.writeByte(data)

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int = stream.writeBlock(srcData, size)

    override fun close() = stream.close()
}