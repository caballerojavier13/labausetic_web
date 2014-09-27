/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expertos;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author javier
 */
public class MethodVisitor extends VoidVisitorAdapter {

  public MethodVisitor() {
    
    super();
    
  }

  @Override
  public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
    // this method will be called for all methods in this 
    // CompilationUnit, including inner class methods
    System.out.println("Metodo: " + n.getName());

    System.out.println("Tipo de Retorno: " + n.getType().toString());
    System.out.println("");

    List<Parameter> parameters = n.getParameters();
    if (parameters != null) {
      System.out.println("Parametros:");
      for (Parameter p : parameters) {
        System.out.println("   - " + p.toString());
      }
    } else {
      System.out.println("No recibe parametros.");
    }
    System.out.println("");
    System.out.println(" --- o --- o ---");
    //System.out.println(n.toString());

  }
}
