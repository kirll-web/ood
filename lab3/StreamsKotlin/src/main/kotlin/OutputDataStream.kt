package org.example

interface OutputDataStream {
    fun writeByte(data: UByte)

    fun writeBlock(srcData: MutableList<Any>, size: Int): Int

    fun close()
}