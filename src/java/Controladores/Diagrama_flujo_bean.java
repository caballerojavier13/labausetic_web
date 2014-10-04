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
import java.util.List;
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
public class Diagrama_flujo_bean {

  List<Sentencia> diagrama;

  private Metodo refMetodo;

  public Diagrama_flujo_bean() {
    try {
   
      refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();
    
    } catch (Exception e) {
      
      FacesContext facesContext = FacesContext.getCurrentInstance();

      ExternalContext ec = facesContext.getExternalContext();

      facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "index.xhtml");
      
      System.out.println("Intentamos redireccionar");

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
      return "";
    }

  }

}
