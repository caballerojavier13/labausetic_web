/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiagramaFlujo;

/**
 *
 * @author javier
 */
public class Nodo {
  private int Id;
  private String texto;
  
  private Nodo siguiente;
  private Nodo siguienteAlt;  
  private boolean permiteAlt;
  
  private TipoNodo tipo;

  public Nodo() {
  }

  public Nodo(int Id) {
    this.Id = Id;
    permiteAlt = false;
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

  public Nodo getSiguiente() {
    return siguiente;
  }

  public void setSiguiente(Nodo siguiente) {
    this.siguiente = siguiente;
  }

  public TipoNodo getTipo() {
    return tipo;
  }

  public void setTipo(TipoNodo tipo) {
    this.tipo = tipo;
  }

  public Nodo getSiguienteAlt() {
    return siguienteAlt;
  }

  public void setSiguienteAlt(Nodo siguienteAlt) {
    this.siguienteAlt = siguienteAlt;
  }

  public boolean isPermiteAlt() {
    return permiteAlt;
  }

  public void setPermiteAlt(boolean permiteAlt) {
    this.permiteAlt = permiteAlt;
  }
  
  
}
