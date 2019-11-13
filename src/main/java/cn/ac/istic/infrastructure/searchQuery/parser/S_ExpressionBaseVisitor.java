package cn.ac.istic.infrastructure.searchQuery.parser;// Generated from /home/ufo/Documents/workspace/policy/src/main/resources/S_Expression.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link S_ExpressionVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class S_ExpressionBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements S_ExpressionVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitS_expression(S_ExpressionParser.S_expressionContext ctx) { return visitChildren(ctx); }
}