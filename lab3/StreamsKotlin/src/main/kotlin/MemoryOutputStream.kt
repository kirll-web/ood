package org.example

class MemoryOutputStream(
    stream: MutableList<UByte>
): OutputDataStream {
    private val mMemoryStream = stream
    private var mClose = false

    override fun writeByte(data: UByte) {
        if(mClose) throw LogicError("The file is closed")

        mMemoryStream.add(data)
    }

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int {
        var counter = 0
        for (i in 0 until size) {
            if(mClose) throw LogicError("The file is closed")
            if(counter >= srcData.size) break
            mMemoryStream.add(srcData[i] as UByte)
            ++counter
        }

        return counter
    }

    override fun close() { mClose = true }
}