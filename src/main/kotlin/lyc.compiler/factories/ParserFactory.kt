package lyc.compiler.factories

import lyc.compiler.Parser;

import java.io.Reader

object ParserFactory {
    fun create(input: String): Parser {
        return Parser(LexerFactory.create(input))
    }

    fun create(reader: Reader): Parser {
        return Parser(LexerFactory.create(reader))
    }
}