package org.example

import java.io.File
import java.io.FileInputStream
import java.io.IOException


class FileInputDataStream(
    fileName: String
): InputDataStream {
    private val mFileStream: FileInputStream
    
    private var mIsClosed = false
    

    init {
        val file = File(fileName)
        if(!file.exists()) throw IOException("The file with name $fileName is cannot be openned")
        mFileStream = FileInputStream(file)
    }

    override fun isEOF() = mFileStream.available() == 0


    override fun readByte(): UByte {
        if(isEOF()) throw IOException("The end of the file has been reached")

        return mFileStream.read().toUByte()
    }


    override fun readBlock(dstBuffer: MutableList<Any>, size: Int): Int {
        if(isEOF()) throw IOException("The end of the file has been reached")
        if(mIsClosed) throw LogicError("The file is closed")
        var readCounter = 0

        while (readCounter < size && !isEOF()) {
            if(mIsClosed) throw LogicError("The file is closed")
            dstBuffer.add(readByte())
            ++readCounter
        }

        return readCounter
    }

    // Закрывает поток. Операции над ним после этого должны выбрасывать исключение logic_error
    override fun close() { mIsClosed = true }
}