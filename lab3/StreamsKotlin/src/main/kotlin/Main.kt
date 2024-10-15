package org.example

import CompressOutputDataStream
import DecompressInputDataStream

const val ENCRYPT_COMMAND = "--encrypt"
const val DECRYPT_COMMAND = "--decrypt"
const val COMPRESS_COMMAND = "--compress"
const val DECOMPRESS_COMMAND = "--decompress"
const val BUFFER_SIZE = 255

enum class CommandKey(val key: String) {
    ENCRYPT(ENCRYPT_COMMAND),
    DECRYPT(DECRYPT_COMMAND),
    COMPRESS(COMPRESS_COMMAND),
    DECOMPRESS(DECOMPRESS_COMMAND);
}

data class Command(val key: CommandKey, val value: UInt? = null)

data class Args(
    val commands: List<Command>,
    val inputFileName: String,
    val outputFileName: String
)

fun parseCommandLine(commandLine: String): Args {
    val words = commandLine.trim().split(" ")
    var i = 0

    return Args(
        commands = buildList {
            while (i < words.size) {
                val it = words[i]
                when (it) {
                    DECRYPT_COMMAND -> {
                        ++i
                        add(getKeyValueCommand(CommandKey.DECRYPT, words[i]))
                    }

                    ENCRYPT_COMMAND -> {
                        ++i
                        add(getKeyValueCommand(CommandKey.ENCRYPT, words[i]))
                    }

                    DECOMPRESS_COMMAND -> add(Command(key = CommandKey.DECOMPRESS))

                    COMPRESS_COMMAND -> add(Command(key = CommandKey.COMPRESS))
                }
                ++i
            }
        },
        inputFileName = getFileName(words[words.size - 2]),
        outputFileName = getFileName(words[words.size - 1])
    )
}

fun getFileName(word: String) = when {
    CommandKey.entries.map { it.key }.contains(word) ->
        throw IllegalArgumentException("The input file was not passed to the arguments")

    else -> word
}

fun getKeyValueCommand(key: CommandKey, value: String) = try {
    Command(key = key, value = value.toUInt())
} catch (ex: Exception) {
    throw IllegalArgumentException("key for decrypt is not number")
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Example: --decompress --decrypt 100500 --decrypt 3 output.dat input.dat.restored")
    println("Input command: ")
    val args = parseCommandLine(readln())

    var inputFileStream: InputDataStream = FileInputDataStream(args.inputFileName)
    var outputFileStream: OutputDataStream = FileOutputDataStream(args.outputFileName)

    args.commands.forEach { command ->
        when (command.key) {
            CommandKey.ENCRYPT,
            CommandKey.COMPRESS-> {
                outputFileStream = decorateOutput(outputFileStream, command)
            }

            CommandKey.DECRYPT,
            CommandKey.DECOMPRESS -> {
                inputFileStream = decorateInput(inputFileStream, command)
            }
        }
    }

    var size: Int


    while (!inputFileStream.isEOF())
    {
        val buffer = mutableListOf<UByte>()
        size = inputFileStream.readBlock(buffer as MutableList<Any>, BUFFER_SIZE)

        if (size > 0) outputFileStream.writeBlock(buffer, size)
    }
}

fun decorateOutput(stream: OutputDataStream, command: Command): OutputDataStream =
    when (command.key) {
        CommandKey.ENCRYPT -> CryptOutputDataStream(stream, command.value!!)
        CommandKey.COMPRESS -> CompressOutputDataStream(stream)
        else -> throw IllegalArgumentException("This command not for output")
    }

fun decorateInput(stream: InputDataStream, command: Command): InputDataStream = when (command.key) {
    CommandKey.DECRYPT -> DecryptInputDataStream(stream, command.value!!)
    CommandKey.DECOMPRESS -> DecompressInputDataStream(stream)
    else -> throw IllegalArgumentException("This command not for input")
}
