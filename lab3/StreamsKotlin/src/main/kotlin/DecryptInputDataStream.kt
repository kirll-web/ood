package org.example

class DecryptInputDataStream (
    stream: InputDataStream,
    key: UInt
): InputDataStreamDecorator(stream) {
    private var mCrypto = Crypto(key)

    override fun readByte() = mCrypto.decrypt(super.readByte())

    override fun readBlock(dstBuffer: MutableList<Any>, size: Int): Int {
        val length = super.readBlock(dstBuffer, size)
        val newList = dstBuffer.map {
            mCrypto.decrypt(it as UByte)
        }.toMutableList()

        dstBuffer.clear()

        newList.forEach {
            dstBuffer.add(it)
        }

        return length
    }
}