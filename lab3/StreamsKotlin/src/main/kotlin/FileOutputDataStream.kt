package org.example

import java.io.File
import java.io.FileOutputStream

class FileOutputDataStream(
    fileName: String
): OutputDataStream {
    private val mFileStream: FileOutputStream
    private var mClose = false
    private var mFile: File = File(fileName)

    init {
        if(!mFile.exists()) mFile.createNewFile()
        mFileStream = FileOutputStream(mFile)
    }

    override fun writeByte(data: UByte) {
        if(mClose) throw LogicError("The file is closed")

        mFileStream.write(data.toInt())
    }

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int {
        var counter = 0
        for (i in 0 until size) {
            if(mClose) throw LogicError("The file is closed")
            if(counter >= srcData.size) break
            mFileStream.write((srcData[i] as UByte).toInt())
            ++counter
        }
        return counter
    }

    override fun close() { mClose = true }
}