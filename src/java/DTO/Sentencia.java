/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import japa.parser.ast.stmt.Statement;
import java.util.List;

/**
 *
 * @author javier
 */
public class Sentencia {

  private int tipoSentencia;
  private Statement sentencia;
  private String Nombre;

  private List<Sentencia> refSentencias;

  public Sentencia() {
  }

  public Sentencia(String Nombre) {
    this.Nombre = Nombre;
  }

  public int getTipoSentencia() {
    return tipoSentencia;
  }

  public void setTipoSentencia(int tipoSentencia) {
    this.tipoSentencia = tipoSentencia;
  }

  public Statement getSentencia() {
    return sentencia;
  }

  public void setSentencia(Statement sentencia) {
    this.sentencia = sentencia;
  }

  public String getNombre() {
    return Nombre;
  }

  public void setNombre(String Nombre) {
    this.Nombre = Nombre;
  }

  public List<Sentencia> getRefSentencias() {
    return refSentencias;
  }

  public void setRefSentencias(List<Sentencia> refSentencias) {
    this.refSentencias = refSentencias;
  }

}
