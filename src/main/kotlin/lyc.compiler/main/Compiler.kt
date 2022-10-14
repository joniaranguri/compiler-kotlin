package lyc.compiler.main

import lyc.compiler.Parser
import lyc.compiler.constants.Constants.ASSEMBLER_FILENAME
import lyc.compiler.constants.Constants.CANNOT_READ_INPUT_FILE_MESSAGE_ERROR
import lyc.compiler.constants.Constants.COMPILATION_ERROR_MESSAGE
import lyc.compiler.constants.Constants.COMPILATION_SUCCESSFUL_MESSAGE
import lyc.compiler.constants.Constants.INTERMEDIATE_CODE_FILENAME
import lyc.compiler.constants.Constants.NO_FILENAME_PROVIDED_MESSAGE_ERROR
import lyc.compiler.constants.Constants.SYMBOL_TABLE_FILENAME
import lyc.compiler.factories.FileFactory
import lyc.compiler.factories.ParserFactory
import lyc.compiler.files.FileOutputWriter
import lyc.compiler.files.SymbolTableGenerator
import java.io.IOException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println(NO_FILENAME_PROVIDED_MESSAGE_ERROR)
        exitProcess(0)
    }
    try {
        FileFactory.create(args[0]).use { reader ->
            val parser: Parser = ParserFactory.create(reader)
            parser.parse()
            FileOutputWriter.writeOutput(SYMBOL_TABLE_FILENAME, SymbolTableGenerator())
            FileOutputWriter.writeOutput(INTERMEDIATE_CODE_FILENAME, SymbolTableGenerator())
            FileOutputWriter.writeOutput(ASSEMBLER_FILENAME, SymbolTableGenerator())
        }
    } catch (e: IOException) {
        System.err.println("$CANNOT_READ_INPUT_FILE_MESSAGE_ERROR ${e.message}")
        exitProcess(0)
    } catch (e: Exception) {
        System.err.println("$COMPILATION_ERROR_MESSAGE ${e.message}")
        exitProcess(0)
    }
    println(COMPILATION_SUCCESSFUL_MESSAGE)
}