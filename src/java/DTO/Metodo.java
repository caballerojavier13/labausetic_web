/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import japa.parser.ast.body.MethodDeclaration;

/**
 *
 * @author javier
 */
public class Metodo {
  
  private int indice;
    
  private String Nombre;
  
  private MethodDeclaration Body;

  public Metodo() {
  }

  public Metodo(int indice, String Nombre, MethodDeclaration Body) {
    this.indice = indice;
    this.Nombre = Nombre;
    this.Body = Body;
  }

  public int getIndice() {
    return indice;
  }

  public void setIndice(int indice) {
    this.indice = indice;
  }

    

  /**
   * Get the value of Body
   *
   * @return the value of Body
   */
  public MethodDeclaration getBody() {
    return Body;
  }

  /**
   * Set the value of Body
   *
   * @param Body new value of Body
   */
  public void setBody(MethodDeclaration Body) {
    this.Body = Body;
  }


  /**
   * Get the value of Nombre
   *
   * @return the value of Nombre
   */
  public String getNombre() {
    return Nombre;
  }

  /**
   * Set the value of Nombre
   *
   * @param Nombre new value of Nombre
   */
  public void setNombre(String Nombre) {
    this.Nombre = Nombre;
  }

}
