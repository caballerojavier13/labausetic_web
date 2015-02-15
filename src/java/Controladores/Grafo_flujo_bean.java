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

    List<Sentencia> diagrama;

    private Metodo refMetodo;

    public Grafo_flujo_bean() {
        try {

            refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();

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

    public List<Sentencia> getDiagrama() {
        return diagrama;
    }

    public String dibujarDiagrama() {
        try {
            return new GeneradorGrafoFlujo().dibujarDiagrama(refMetodo.getBody().getBody());
        } catch (Exception e) {
            return "";
        }

    }
    
}
