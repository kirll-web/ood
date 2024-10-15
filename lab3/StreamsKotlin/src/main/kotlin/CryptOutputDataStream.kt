package org.example


class CryptOutputDataStream(
    stream: OutputDataStream,
    key: UInt
): OutputDataStreamDecorator(stream) {
    private var mCrypto = Crypto(key)

    override fun writeByte(data: UByte) {
        super.writeByte(mCrypto.crypt(data))
    }

    override fun writeBlock(srcData: MutableList<Any>, size: Int): Int {
        val newData = srcData.map {
            mCrypto.crypt(it as UByte)
        }.toMutableList()

        return super.writeBlock(newData as MutableList<Any>, size)
    }
}