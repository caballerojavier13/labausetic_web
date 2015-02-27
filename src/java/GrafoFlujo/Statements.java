/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package GrafoFlujo;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.EmptyStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class Statements {

    public static final int BlockStmt = 0;
    public static final int AssertStmt = 1;
    public static final int BreakStmt = 2;
    public static final int CatchStmt = 3;
    public static final int ContinueStmt = 4;
    public static final int TypeDeclarationStmt = 5;
    public static final int EmptyStmt = 6;
    public static final int ExplicitConstructorInvocationStmt = 7;
    public static final int ExpressionStmt = 8;
    public static final int ThrowStmt = 9;
    public static final int TryStmt = 10;
    public static final int SynchronizedStmt = 11;
    public static final int LabeledStmt = 12;
    public static final int ReturnStmt = 13;
    public static final int SwitchStmt = 14;

    public static final int IfStmt = 15;
    public static final int SwitchEntryStmt = 16;
    public static final int ForStmt = 17;
    public static final int ForeachStmt = 18;
    public static final int DoStmt = 19;
    public static final int WhileStmt = 20;

    public static int getIndexStatement(Statement s) {
        if (s instanceof AssertStmt) {
            return AssertStmt;
        } else {
            if (s instanceof WhileStmt) {
                return WhileStmt;
            } else {
                if (s instanceof BreakStmt) {
                    return BreakStmt;
                } else {
                    if (s instanceof ContinueStmt) {
                        return ContinueStmt;
                    } else {
                        if (s instanceof DoStmt) {
                            return DoStmt;
                        } else {
                            if (s instanceof EmptyStmt) {
                                return EmptyStmt;
                            } else {
                                if (s instanceof ExplicitConstructorInvocationStmt) {
                                    return ExplicitConstructorInvocationStmt;
                                } else {
                                    if (s instanceof ExpressionStmt) {
                                        return ExpressionStmt;
                                    } else {
                                        if (s instanceof ForeachStmt) {
                                            return ForeachStmt;
                                        } else {
                                            if (s instanceof ForStmt) {
                                                return ForStmt;
                                            } else {
                                                if (s instanceof IfStmt) {
                                                    return IfStmt;
                                                } else {
                                                    if (s instanceof LabeledStmt) {
                                                        return LabeledStmt;
                                                    } else {
                                                        if (s instanceof ReturnStmt) {
                                                            return ReturnStmt;
                                                        } else {
                                                            if (s instanceof SwitchEntryStmt) {
                                                                return SwitchEntryStmt;
                                                            } else {
                                                                if (s instanceof SwitchStmt) {
                                                                    return SwitchStmt;
                                                                } else {
                                                                    if (s instanceof SynchronizedStmt) {
                                                                        return SynchronizedStmt;
                                                                    } else {
                                                                        if (s instanceof ThrowStmt) {
                                                                            return ThrowStmt;
                                                                        } else {
                                                                            if (s instanceof TryStmt) {
                                                                                return TryStmt;
                                                                            } else {
                                                                                if (s instanceof TypeDeclarationStmt) {
                                                                                    return TypeDeclarationStmt;
                                                                                } else {

                                                                                    if (s instanceof BlockStmt) {
                                                                                        return BlockStmt;
                                                                                    } else {
                                                                                        return -1;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<Statement> findStatement(MethodDeclaration method, int type) {
        return findStatement(method.getBody(), type);
    }

    private static List<Statement> findStatement(Statement s, int type) {
        List<Statement> retorno = new ArrayList();
        if (getIndexStatement(s) == type) {
            retorno.add(s);
        } else {
            List<Statement> stmts;
            List<Statement> findStatement;
            switch (getIndexStatement(s)) {
                case BlockStmt:
                    stmts = ((BlockStmt) s).getStmts();
                    if (stmts != null) {
                        for (Statement statement : stmts) {
                            findStatement = findStatement(statement, type);
                            for (Statement statement1 : findStatement) {
                                if (statement1 != null) {
                                    retorno.add(statement1);
                                }
                            }
                        }
                    }
                    break;
                case DoStmt:
                    findStatement = findStatement(((DoStmt) s).getBody(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
                case ForStmt:
                    findStatement = findStatement(((ForStmt) s).getBody(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
                case ForeachStmt:
                    findStatement = findStatement(((ForeachStmt) s).getBody(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
                case IfStmt:
                    findStatement = findStatement(((IfStmt) s).getThenStmt(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    findStatement = findStatement(((IfStmt) s).getElseStmt(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
                case SwitchEntryStmt:
                    stmts = ((SwitchEntryStmt) s).getStmts();
                    for (Statement statement : stmts) {
                        findStatement = findStatement(statement, type);
                        for (Statement statement1 : findStatement) {
                            if (statement1 != null) {
                                retorno.add(statement1);
                            }
                        }
                    }
                    break;
                case TryStmt:
                    findStatement = findStatement(((TryStmt) s).getTryBlock(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    findStatement = findStatement(((TryStmt) s).getFinallyBlock(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
                case WhileStmt:
                    findStatement = findStatement(((WhileStmt) s).getBody(), type);
                    for (Statement statement1 : findStatement) {
                        if (statement1 != null) {
                            retorno.add(statement1);
                        }
                    }
                    break;
            }
        }

        return retorno;
    }

    public static String nameStatement(Statement s) {
        return nameStatement(getIndexStatement(s));
    }

    public static String nameStatement(int type) {
        switch (type) {
            case AssertStmt:
                return "ASSERT";
            case BlockStmt:
                return "BLOQUE";
            case BreakStmt:
                return "BREAK";
            case CatchStmt:
                return "CATCH";
            case ContinueStmt:
                return "CONTINUE";
            case DoStmt:
                return "DO";
            case ForStmt:
                return "FOR";
            case ForeachStmt:
                return "FOREACH";
            case IfStmt:
                return "IF";
            case ReturnStmt:
                return "RETURN";
            case SwitchEntryStmt:
                return "SWITCH ENTRADA";
            case SwitchStmt:
                return "SWITCH";
            case ThrowStmt:
                return "THROWS";
            case TryStmt:
                return "TRY";
            case TypeDeclarationStmt:
                return "DECLARACION VARIABLE";
            case WhileStmt:
                return "WHILE";
            case EmptyStmt:
                return "";
            case ExplicitConstructorInvocationStmt:
                return "LLAMADA_CONSTRUCTOR";
            case ExpressionStmt:
                return "EXPRESION";
            case LabeledStmt:
                return "ETIQUETA";
            case SynchronizedStmt:
                return "SINCRONIZACION";
            default:
                return "Nada";
        }
    }

    public static List<Statement> sentenciasComponentes(Statement s, int type) {
        List<Statement> retorno = new ArrayList();
        List<Statement> stmts;
        List<Statement> findStatement;
        switch (getIndexStatement(s)) {
            case BlockStmt:
                stmts = ((BlockStmt) s).getStmts();
                if (stmts != null) {
                    for (Statement statement : stmts) {
                        retorno.add(statement);
                    }
                }
                break;
            case DoStmt:
                retorno.add(((DoStmt) s).getBody());
                break;
            case ForStmt:
                retorno.add(((ForStmt) s).getBody());
                break;
            case ForeachStmt:
                retorno.add(((ForeachStmt) s).getBody());
                break;
            case IfStmt:
                retorno.add(((IfStmt) s).getThenStmt());
                retorno.add(((IfStmt) s).getElseStmt());
                break;
            case SwitchEntryStmt:
                stmts = ((SwitchEntryStmt) s).getStmts();
                for (Statement statement : stmts) {
                    retorno.add(statement);
                }
                break;
            case TryStmt:
                retorno.add(((TryStmt) s).getTryBlock());
                retorno.add(((TryStmt) s).getFinallyBlock());
                break;
            case WhileStmt:
                retorno.add(((WhileStmt) s).getBody());
                break;

        }

        return retorno;
    }

    public static List<Nodo> getNodos(Statement s, Nodo fin, int Id) {
        List<Nodo> resultado = new ArrayList();
        Nodo n1, n2, n3;
        List<Nodo> nodos;
        Nodo finTemp;
        switch (Statements.getIndexStatement(s)) {
            case AssertStmt:
                //return "ASSERT";
                break;
            case BlockStmt:
                List<Statement> stmts = ((BlockStmt) s).getStmts();
                if (stmts != null) {
                    int index = 0;
                    Nodo ifTemp;
                    for (Statement tempS : stmts) {
                        List<Nodo> TempResult;
                        if (index < (stmts.size() - 1)) {
                            TempResult = getNodos(tempS, null, Id);
                        } else {
                            TempResult = getNodos(tempS, fin, Id);
                        }
                        Id += TempResult.size();
                        ifTemp = TempResult.get(0);
                        if (index < (stmts.size())) {
                            addNullSiguiente(resultado, ifTemp);
                        }

                        for (Nodo n : TempResult) {
                            resultado.add(n);
                        }

                        index++;
                    }
                } else {
                    n1 = new Nodo(Id);
                    n1.addSiguiente(fin);
                    n1.setTexto("//Vacio");
                    resultado.add(n1);
                }
                break;
            case BreakStmt:
                n1 = new Nodo(Id);
                n1.addSiguiente(fin);
                n1.setTexto("Break");
                resultado.add(n1);
                break;
            case ContinueStmt:
                n1 = new Nodo(Id);
                n1.addSiguiente(fin);
                n1.setTexto("Continue");
                resultado.add(n1);
                ;
                break;

            case DoStmt:
                try {
                    n1 = new Nodo(Id);
                    n1.setTexto(((DoStmt) s).getCondition().toString());
                    n1.addSiguiente(fin);
                    Id++;

                    nodos = getNodos(((DoStmt) s).getBody(), n1, Id);
                    if (nodos.size() > 0) {
                        n1.addSiguiente(nodos.get(0));
                        for (Nodo nodo : nodos) {
                            resultado.add(nodo);
                        }
                    }
                    resultado.add(n1);
                } catch (Exception e) {

                }
                break;
            case ForStmt:
                ForStmt forstmt = ((ForStmt) s);
                Nodo compare = new Nodo(Id);
                compare.setTexto(forstmt.getCompare().toString());

                compare.addSiguiente(fin);

                Id++;

                List<Nodo> nodosUpdate = getNodosExpresion(forstmt.getUpdate(), compare, Id);
                Id += nodosUpdate.size();

                List<Nodo> nodosBody = getNodos(forstmt.getBody(), nodosUpdate.get(0), Id);
                Id += nodosBody.size();

                compare.addSiguiente(nodosBody.get(0));

                List<Nodo> nodosInit = getNodosExpresion(forstmt.getInit(), compare, Id);
                Id += nodosInit.size();

                for (Nodo nodo : nodosInit) {
                    resultado.add(nodo);
                }
                resultado.add(compare);

                for (Nodo nodo : nodosBody) {
                    resultado.add(nodo);
                }

                for (Nodo nodo : nodosUpdate) {
                    resultado.add(nodo);
                }

                break;
            case ForeachStmt:
                ForeachStmt foreachstmt = ((ForeachStmt) s);

                n1 = new Nodo(Id);

                n1.setTexto(foreachstmt.getVariable().getVars().get(0).toString() + ":" + foreachstmt.getIterable().toString() + ".next()");
                n1.addSiguiente(fin);
                Id++;
                nodos = getNodos(foreachstmt.getBody(), n1, Id);

                n1.addSiguiente(nodos.get(0));

                resultado.add(n1);

                for (Nodo nodo : nodos) {
                    resultado.add(nodo);
                }

                break;
            case IfStmt:

                n1 = new Nodo(Id);

                n1.setTexto(((IfStmt) s).getCondition().toString().trim());

                Id++;
                resultado.add(n1);

                nodos = getNodos(((IfStmt) s).getThenStmt(), fin, Id);
                if (nodos.size() > 0) {
                    n1.addSiguiente(nodos.get(0));
                    for (Nodo nodo : nodos) {
                        resultado.add(nodo);
                        Id++;
                    }
                } else {
                    n2 = new Nodo(Id);
                    n2.setTexto("//Then Vacio");
                    n2.addSiguiente(fin);

                    n1.addSiguiente(n2);
                }

                nodos = getNodos(((IfStmt) s).getElseStmt(), fin, Id);
                if (nodos.size() > 0) {

                    n1.addSiguiente(nodos.get(0));
                    for (Nodo nodo : nodos) {
                        resultado.add(nodo);
                        Id++;
                    }
                } else {
                    n1.addSiguiente(fin);
                }

                break;
            case ReturnStmt:

                n1 = new Nodo(Id);
                n1.setTexto("RETURN");
                n1.setRetorno(true);

                n1.addSiguiente(fin);

                resultado.add(n1);
                break;
            case SwitchStmt:
                try {
                    nodos = getNodosSwitch(((SwitchStmt) s), fin, Id);

                    for (Nodo nodo : nodos) {
                        resultado.add(nodo);
                    }
                } catch (Exception e) {

                }
                break;
            case ThrowStmt:
                n1 = new Nodo(Id);
                n1.setTexto(((ThrowStmt) s).getExpr().toString());

                n1.addSiguiente(fin);

                resultado.add(n1);
                break;
            case TryStmt:

                TryStmt stmtTry = (TryStmt) s;

                finTemp = fin;

                List<Nodo> finallyNodos = getNodos(stmtTry.getFinallyBlock(), fin, Id);

                Id = Id + finallyNodos.size();
                if (finallyNodos.size() > 0) {
                    finTemp = finallyNodos.get(0);
                }
                List<Nodo> tryNodos = new ArrayList();

                stmts = stmtTry.getTryBlock().getStmts();

                for (int j = stmts.size() - 1; j > -1; j--) {
                    nodos = getNodos(stmts.get(j), finTemp, Id);

                    for (Nodo nodo : nodos) {
                        tryNodos.add(nodo);
                        Id++;
                    }
                    if (nodos.size() > 0) {
                        finTemp = nodos.get(0);
                    }

                }

                if (finallyNodos.size() > 0) {
                    finTemp = finallyNodos.get(0);
                } else {
                    finTemp = fin;
                }
                List<CatchClause> catchs = stmtTry.getCatchs();

                List<Nodo> catchNodos = new ArrayList();

                Nodo finAlt = null;

                for (int i = 0; i < catchs.size(); i++) {
                    nodos = getNodosCatch(catchs.get(i), finTemp, null, Id);

                    Id = Id + nodos.size();
                    if (nodos.size() > 0) {
                        addNullSiguiente(catchNodos, nodos.get(0));

                        for (Nodo nodo : nodos) {
                            catchNodos.add(nodo);
                        }

                    }

                }

                addNullSiguiente(catchNodos, tryNodos.get(0));

                resultado.addAll(catchNodos);
                resultado.addAll(tryNodos);
                resultado.addAll(finallyNodos);

                break;
            case TypeDeclarationStmt:
                n1 = new Nodo(Id);
                n1.setTexto(((TypeDeclarationStmt) s).toString());

                n1.addSiguiente(fin);

                resultado.add(n1);
                break;
            case WhileStmt:
                try {
                    n1 = new Nodo(Id);
                    n1.setTexto(((WhileStmt) s).getCondition().toString());

                    n1.addSiguiente(fin);
                    Id++;

                    resultado.add(n1);
                    nodos = getNodos(((WhileStmt) s).getBody(), n1, Id);
                    if (nodos.size() > 0) {
                        n1.addSiguiente(nodos.get(0));
                        for (Nodo nodo : nodos) {
                            resultado.add(nodo);
                        }
                    }
                } catch (Exception e) {

                }
                break;
            case EmptyStmt:

                n1 = new Nodo(Id);

                n1.setTexto("Empty");

                n1.addSiguiente(fin);
                resultado.add(n1);

                break;
            case ExplicitConstructorInvocationStmt:
                System.out.println("Llamada a constructor");
                n1 = new Nodo(Id);
                n1.setTexto(((ExplicitConstructorInvocationStmt) s).getExpr().toString());

                n1.addSiguiente(fin);

                resultado.add(n1);
                break;
            case ExpressionStmt:
                n1 = new Nodo(Id);

                n1.setTexto(((ExpressionStmt) s).getExpression().toString());

                n1.addSiguiente(fin);
                resultado.add(n1);
                break;
            case LabeledStmt:
                //return "ETIQUETA";
                break;
            case SynchronizedStmt:
                //return "SINCRONIZACION";
                break;
            default:
                break;
        }
        return resultado;
    }

    private static List<Nodo> reverseNodos(List<Nodo> nodos) {
        List<Nodo> resultado = new ArrayList();

        for (int i = (nodos.size() - 1); i > -1; i--) {
            resultado.add(nodos.get(i));
        }

        return resultado;
    }

    private static List<Nodo> getNodosCatch(CatchClause s, Nodo fin, Nodo finAlt, int Id) {
        List<Nodo> resultado = new ArrayList();

        Nodo excepcion = new Nodo(Id);

        Id++;

        List<Nodo> nodos = getNodos(s.getCatchBlock(), fin, Id);

        excepcion.addSiguiente(nodos.get(0));
        excepcion.setTexto(s.getExcept().getType().toString());
        excepcion.addSiguiente(finAlt);
        resultado.add(excepcion);

        resultado.addAll(nodos);

        return resultado;
    }

    private static List<Nodo> getNodosSwitch(SwitchStmt s, Nodo fin, int Id) {
        List<Nodo> resultado = new ArrayList();
        List<SwitchEntryStmt> entries = s.getEntries();

        Nodo primerNodo = null;
        try {
            primerNodo = new Nodo(Id);
            primerNodo.setTexto(s.getSelector().toString());
            Id++;
            for (int i = entries.size() - 1; i > -1; i--) {

                SwitchEntryStmt switchEntryStmt = entries.get(i);

                Nodo label = new Nodo(Id);

                try {
                    label.setTexto(s.getSelector().toString() + " == " + switchEntryStmt.getLabel().toString());

                } catch (NullPointerException e) {
                    label.setTexto("Por defecto");

                }
                resultado.add(label);
                primerNodo.addSiguiente(label);

                Id++;

                List<Nodo> blocks = new ArrayList();

                List<Statement> stmts = switchEntryStmt.getStmts();

                Nodo finTemp = fin;
                for (int j = stmts.size() - 1; j > -1; j--) {
                    List<Nodo> nodos = getNodos(stmts.get(j), finTemp, Id);

                    if (nodos.size() > 0) {
                        finTemp = nodos.get(0);

                        for (Nodo nodo : nodos) {
                            resultado.add(nodo);
                            Id++;
                        }
                    }

                }
                label.addSiguiente(finTemp);

            }
            resultado.add(primerNodo);
            resultado = reverseNodos(resultado);

        } catch (Exception e) {

        }
        return resultado;
    }

    private static List<Nodo> getNodosExpresion(List<Expression> listado, Nodo fin, int Id) {
        Nodo finTemp = fin;
        List<Nodo> Resultado = new ArrayList();
        for (int i = listado.size() - 1; i > -1; i--) {
            Nodo n = new Nodo(Id);
            n.setTexto(listado.get(i).toString());
            n.addSiguiente(finTemp);
            Resultado.add(n);
            finTemp = n;
            Id++;
        }

        return Resultado;
    }

    private static void addNullSiguiente(List<Nodo> nodos, Nodo fin) {
        for (Nodo nodo : nodos) {
            if (nodo.getSiguiente().size() < 1) {
                nodo.addSiguiente(fin);
            }
        }
    }

}
