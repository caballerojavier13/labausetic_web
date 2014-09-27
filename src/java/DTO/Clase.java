package DTO;

import japa.parser.ast.CompilationUnit;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javier
 */
public class Clase {
  
  private String Nombre;
  private CompilationUnit refCompilationUnit;
  
  public Clase() {
  }

  public Clase(String Nombre) {
    this.Nombre = Nombre;
    
  }


  public String getNombre() {
    return Nombre;
  }

  public void setNombre(String Nombre) {
    this.Nombre = Nombre;
  }

  public CompilationUnit getRefCompilationUnit() {
    return refCompilationUnit;
  }

  public void setRefCompilationUnit(CompilationUnit refCompilationUnit) {
    this.refCompilationUnit = refCompilationUnit;
  }

  
  
}
