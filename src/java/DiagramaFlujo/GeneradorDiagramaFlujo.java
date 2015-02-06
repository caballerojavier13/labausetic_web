/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaFlujo;

import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class GeneradorDiagramaFlujo {

    public GeneradorDiagramaFlujo() {
    }

    public String dibujarDiagrama(BlockStmt body) {

        List<Nodo> parseBody = parseBody(body);

        List<Link> linkearNodos = linkearNodos(parseBody);

        return ParseSentencias(parseBody, linkearNodos);

    }

    private List<Nodo> parseBody(BlockStmt body) {
        List<Nodo> resultado = new ArrayList();
        Nodo finTemp = null;
        Nodo inicioTemp = null;
        Nodo inicio = new Nodo(0);
        int contadorNodo = 0;
        inicio.setTexto("Inicio");
        inicio.setTipo(TipoNodo.START);
        resultado.add(inicio);

        contadorNodo++;

        List<Statement> stmts = body.getStmts();

        try {
            for (int i = (stmts.size() - 1); i > -1; i--) {
                List<Nodo> nodos = Statements.getNodos(stmts.get(i), finTemp, contadorNodo);
                if (nodos.size() > 0) {
                    finTemp = nodos.get(0);

                    inicio.setSiguiente(nodos.get(0));

                    for (Nodo nodo : nodos) {
                        resultado.add(nodo);
                        contadorNodo++;
                    }
                }
            }
            Nodo fin = new Nodo(resultado.size());

            fin.setTexto("Fin");
            fin.setTipo(TipoNodo.END);
            Nodo NodoRetorno = findRetorno(resultado);
            if(NodoRetorno == null){
                NodoRetorno = new Nodo(resultado.size() + 1);
                NodoRetorno.setTexto("VOID");
                NodoRetorno.setRetorno(true);
                NodoRetorno.setTipo(TipoNodo.OPERATION);
            }
            resultado.add(NodoRetorno);
            NodoRetorno.setSiguiente(fin);
            Statements.setNullSiguiente(resultado, NodoRetorno, false);
            Statements.setNullSiguienteAlt(resultado, NodoRetorno); 
            resultado.add(fin);
        } catch (Exception e) {

        }

        return resultado;
    }
    private Nodo findRetorno(List<Nodo> nodos) {
        Nodo resultado = null;
        for(Nodo n : nodos){
            if(n.isRetorno()){
                resultado = n;
                break;
            }
        }
        return resultado;
    }
    private List<Link> linkearNodos(List<Nodo> nodos) {
        List<Link> resultado = new ArrayList();

        for (Nodo nodo : nodos) {
            Link l;
            if (nodo.getSiguiente() != null) {
                l = new Link(nodo, nodo.getSiguiente());
                resultado.add(l);
                if (nodo.isPermiteAlt()) {
                    l.setDestino2(nodo.getSiguienteAlt());
                }
            }
        }
        return resultado;
    }

    private String ParseSentencias(List<Nodo> nodos, List<Link> links) {

        String resultado = "";

        for (Nodo nodo : nodos) {
            if (nodo.getTipo() == TipoNodo.CONDITION) {
                resultado = resultado + "c" + nodo.getId() + "=>condition: " + nodo.getTexto() + "\n";
            } else {
                if (nodo.getTipo() == TipoNodo.OPERATION) {
                    resultado = resultado + "op" + nodo.getId() + "=>operation: " + nodo.getTexto() + "\n";
                } else {
                    if (nodo.getTipo() == TipoNodo.END) {
                        resultado = resultado + "fin=>end: " + nodo.getTexto() + "\n";
                    } else {
                        if (nodo.getTipo() == TipoNodo.START) {
                            resultado = resultado + "inicio=>start: " + nodo.getTexto() + "\n";
                        } else {

                        }
                    }
                }
            }

        }

        resultado = resultado + "\n";

        for (Link link : links) {

            if (link.getOrigen().getTipo() == TipoNodo.CONDITION) {
                resultado = resultado + "c" + link.getOrigen().getId() + "(yes)->";
                if (link.getDestino().getTipo() == TipoNodo.CONDITION) {
                    resultado = resultado + "c" + link.getDestino().getId() + "\n";
                } else {
                    if (link.getDestino().getTipo() == TipoNodo.OPERATION) {
                        resultado = resultado + "op" + link.getDestino().getId() + "\n";
                    } else {
                        if (link.getDestino().getTipo() == TipoNodo.END) {
                            resultado = resultado + "fin \n";
                        } else {
                            if (link.getDestino().getTipo() == TipoNodo.START) {
                                resultado = resultado + "inicio \n";
                            } else {

                            }
                        }
                    }
                }
                resultado = resultado + "c" + link.getOrigen().getId() + "(no)->";
                if (link.getDestino2().getTipo() == TipoNodo.CONDITION) {
                    resultado = resultado + "c" + link.getDestino2().getId() + "\n";
                } else {
                    if (link.getDestino2().getTipo() == TipoNodo.OPERATION) {
                        resultado = resultado + "op" + link.getDestino2().getId() + "\n";
                    } else {
                        if (link.getDestino2().getTipo() == TipoNodo.END) {
                            resultado = resultado + "fin \n";
                        } else {
                            if (link.getDestino2().getTipo() == TipoNodo.START) {
                                resultado = resultado + "inicio \n";
                            } else {

                            }
                        }
                    }
                }
            } else {
                if (link.getOrigen().getTipo() == TipoNodo.OPERATION) {
                    resultado = resultado + "op" + link.getOrigen().getId() + "->";
                    if (link.getDestino().getTipo() == TipoNodo.CONDITION) {
                        resultado = resultado + "c" + link.getDestino().getId() + "\n";
                    } else {
                        if (link.getDestino().getTipo() == TipoNodo.OPERATION) {
                            resultado = resultado + "op" + link.getDestino().getId() + "\n";
                        } else {
                            if (link.getDestino().getTipo() == TipoNodo.END) {
                                resultado = resultado + "fin \n";
                            } else {
                                if (link.getDestino().getTipo() == TipoNodo.START) {
                                    resultado = resultado + "inicio \n";
                                } else {

                                }
                            }
                        }
                    }
                } else {
                    if (link.getOrigen().getTipo() == TipoNodo.END) {
                        resultado = resultado + "fin->";
                        if (link.getDestino().getTipo() == TipoNodo.CONDITION) {
                            resultado = resultado + "c" + link.getDestino().getId() + "\n";
                        } else {
                            if (link.getDestino().getTipo() == TipoNodo.OPERATION) {
                                resultado = resultado + "op" + link.getDestino().getId() + "\n";
                            } else {
                                if (link.getDestino().getTipo() == TipoNodo.END) {
                                    resultado = resultado + "fin \n";
                                } else {
                                    if (link.getDestino().getTipo() == TipoNodo.START) {
                                        resultado = resultado + "inicio \n";
                                    } else {

                                    }
                                }
                            }
                        }
                    } else {
                        if (link.getOrigen().getTipo() == TipoNodo.START) {
                            resultado = resultado + "inicio->";
                            if (link.getDestino().getTipo() == TipoNodo.CONDITION) {
                                resultado = resultado + "c" + link.getDestino().getId() + "\n";
                            } else {
                                if (link.getDestino().getTipo() == TipoNodo.OPERATION) {
                                    resultado = resultado + "op" + link.getDestino().getId() + "\n";
                                } else {
                                    if (link.getDestino().getTipo() == TipoNodo.END) {
                                        resultado = resultado + "fin \n";
                                    } else {
                                        if (link.getDestino().getTipo() == TipoNodo.START) {
                                            resultado = resultado + "inicio \n";
                                        } else {

                                        }
                                    }
                                }
                            }
                        } else {

                        }
                    }
                }
            }

        }
        return resultado;
    }

    private List<Nodo> reverseNodos(List<Nodo> nodos) {
        List<Nodo> resultado = new ArrayList();

        for (int i = (nodos.size() - 1); i > -1; i--) {
            resultado.add(nodos.get(i));
        }

        return resultado;
    }

}
