package cn.ac.istic.infrastructure.searchQuery;

import cn.ac.istic.infrastructure.searchQuery.parser.S_ExpressionListener;
import cn.ac.istic.infrastructure.searchQuery.parser.S_ExpressionParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AstConverter implements S_ExpressionListener {
    private Stack<List> sExpressionStack = new Stack();

    public List getAst(){
        return sExpressionStack.peek();
    }

    @Override
    public void enterS_expression(S_ExpressionParser.S_expressionContext ctx) {
        sExpressionStack.push(new ArrayList());
    }

    @Override
    public void exitS_expression(S_ExpressionParser.S_expressionContext ctx) {
        if (sExpressionStack.size() > 1) {
            List tmp=sExpressionStack.pop();
            sExpressionStack.peek().add(tmp);
        }
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        Object obj;
        String text = node.getText();
        switch (node.getSymbol().getType()) {
            case S_ExpressionParser.Double:
                obj = Double.parseDouble(text);
                break;
            case S_ExpressionParser.Integer:
                obj = Integer.parseInt(text);
                break;
            case S_ExpressionParser.String:
                obj = text.substring(1, text.length() - 1);
                break;
            case S_ExpressionParser.Null:
                obj = null;
                break;
            case S_ExpressionParser.Bool:
                obj = Boolean.parseBoolean(text);
                break;
            case S_ExpressionParser.Variable:
                obj =new AstConverter.Vaiable();
                ((Vaiable) obj).setName(text);
                break;
            default:
                obj = null;
        }
        if (null != obj)
            sExpressionStack.peek().add(obj);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.err.println("err:" + node.getSymbol().getText());
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
//        System.out.println("enterRule");
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
//        System.out.println("exitRule");
    }
    public class Vaiable{
        private String name;
        private List sExpression=new ArrayList();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List getsExpression() {
            return sExpression;
        }

        public void setsExpression(List sExpression) {
            this.sExpression = sExpression;
        }
        public String toString(){
            return this.name;
        }
    }
}
