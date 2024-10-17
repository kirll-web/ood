import org.example.OutputDataStream
import org.example.OutputDataStreamDecorator

class CompressOutputDataStream (
    stream: OutputDataStream
): OutputDataStreamDecorator(stream) {
    private var mLastByte: UByte? = null
    private var mCountUniq: UByte = 0U
    private var mCountNotUniq: UByte = 0U
    private var mNotUniqBytes: MutableList<UByte> = mutableListOf()


    override fun writeByte(data: UByte) {
        writeBlock(mutableListOf(data), 1)
    }

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int {
        var pos = 0

        while (pos < size)
        {
            if(mLastByte == null) {
                mLastByte = srcData[pos] as UByte
                continue
            }

            if(mLastByte != null && mLastByte != srcData[pos]) {
                ++mCountNotUniq
                mNotUniqBytes.add(mLastByte!!)

                if(mCountUniq > 0.toUByte()) {
                    writeCompressedBlock()
                }
            }

            if (mLastByte != null && mLastByte == srcData[pos])
            {
                ++mCountUniq
                if(mCountUniq > 0.toUByte()) {
                    writeCompressedBlock()
                }
            }

            if (mCountUniq == 255.toUByte())
            {
                writeCompressedBlock()
            }

            if (mCountNotUniq == 255.toUByte())
            {
                writeCompressedBlock()
            }

            ++pos
        }

        writeCompressedBlock()
        return pos
    }

    private fun writeCompressedBlock() {
        if(mCountUniq != 0.toUByte()) {

        }

        mCountUniq = 0U
    }
}