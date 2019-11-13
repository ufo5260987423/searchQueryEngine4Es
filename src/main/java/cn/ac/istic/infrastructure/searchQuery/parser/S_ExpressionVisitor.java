package cn.ac.istic.infrastructure.searchQuery.parser;// Generated from /home/ufo/Documents/workspace/policy/src/main/resources/S_Expression.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link S_ExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface S_ExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link S_ExpressionParser#s_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS_expression(S_ExpressionParser.S_expressionContext ctx);
}