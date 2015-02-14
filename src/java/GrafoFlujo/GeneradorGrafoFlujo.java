/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoFlujo;

import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class GeneradorGrafoFlujo {

    private static List<Nodo> Grafo;
    
    public GeneradorGrafoFlujo() {
    }

    public String dibujarDiagrama(BlockStmt body) {

        List<Nodo> parseBody = parseBody(body);

        return ParseSentencias(parseBody);
    }

    public List<Nodo> parseBody(BlockStmt body) {
        List<Nodo> resultado = new ArrayList();
        Nodo finTemp;
        Nodo inicioTemp = null;
        Nodo inicio = new Nodo(0);
        int contadorNodo = 0;
        inicio.setTexto("Inicio");

        Nodo fin = new Nodo(-1);

        fin.setTexto("Fin");
        finTemp = fin;
        contadorNodo++;

        List<Statement> stmts = body.getStmts();
        resultado.add(fin);
        try {
            for (int i = (stmts.size() - 1); i > -1; i--) {
                List<Nodo> nodos = Statements.getNodos(stmts.get(i), finTemp, contadorNodo);
                if (nodos.size() > 0) {
                    finTemp = nodos.get(0);

                    for (Nodo nodo : nodos) {
                        resultado.add(nodo);
                        contadorNodo++;
                    }
                }
            }

            resultado.add(inicio);
            resultado = reverseNodos(resultado);
            inicio.addSiguiente(finTemp);
        } catch (Exception e) {
        }
        fin.setId(resultado.size() - 1);
        Grafo = resultado;
        return resultado;
    }

    private String ParseSentencias(List<Nodo> nodos) {

        String resultado = "{ \n";
        resultado += "\"nodes\": { \n";
        for (int i = 0; i < nodos.size(); i++) {
            if(i ==  nodos.size() - 1){
                resultado += "\""+ nodos.get(i).getId() +"\": {} \n";
            }else{
                resultado += "\""+ nodos.get(i).getId() +"\": {}, \n";
            }
        }
        resultado += "},\n";
        resultado += "\"edges\": {\n";
        for (int i = 0; i < nodos.size(); i++) {
            if(i ==  nodos.size() - 1){
                resultado += NodosAsociados(nodos.get(i), true);
            }else{
                resultado += NodosAsociados(nodos.get(i), false);
            }
        }
        resultado += "}";
        return resultado + "\n}";
    }

    private String NodosAsociados(Nodo n, boolean ultimo) {
        String resultado = "\""+n.getId()+"\": {";
        List<Nodo> listado = n.getSiguiente();
        for (int i = 0; i < listado.size(); i++) {
            if (i == listado.size() - 1) {
                resultado += "\""+ listado.get(i).getId() +"\": {} \n";
            }else{
                resultado += "\""+ listado.get(i).getId() +"\": {}, \n";
            }
        }
        if(ultimo){
            return resultado + "}\n";
        }else{
            return resultado + "},\n";
        }
    }

    private List<Nodo> reverseNodos(List<Nodo> nodos) {
        List<Nodo> resultado = new ArrayList();

        for (int i = (nodos.size() - 1); i > -1; i--) {
            resultado.add(nodos.get(i));
        }

        return resultado;
    }

    public static List<Nodo> getGrafo() {
        return Grafo;
    }
        
    
    

}
