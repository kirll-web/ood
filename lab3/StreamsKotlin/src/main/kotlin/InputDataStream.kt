package org.example

interface InputDataStream {
    fun isEOF(): Boolean

    // Считывает байт из потока.
    // Выбрасывает исключение std::ios_base::failure в случае ошибки
    fun readByte(): UByte

    // Считывает из потока блок данных размером size байт, записывая его в память
    // по адресу dstBuffer
    // Возвращает количество реально прочитанных байт. Выбрасывает исключение в случае ошибки
    fun readBlock(dstBuffer:  MutableList<Any>, size: Int): Int

    // Закрывает поток. Операции над ним после этого должны выбрасывать исключение logic_error
    fun close()
}