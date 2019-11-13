package cn.ac.istic.infrastructure.searchQuery.parser;// Generated from /home/ufo/Documents/workspace/policy/src/main/resources/S_Expression.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class S_ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LBrace=1, RBrace=2, LBracket=3, RBracket=4, LParentheses=5, RParentheses=6,
		Equal=7, Comma=8, Colon=9, WhiteSpace=10, LINE_COMMENT=11, Integer=12,
		Double=13, Bool=14, Null=15, String=16, Variable=17;
	public static final int
		RULE_s_expression = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"s_expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'['", "']'", "'('", "')'", "'='", "','", "':'",
			null, null, null, null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LBrace", "RBrace", "LBracket", "RBracket", "LParentheses", "RParentheses",
			"Equal", "Comma", "Colon", "WhiteSpace", "LINE_COMMENT", "Integer", "Double",
			"Bool", "Null", "String", "Variable"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "S_Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public S_ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class S_expressionContext extends ParserRuleContext {
		public TerminalNode LParentheses() { return getToken(S_ExpressionParser.LParentheses, 0); }
		public TerminalNode RParentheses() { return getToken(S_ExpressionParser.RParentheses, 0); }
		public List<S_expressionContext> s_expression() {
			return getRuleContexts(S_expressionContext.class);
		}
		public S_expressionContext s_expression(int i) {
			return getRuleContext(S_expressionContext.class,i);
		}
		public List<TerminalNode> Integer() { return getTokens(S_ExpressionParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(S_ExpressionParser.Integer, i);
		}
		public List<TerminalNode> Double() { return getTokens(S_ExpressionParser.Double); }
		public TerminalNode Double(int i) {
			return getToken(S_ExpressionParser.Double, i);
		}
		public List<TerminalNode> Bool() { return getTokens(S_ExpressionParser.Bool); }
		public TerminalNode Bool(int i) {
			return getToken(S_ExpressionParser.Bool, i);
		}
		public List<TerminalNode> Null() { return getTokens(S_ExpressionParser.Null); }
		public TerminalNode Null(int i) {
			return getToken(S_ExpressionParser.Null, i);
		}
		public List<TerminalNode> String() { return getTokens(S_ExpressionParser.String); }
		public TerminalNode String(int i) {
			return getToken(S_ExpressionParser.String, i);
		}
		public List<TerminalNode> Variable() { return getTokens(S_ExpressionParser.Variable); }
		public TerminalNode Variable(int i) {
			return getToken(S_ExpressionParser.Variable, i);
		}
		public S_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof S_ExpressionListener ) ((S_ExpressionListener)listener).enterS_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof S_ExpressionListener ) ((S_ExpressionListener)listener).exitS_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof S_ExpressionVisitor ) return ((S_ExpressionVisitor<? extends T>)visitor).visitS_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final S_expressionContext s_expression() throws RecognitionException {
		S_expressionContext _localctx = new S_expressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
			match(LParentheses);
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LParentheses) | (1L << Integer) | (1L << Double) | (1L << Bool) | (1L << Null) | (1L << String) | (1L << Variable))) != 0)) {
				{
				setState(11);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(3);
					s_expression();
					}
					break;
				case 2:
					{
					setState(4);
					match(Integer);
					}
					break;
				case 3:
					{
					setState(5);
					match(Double);
					}
					break;
				case 4:
					{
					setState(6);
					match(Bool);
					}
					break;
				case 5:
					{
					setState(7);
					match(Null);
					}
					break;
				case 6:
					{
					setState(8);
					match(String);
					}
					break;
				case 7:
					{
					setState(9);
					match(Double);
					}
					break;
				case 8:
					{
					setState(10);
					match(Variable);
					}
					break;
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(16);
			match(RParentheses);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23\25\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\3\2\3"+
		"\2\2\2\3\2\2\2\2\33\2\4\3\2\2\2\4\17\7\7\2\2\5\16\5\2\2\2\6\16\7\16\2"+
		"\2\7\16\7\17\2\2\b\16\7\20\2\2\t\16\7\21\2\2\n\16\7\22\2\2\13\16\7\17"+
		"\2\2\f\16\7\23\2\2\r\5\3\2\2\2\r\6\3\2\2\2\r\7\3\2\2\2\r\b\3\2\2\2\r\t"+
		"\3\2\2\2\r\n\3\2\2\2\r\13\3\2\2\2\r\f\3\2\2\2\16\21\3\2\2\2\17\r\3\2\2"+
		"\2\17\20\3\2\2\2\20\22\3\2\2\2\21\17\3\2\2\2\22\23\7\b\2\2\23\3\3\2\2"+
		"\2\4\r\17";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}