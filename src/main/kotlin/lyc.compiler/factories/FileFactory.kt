package lyc.compiler.factories

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object FileFactory {
    @Throws(IOException::class)
    fun create(filename: String): BufferedReader {
        return BufferedReader(FileReader(filename))
    }
}