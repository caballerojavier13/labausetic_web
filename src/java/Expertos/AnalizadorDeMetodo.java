/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expertos;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import java.util.List;

/**
 *
 * @author javier
 */
public class AnalizadorDeMetodo {

    private MethodDeclaration method;

    private Expression SentenciaReturn;

    public AnalizadorDeMetodo() {
    }

    public AnalizadorDeMetodo(MethodDeclaration method) {
        this.method = method;
    }

    public ReturnStmt SentenciaReturn() {

        if (!(method.getType().toString().equalsIgnoreCase("void"))) {
            List<Statement> findStatement = Statements.findStatement(method, Statements.ReturnStmt);
            System.out.println("     - Variable/Expresion de Salida: " + ((ReturnStmt)findStatement.get(0)).getExpr().toString());
        }

        return null;
    }

    public List<Parameter> ParametrosDeEntrada() {
        return method.getParameters();
    }
    
    public List<Expression> VariablesDeEstado(){
        List<Statement> findStatement = Statements.findStatement(method, Statements.ExpressionStmt);
        for (Statement statement : findStatement) {
            if(Expressions.isDeterminatedExpression(((ExpressionStmt)statement).getExpression(), Expressions.VariableDeclarationExpr)){
                List<VariableDeclarator> vars = ((VariableDeclarationExpr)((ExpressionStmt)statement).getExpression()).getVars();
                for (VariableDeclarator variableDeclarator : vars) {
                    System.err.println(variableDeclarator.getId().getName());
                }
            }
            
        }
        return null;
    }
    
}
