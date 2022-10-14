package lyc.compiler.model

import java.io.Serial

class InvalidLengthException(message: String?) : CompilerException(message) {
    companion object {
        @Serial
        private val serialVersionUID = -6649278000190971816L
    }
}