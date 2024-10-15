package org.example

open class InputDataStreamDecorator(
    private val stream: InputDataStream
): InputDataStream {
    override fun readByte(): UByte = stream.readByte()

    override fun isEOF() = stream.isEOF()

    override fun readBlock(dstBuffer: MutableList<Any>, size: Int) = stream.readBlock(
        dstBuffer, size
    )

    override fun close() = stream.close()
}