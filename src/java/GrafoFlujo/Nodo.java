/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafoFlujo;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author javier
 */
public class Nodo {
  private int Id;
  private String texto;
  private boolean retorno;
  
  private List<Nodo> siguiente;

  public Nodo() {
     siguiente = new ArrayList();
  }

  public Nodo(int Id) {
    this.Id = Id;
    siguiente = new ArrayList();
  }

  public int getId() {
    return Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

    public List<Nodo> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(List<Nodo> siguiente) {
        this.siguiente = siguiente;
    }

    public void addSiguiente(Nodo n){
        if(n != null){
            this.siguiente.add(n);
        }        
    }

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }
    
}
