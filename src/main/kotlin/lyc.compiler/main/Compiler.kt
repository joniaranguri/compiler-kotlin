package lyc.compiler.main

import lyc.compiler.Parser
import lyc.compiler.factories.FileFactory
import lyc.compiler.factories.ParserFactory
import lyc.compiler.files.FileOutputWriter
import lyc.compiler.files.SymbolTableGenerator
import java.io.IOException
import kotlin.system.exitProcess
 fun main(args: Array<String>) {
        if (args.size != 1) {
            println("Filename must be provided as argument.")
            exitProcess(0)
        }
        try {
            FileFactory.create(args[0]).use { reader ->
                val parser: Parser = ParserFactory.create(reader)
                parser.parse()
                FileOutputWriter.writeOutput("symbol-table.txt", SymbolTableGenerator())
                FileOutputWriter.writeOutput("intermediate-code.txt", SymbolTableGenerator())
                FileOutputWriter.writeOutput("final.asm", SymbolTableGenerator())
            }
        } catch (e: IOException) {
            System.err.println("There was an error trying to read input file " + e.message)
            exitProcess(0)
        } catch (e: Exception) {
            System.err.println("Compilation error: " + e.message)
            exitProcess(0)
        }
        println("Compilation Successful")
    }