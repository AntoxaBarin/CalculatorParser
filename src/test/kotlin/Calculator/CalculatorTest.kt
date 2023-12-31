package Calculator

import org.graalvm.shadowed.org.antlr.v4.runtime.ANTLRInputStream
import org.graalvm.shadowed.org.antlr.v4.runtime.BailErrorStrategy
import org.graalvm.shadowed.org.antlr.v4.runtime.CharStreams
import org.graalvm.shadowed.org.antlr.v4.runtime.CommonTokenStream
import org.graalvm.shadowed.org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Path

fun isEqualFiles(firstFile: Path, secondFile: Path): Boolean {    // Сравнивает два файла
    if (Files.size(firstFile) != Files.size(secondFile)) {
        return false
    }
    Files.newBufferedReader(firstFile).use { bf1 ->
        Files.newBufferedReader(secondFile).use { bf2 ->
            var ch: Int
            while (bf1.read().also { ch = it } != -1) {
                if (ch != bf2.read()) {
                    return false
                }
            }
        }
    }
    return true
}

class CalculatorTest {

    @Test
    fun basicOperationsTest() {
        val expression = """1 + 1;
                            2 * 7;
                            2 + 2 * 2;
                            333 + 111;
                            0;
                         """
        val lexer = calculatorLexer(ANTLRInputStream(expression))
        val tokens = CommonTokenStream(lexer)
        val parser = calculatorParser(tokens)
        val calc = CalcVisitor()
        calc.visit(parser.start())

        val results = calc.calculated_values.split("|")
        assertEquals(results[0], "1+1=2;")
        assertEquals(results[1], "2*7=14;")
        assertEquals(results[2], "2+2*2=6;")
        assertEquals(results[3], "333+111=444;")
        assertEquals(results[4], "0=0;")
    }

    @Test
    fun SkobochkiTest() {
        val expression = """(1 + 1) * 100;
                            ((1 + 1) * 1 + 17) * 3;
                            (1 + 0) * (0 + 1);
                            ((((((((((((((11111111))))))))))))));
                            500+499 + (((300 * 0 + 0)));
                         """
        val lexer = calculatorLexer(ANTLRInputStream(expression))
        val tokens = CommonTokenStream(lexer)
        val parser = calculatorParser(tokens)
        val calc = CalcVisitor()
        calc.visit(parser.start())

        val results = calc.calculated_values.split("|")
        assertEquals(results[0].split("=")[1], "200;")
        assertEquals(results[1].split("=")[1], "57;")
        assertEquals(results[2].split("=")[1], "1;")
        assertEquals(results[3].split("=")[1], "11111111;")
        assertEquals(results[4].split("=")[1], "999;")
    }

    @Test
    fun skipTest() {
        val expression = """1 +                                                       1;
                            1333333                       
                            *
                            0;
                            TuringMachine + NFA * DFA;
                         """
        val lexer = calculatorLexer(ANTLRInputStream(expression))
        val tokens = CommonTokenStream(lexer)
        val parser = calculatorParser(tokens)
        val calc = CalcVisitor()
        calc.visit(parser.start())

        val results = calc.calculated_values.split("|")
        assertEquals(results[0], "1+1=2;")
        assertEquals(results[1], "1333333*0=0;")
        assertEquals(results[2], "TuringMachine+NFA*DFA=0;")
    }

    @Test
    fun variableTest() {
        val expression = """superVariable = 333;
                            superVariable + 777;
                            superVariable = 1000;
                            superVariable * 99;
                         """
        val lexer = calculatorLexer(ANTLRInputStream(expression))
        val tokens = CommonTokenStream(lexer)
        val parser = calculatorParser(tokens)
        val calc = CalcVisitor()
        calc.visit(parser.start())

        val results = calc.calculated_values.split("|")
        assertEquals(results[0], "superVariable=333;")
        assertEquals(results[1], "superVariable+777=1110;")
        assertNotEquals(results[1], "superVariable+777=1000;")  //  ;(
        assertEquals(results[2], "superVariable=1000;")
        assertEquals(results[3], "superVariable*99=99000;")
    }

    @Test
    fun notMatchingGrammarTest() {
        val expression1 = "42 = 41 + 2;"
        val expression2 = """
                          42 + 3;
                          15 * 9999;
                          3 14 15 92;  
                          """
        val expression3 = "P = NP"

        val lexer1 = calculatorLexer(ANTLRInputStream(expression1))
        val tokens1 = CommonTokenStream(lexer1)
        val parser1 = calculatorParser(tokens1)

        // Эта штука сразу кридает исключение при несоответствии грамматике
        parser1.setErrorHandler(BailErrorStrategy())
        val calc1 = CalcVisitor()

        assertThrows<ParseCancellationException> {
            calc1.visit(parser1.start())
        }

        val lexer2 = calculatorLexer(ANTLRInputStream(expression2))
        val tokens2 = CommonTokenStream(lexer2)
        val parser2 = calculatorParser(tokens2)
        parser2.setErrorHandler(BailErrorStrategy())
        val calc2 = CalcVisitor()

        assertThrows<ParseCancellationException> {
            calc2.visit(parser2.start())
        }

        val lexer3 = calculatorLexer(ANTLRInputStream(expression3))
        val tokens3 = CommonTokenStream(lexer3)
        val parser3 = calculatorParser(tokens3)
        parser3.setErrorHandler(BailErrorStrategy())
        val calc3 = CalcVisitor()

        assertThrows<ParseCancellationException> {
            calc3.visit(parser3.start())
        }
    }

    @Test
    fun fileTest() {
        val inputStream = FileInputStream("data/test1_in.txt")
        val input = CharStreams.fromStream(inputStream)
        val lexer = calculatorLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = calculatorParser(tokens)

        val calc = CalcVisitor()
        val answer = calc.calculate(parser)
        val splitted_answer = answer.split("|")
        val file = File("data/test1_out.txt")

        PrintWriter(file, Charsets.UTF_8).use {
            for (i in 0..splitted_answer.size - 2) {
                it.println(splitted_answer[i])
            }
        }

        assertEquals(isEqualFiles(File("data/test1_out.txt").toPath(), File("data/test1_predicted.txt").toPath()), true)

        // Очищает файл test1_out.txt
        PrintWriter(file, Charsets.UTF_8).use {
            it.print("")
        }
    }

}