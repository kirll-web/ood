package org.example

import java.io.IOException

class MemoryInputStream(
    stream: MutableList<UByte>
): InputDataStream {
    private val mMemoryStream = stream
    private var mIsClosed = false
    
    override fun isEOF() = mMemoryStream.isEmpty()


    override fun readByte(): UByte {
        if(isEOF()) throw IOException("The end of the memory stream has been reached")

        return mMemoryStream.removeLast()
    }


    override fun readBlock(dstBuffer: MutableList<Any>, size: Int): Int {
        if(isEOF()) throw IOException("The end of the memory stream has been reached")
        if(mIsClosed) throw IOException("The memory stream is closed")
        var readCounter = 0

        while (readCounter < size && !isEOF()) {
            if(mIsClosed) throw LogicError("The file is closed")
            dstBuffer.add(mMemoryStream.removeLast())
            ++readCounter
        }

        return readCounter
    }

    // Закрывает поток. Операции над ним после этого должны выбрасывать исключение logic_error
    override fun close() { mIsClosed = true }
}