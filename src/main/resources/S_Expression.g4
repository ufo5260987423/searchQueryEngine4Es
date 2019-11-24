grammar S_Expression;

//userd
fragment Dot: '.';
fragment DOUBLE_QUOTES :'"';
fragment Digit: '0' .. '9';
fragment Letter : ('a'..'z'|'A'..'Z'|'_'|'-');
fragment HexDigit: ('0' .. '9' | 'A' .. 'F' | 'a' .. 'f');
fragment UnicodeChar: ~('"'| '\\' );
fragment StringChar :  ('a'..'z'|'A'..'Z'|'0'..'9'|'_')+;
fragment EscapeSequence : '\\' ('"' | '\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' | 'u' HexDigit HexDigit HexDigit HexDigit);
fragment Int: '-'? ('0' | '1'..'9' Digit*);
fragment Frac: Dot Digit+;
fragment Exp: ('e' | 'E') ('+' | '-')? Digit+;
fragment StringLiteral :  ( EscapeSequence | ~('\\'|'"'| '\n' |'\r') ) ;

LBrace :  '{' ;
RBrace :  '}' ;
LBracket: '[';
RBracket: ']';
LParentheses: '(';
RParentheses: ')';
Equal :  '=' ;
Comma : ',' ;
Colon: ':';

//WS  : [\t\n\r]+ ->  skip;
WhiteSpace: (' ' | '\r' | '\t' | '\u000C' |'\n') ->skip;
LINE_COMMENT : '`' ~('\n')+? ->skip;

//lexer
Integer : Int;
Double : Int (Frac Exp? | Exp);
Bool: 'true'|'false';
Null : 'null';
String : DOUBLE_QUOTES StringLiteral* DOUBLE_QUOTES;
Variable : Letter (Letter | Digit)* ;

s_expression: LParentheses (s_expression| Integer | Double|Bool|Null|String|Double|Variable)* RParentheses ;
