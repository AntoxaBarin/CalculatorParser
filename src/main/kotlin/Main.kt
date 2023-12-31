import Calculator.CalcVisitor
import Calculator.calculatorLexer
import Calculator.calculatorParser
import org.graalvm.shadowed.org.antlr.v4.runtime.*
import org.graalvm.shadowed.org.antlr.v4.runtime.misc.ParseCancellationException

import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter


fun main() {
    println("Enter the path to the input file:")
    val input_file = readln()

    val inputStream = FileInputStream(input_file)
    val input = CharStreams.fromStream(inputStream)
    val lexer = calculatorLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = calculatorParser(tokens)

    // кидает exxception при несоответствии грамматики и текста
    parser.setErrorHandler(BailErrorStrategy())

    var answer = ""
    val calc = CalcVisitor()
    try {
        answer = calc.calculate(parser)
    }
    catch (e : ParseCancellationException) {
        print("Input text does not match with the grammar!")
        return
    }

    // Парсим вычисления калькулятора
    val splitted_answer = answer.split("|")
    for (i in 0..splitted_answer.size - 2) {
        println(splitted_answer[i])
    }

    println("Enter the path to the output file:")
    val output_file = readln()
    val file = File(output_file)

    PrintWriter(file, Charsets.UTF_8).use {
        for (i in 0..splitted_answer.size - 2) {
            it.println(splitted_answer[i])
        }
    }

}
