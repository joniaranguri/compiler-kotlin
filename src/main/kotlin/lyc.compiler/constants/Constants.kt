package lyc.compiler.constants

object Constants {
    // Messages
    const val NO_FILENAME_PROVIDED_MESSAGE_ERROR = "Filename must be provided as argument."
    const val CANNOT_READ_INPUT_FILE_MESSAGE_ERROR = "There was an error trying to read input file"
    const val COMPILATION_ERROR_MESSAGE = "Compilation error:"
    const val COMPILATION_SUCCESSFUL_MESSAGE = "Compilation Successful"

    // File names
    const val SYMBOL_TABLE_FILENAME = "symbol-table.txt"
    const val INTERMEDIATE_CODE_FILENAME = "intermediate-code.txt"
    const val ASSEMBLER_FILENAME = "final.asm"

    // Values
    const val MAX_LENGTH = 30
}