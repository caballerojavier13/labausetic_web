/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoFlujo;

/**
 *
 * @author javier
 */
public class Link {

  private Nodo origen;
  private Nodo destino;
  private Nodo destino2;

  public Link() {
  }

  public Link(Nodo origen, Nodo destino) {
    this.origen = origen;
    this.destino = destino;
  }

  public Nodo getOrigen() {
    return origen;
  }

  public void setOrigen(Nodo origen) {
    this.origen = origen;
  }

  public Nodo getDestino() {
    return destino;
  }

  public void setDestino(Nodo destino) {
    this.destino = destino;
  }

  public Nodo getDestino2() {
    return destino2;
  }

  public void setDestino2(Nodo destino2) {
    this.destino2 = destino2;
  }
  
  
}
