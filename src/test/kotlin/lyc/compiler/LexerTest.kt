package lyc.compiler

import com.google.common.truth.Truth.assertThat
import lyc.compiler.constants.Constants.MAX_LENGTH
import lyc.compiler.factories.LexerFactory
import lyc.compiler.model.CompilerException
import lyc.compiler.model.InvalidIntegerException
import lyc.compiler.model.InvalidLengthException
import lyc.compiler.model.UnknownCharacterException
import org.apache.commons.text.CharacterPredicates
import org.apache.commons.text.RandomStringGenerator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.IOException

@Disabled
class LexerTest {
    private var lexer: Lexer? = null

    @Test
    @Throws(Exception::class)
    fun comment() {
        scan("/*This is a comment*/")
        assertThat(nextToken()).isEqualTo(ParserSym.EOF)
    }

    @Test
    fun invalidStringConstantLength() {
        assertThrows(InvalidLengthException::class.java) {
            scan(String.format("\"%s\"", getRandomString()))
            nextToken()
        }
    }

    @Test
    fun invalidIdLength() {
        assertThrows(InvalidLengthException::class.java) {
            scan(getRandomString())
            nextToken()
        }
    }

    @Test
    fun invalidPositiveIntegerConstantValue() {
        assertThrows(InvalidIntegerException::class.java) {
            scan(String.format("%d", 9223372036854775807L))
            nextToken()
        }
    }

    @Test
    fun invalidNegativeIntegerConstantValue() {
        assertThrows(InvalidIntegerException::class.java) {
            scan(String.format("%d", -9223372036854775807L))
            nextToken()
        }
    }

    @Test
    @Throws(Exception::class)
    fun assignmentWithExpressions() {
        scan("c=d*(e-21)/4")
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER)
        assertThat(nextToken()).isEqualTo(ParserSym.ASSIG)
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER)
        assertThat(nextToken()).isEqualTo(ParserSym.MULT)
        assertThat(nextToken()).isEqualTo(ParserSym.OPEN_BRACKET)
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER)
        assertThat(nextToken()).isEqualTo(ParserSym.SUB)
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT)
        assertThat(nextToken()).isEqualTo(ParserSym.CLOSE_BRACKET)
        assertThat(nextToken()).isEqualTo(ParserSym.DIV)
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT)
        assertThat(nextToken()).isEqualTo(ParserSym.EOF)
    }

    @Test
    fun unknownCharacter() {
        assertThrows(UnknownCharacterException::class.java) {
            scan("#")
            nextToken()
        }
    }

    @AfterEach
    fun resetLexer() {
        lexer = null
    }

    private fun scan(input: String) {
        lexer = LexerFactory.create(input)
    }

    @Throws(IOException::class, CompilerException::class)
    private fun nextToken(): Int {
        return lexer!!.next_token().sym
    }

    private fun getRandomString(): String =
        RandomStringGenerator.Builder().filteredBy(CharacterPredicates.LETTERS).withinRange('a'.code, 'z'.code).build()
            .generate(MAX_LENGTH * 2)
}