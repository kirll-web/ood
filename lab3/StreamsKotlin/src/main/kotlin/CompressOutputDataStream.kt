import org.example.OutputDataStream
import org.example.OutputDataStreamDecorator

class CompressOutputDataStream (
    stream: OutputDataStream
): OutputDataStreamDecorator(stream) {
    private var mLastByte: UByte = 0U
    private var mCount: UByte = 0U

    override fun writeByte(data: UByte) {
        writeBlock(mutableListOf(data), 1)
    }

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int {
        var pos = 0

        while (pos < size)
        {
            if (mCount != 0.toUByte() && mLastByte != srcData[pos])
            {
                writeCompressedBlock()
            }

            mLastByte = srcData[pos] as UByte
            ++mCount

            if (mCount == 255.toUByte())
            {
                writeCompressedBlock()
            }

            ++pos
        }

        writeCompressedBlock()
        return pos
    }

    private fun writeCompressedBlock() {
        super.writeByte(mLastByte)
        super.writeByte(mCount)

        mCount = 0U
    }
}