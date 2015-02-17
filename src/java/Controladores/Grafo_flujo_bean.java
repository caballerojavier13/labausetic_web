/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DTO.Metodo;
import DTO.Sentencia;
import Expertos.Redirector;
import GrafoFlujo.GeneradorGrafoFlujo;
import GrafoFlujo.Nodo;
import japa.parser.ast.stmt.BlockStmt;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author javier
 */
@ManagedBean
@ViewScoped
public class Grafo_flujo_bean {

    private List<Nodo> diagrama;

    private List<Nodo> diagrama_ordenado;

    private Metodo refMetodo;

    private String ParseSentencias;

    public Grafo_flujo_bean() {
        try {

            refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();

            BlockStmt body = refMetodo.getBody().getBody();

            diagrama = new GeneradorGrafoFlujo().parseBody(body);

            ParseSentencias = new GeneradorGrafoFlujo().ParseSentencias(diagrama);

            diagrama_ordenado = this.ordernarNodos(this.diagrama);

        } catch (Exception e) {

            new Redirector().RedireccionarHome();

        }
    }

    public Metodo getRefMetodo() {
        return refMetodo;
    }

    public void setRefMetodo(Metodo refMetodo) {
        this.refMetodo = refMetodo;
    }

    public String dibujarDiagrama() {
        return ParseSentencias;
    }

    public List<Nodo> getDiagrama_ordenado() {
        return diagrama_ordenado;
    }

    private List<Nodo> ordernarNodos(List<Nodo> listado) {
        List<Nodo> Resultado = new ArrayList();
        List<Nodo> temporal = listado;
        while (temporal.size() > 0) {
            int menor = findMenor(temporal);
            Resultado.add(temporal.get(menor));
            temporal.remove(menor);
        }

        return Resultado;
    }

    private int findMenor(List<Nodo> listado) {
        List<Nodo> temporal = listado;
        int Resultado = 0;
        int menor = temporal.get(0).getId();

        for (int i = 1; i < temporal.size(); i++) {
            if (temporal.get(i).getId() < menor) {
                menor = temporal.get(i).getId();
                Resultado = i;
            }
        }
        return Resultado;
    }

}
