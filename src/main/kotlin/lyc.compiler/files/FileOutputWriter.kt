package lyc.compiler.files

import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object FileOutputWriter {
    private const val OUTPUT_DIRECTORY = "target/output"
    fun writeOutput(fileName: String?, fileGenerator: FileGenerator) {
        createOutputDirectory()
        try {
            FileWriter(String.format("%s/%s", OUTPUT_DIRECTORY, fileName)).use { fileWriter ->
                fileGenerator.generate(fileWriter)
                fileWriter.flush()
            }
        } catch (e: IOException) {
            System.err.println("Error trying to create file " + e.message)
        }
    }

    private fun createOutputDirectory() {
        val path = Paths.get(OUTPUT_DIRECTORY)
        try {
            Files.createDirectories(path)
        } catch (e: IOException) {
            System.err.println("Error trying to create directory " + e.message)
        }
    }
}