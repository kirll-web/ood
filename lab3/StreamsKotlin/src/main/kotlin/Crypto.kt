package org.example

import kotlin.random.Random

class Crypto(
    key: UInt
) {
    private val mCrypt = mutableListOf<UByte>()
    private val mDecrypt = mutableListOf<UByte>()


    init {
        for (i in 0 until TABLE_SIZE) {
            mCrypt.add(i.toUByte())
            mDecrypt.add(0.toUByte())
        }

        val generator = Random(key.toInt())
        mCrypt.shuffle(generator)

        mCrypt.forEachIndexed { i, it ->
            mDecrypt[it.toInt()] = i.toUByte()
        }
    }


    fun crypt(data: UByte) = mCrypt[data.toInt()]

    fun decrypt(data: UByte) = mDecrypt[data.toInt()]


    companion object {
        const val TABLE_SIZE = 256
    }
}