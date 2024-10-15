import org.example.InputDataStream
import org.example.InputDataStreamDecorator

class DecompressInputDataStream (
    stream: InputDataStream
): InputDataStreamDecorator(stream) {
    private var mLastByte: UByte = 0.toUByte()
    private var mCount: UByte = 0.toUByte()

    override fun readByte(): UByte {
        readCompressedBlock()
        --mCount

        return mLastByte
    }

    override fun readBlock(dstBuffer: MutableList<Any>, size: Int): Int {
        var readCount = 0

        readCompressedBlock()

        while (mCount != 0.toUByte() && readCount < size)
        {
            dstBuffer.add(mLastByte)
            --mCount
            ++readCount
        }

        return readCount
    }

    private fun readCompressedBlock() {
        if (mCount == 0.toUByte()) {
            mLastByte = super.readByte()
            mCount = super.readByte()
        }
    }
}