package lyc.compiler.files

import java.io.FileWriter
import java.io.IOException

class IntermediateCodeGenerator : FileGenerator {
    @Throws(IOException::class)
    override fun generate(fileWriter: FileWriter) {
        fileWriter.write("TODO")
    }
}