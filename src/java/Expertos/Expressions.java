/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expertos;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.VariableDeclarationExpr;

/**
 *
 * @author javier
 */
public class Expressions {

    public static final int AnnotationExpr = 0;
    public static final int VariableDeclarationExpr = 1;

    private static int getIndexStatement(Expression e) {
        if (e instanceof VariableDeclarationExpr) {
            return VariableDeclarationExpr;
        } else {
            return -1;
        }

    }

    public static boolean isDeterminatedExpression(Expression e, int i) {
        boolean resultado = false;
        switch (i) {
            case VariableDeclarationExpr:
                if (VariableDeclarationExpr == getIndexStatement(e)) {
                    resultado = true;
                }
                break;
        }
        return resultado;
    }
}
