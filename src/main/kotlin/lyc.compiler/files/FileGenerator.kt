package lyc.compiler.files

import java.io.FileWriter
import java.io.IOException

interface FileGenerator {
    @Throws(IOException::class)
    fun generate(fileWriter: FileWriter)
}