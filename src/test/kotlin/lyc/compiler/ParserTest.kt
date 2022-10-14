package lyc.compiler

import java_cup.runtime.Symbol
import lyc.compiler.factories.ParserFactory
import org.apache.commons.io.IOUtils
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.charset.StandardCharsets
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertThrows

@Disabled
class ParserTest {

    @Test
    @Throws(Exception::class)
    fun assignmentWithExpression() {
        compilationSuccessful("c=d*(e-21)/4")
    }

    @Test
    fun syntaxError() {
        compilationError("1234")
    }

    @Test
    @Throws(Exception::class)
    fun assignments() {
        compilationSuccessful(readFromFile("assignments.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun write() {
        compilationSuccessful(readFromFile("write.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun read() {
        compilationSuccessful(readFromFile("read.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun comment() {
        compilationSuccessful(readFromFile("comment.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun init() {
        compilationSuccessful(readFromFile("init.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun and() {
        compilationSuccessful(readFromFile("and.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun or() {
        compilationSuccessful(readFromFile("or.txt"))
    }

    @Test
    @Throws(Exception::class)
    operator fun not() {
        compilationSuccessful(readFromFile("not.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun ifStatement() {
        compilationSuccessful(readFromFile("if.txt"))
    }

    @Test
    @Throws(Exception::class)
    fun whileStatement() {
        compilationSuccessful(readFromFile("while.txt"))
    }

    @Throws(Exception::class)
    private fun compilationSuccessful(input: String) {
        assertThat(scan(input).sym).isEqualTo(ParserSym.EOF)
    }

    private fun compilationError(input: String) {
        assertThrows(Exception::class.java) { scan(input) }
    }

    @Throws(Exception::class)
    private fun scan(input: String): Symbol {
        return ParserFactory.create(input).parse()
    }

    @Throws(IOException::class)
    private fun readFromFile(fileName: String): String {
        val resource = javaClass.getResourceAsStream(String.format("/%s", fileName))
        assertThat(resource).isNotNull()
        return IOUtils.toString(resource, StandardCharsets.UTF_8)
    }
}
