package Calculator;// Generated from /home/ivan/IdeaProjects/calcGrammar/calculator.g4 by ANTLR 4.13.1
import org.graalvm.shadowed.org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code statements}
	 * labeled alternative in {@link calculatorParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(calculatorParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definition}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(calculatorParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link calculatorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code number}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(calculatorParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(calculatorParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sum}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(calculatorParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(calculatorParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brackets}
	 * labeled alternative in {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrackets(calculatorParser.BracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link calculatorParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(calculatorParser.AssignContext ctx);
}