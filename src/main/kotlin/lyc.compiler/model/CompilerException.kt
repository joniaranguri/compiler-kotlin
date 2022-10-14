package lyc.compiler.model

import java.io.Serial

abstract class CompilerException(message: String?) : Exception(message) {
    companion object {
        @Serial
        private val serialVersionUID = -3138875452688305726L
    }
}