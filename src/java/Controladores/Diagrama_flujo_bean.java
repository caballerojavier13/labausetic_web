/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DTO.Metodo;
import DTO.Sentencia;
import DiagramaFlujo.GeneradorDiagramaFlujo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author javier
 */
@ManagedBean
@ViewScoped
public class Diagrama_flujo_bean implements Serializable {

    List<Sentencia> diagrama;

    private Metodo refMetodo;

    public Diagrama_flujo_bean() {
        try {
            refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();

        } catch (Exception e) {
            redireccionar();
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
            return new GeneradorDiagramaFlujo().dibujarDiagrama(refMetodo.getBody().getBody());
        } catch (Exception e) {
            redireccionar();
            return null;
        }

    }

    private void redireccionar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./select_proyecto.xhtml");
        } catch (IOException ex) {
        }
    }

}
