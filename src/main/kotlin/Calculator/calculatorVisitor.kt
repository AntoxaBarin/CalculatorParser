package Calculator
/** ExprBaseVisitor is the base class generated by ANTLR */
class CalcVisitor : calculatorBaseVisitor<Int>() {
    var memory: HashMap<String, Int> = HashMap<String, Int>()

    /** These are the functions generated by each label as we defined in our grammar.
     *  Whe only need to override the functions we are interested in.
     */

    // Можно было вместо строки использовать словарь, но тогда в случае многократного присваивания значения
    // какой-нибудь переменной выводилось только последнее присвоенное значение , так как ключ одинаковый
    var calculated_values : String = ""

    fun calculate(parser : calculatorParser) : String {
        visit(parser.start())
        //print(calculated_values)
        return calculated_values
    }

    // Вычисляем значение каждого stat (например, 5 * 13 + 3;)
    override fun visitStatements(ctx: calculatorParser.StatementsContext?): Int {
        for (stat in ctx!!.stat()) {
            if (stat.getText().contains("=")) {  // Случай var = value (чтоб не было a=5=5 на выходе)
                calculated_values += stat.getText().split("=")[0] + "=" + visit(stat) + ";|"
            }
            else {
                calculated_values += stat.getText().dropLast(1) + "=" + visit(stat) + ";|"
            }
        }
        return 0
    }
    // id;
    override fun visitDefinition(ctx: calculatorParser.DefinitionContext): Int {
        return visit(ctx.`var`())    // Называть что-то словом "var" было глупой идеей
    }

    // expr;
    override fun visitExpression(ctx: calculatorParser.ExpressionContext): Int {
        return visit(ctx.expr())
    }

    // NUM;
    override fun visitNumber(ctx: calculatorParser.NumberContext): Int {
        return ctx.INT().getText().toInt()
    }

    // ID;
    override fun visitVariable(ctx: calculatorParser.VariableContext): Int {
        val variable = ctx.ID().getText()
        val value = memory.get(variable)

        if (value != null) {
            return value
        }
        return 0
    }

    // expr '*' expr
    override fun visitMultiplication(ctx: calculatorParser.MultiplicationContext): Int {
        val left = visit(ctx.expr(0))
        val right = visit(ctx.expr(1))
        //println(left * right)
        return left * right
    }

    // expr + expr
    override fun visitSum(ctx: calculatorParser.SumContext): Int {
        val left = visit(ctx.expr(0))
        val right = visit(ctx.expr(1))
        return left + right
    }

    // '(' expr ')'
    override fun visitBrackets(ctx: calculatorParser.BracketsContext): Int {
        return visit(ctx.expr())
    }

    override fun visitAssign(ctx: calculatorParser.AssignContext): Int {
        val id = ctx.ID().getText()
        val value = visit(ctx.expr())
        memory.put(id, value)
        return value
    }
}

