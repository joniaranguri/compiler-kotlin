package lyc.compiler.model

import java.io.Serial

class UnknownCharacterException(unknownInput: String) : CompilerException("Unknown character « $unknownInput »") {
    companion object {
        @Serial
        private val serialVersionUID = -8839023592847976068L
    }
}