package lyc.compiler.factories

import lyc.compiler.Lexer
import java.io.Reader
import java.io.StringReader

object LexerFactory {
    fun create(input: String): Lexer {
        val reader: Reader = StringReader(input)
        return create(reader)
    }

    fun create(reader: Reader): Lexer {
        return Lexer(reader)
    }
}