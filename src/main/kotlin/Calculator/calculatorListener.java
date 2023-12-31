package Calculator;// Generated from /home/ivan/IdeaProjects/calcGrammar/calculator.g4 by ANTLR 4.13.1
import org.graalvm.shadowed.org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calculatorParser}.
 */
public interface calculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code statements}
	 * labeled alternative in {@link calculatorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStatements(calculatorParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statements}
	 * labeled alternative in {@link calculatorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStatements(calculatorParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definition}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(calculatorParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definition}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(calculatorParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expression}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code number}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumber(calculatorParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code number}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumber(calculatorParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(calculatorParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(calculatorParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sum}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSum(calculatorParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sum}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSum(calculatorParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(calculatorParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(calculatorParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brackets}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBrackets(calculatorParser.BracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brackets}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBrackets(calculatorParser.BracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link calculatorParser#var}.
	 * @param ctx the parse tree
	 */
	void enterAssign(calculatorParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link calculatorParser#var}.
	 * @param ctx the parse tree
	 */
	void exitAssign(calculatorParser.AssignContext ctx);
}