package cn.ac.istic.infrastructure.searchQuery.parser;// Generated from /home/ufo/Documents/workspace/policy/src/main/resources/S_Expression.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link S_ExpressionParser}.
 */
public interface S_ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link S_ExpressionParser#s_expression}.
	 * @param ctx the parse tree
	 */
	void enterS_expression(S_ExpressionParser.S_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link S_ExpressionParser#s_expression}.
	 * @param ctx the parse tree
	 */
	void exitS_expression(S_ExpressionParser.S_expressionContext ctx);
}