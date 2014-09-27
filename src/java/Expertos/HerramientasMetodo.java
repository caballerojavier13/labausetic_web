/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Expertos;

import DTO.Metodo;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class HerramientasMetodo {
  public static List<Metodo> GetMetodos(CompilationUnit cu) {
        List<TypeDeclaration> types = cu.getTypes();
        List<Metodo> resultado = new ArrayList();
        for (TypeDeclaration type : types) {
            List<BodyDeclaration> members = type.getMembers();
            int j = 0;
            for (BodyDeclaration member : members) {
                if (member instanceof MethodDeclaration) {
                    MethodDeclaration method = (MethodDeclaration) member;
                    resultado.add(new Metodo(j,method.getName(), method));
                    j++;
                    //System.out.println("  - Metodo: " + method.getName());
//                    AnalizarMetodo(method);
                } else {
//                    if (member instanceof FieldDeclaration) {
//                        FieldDeclaration field = (FieldDeclaration) member;
//                        List<VariableDeclarator> variables = field.getVariables();
//                        for (VariableDeclarator v : variables) {
//                            System.out.println("  - Variable de la Clase: " + v.getId().getName());
//                        }
//
//                    }
                }
            }
        }
        return resultado;
    }

    private static void AnalizarMetodo(MethodDeclaration method) {

        AnalizadorDeMetodo analizadorDeMetodo = new AnalizadorDeMetodo(method);

        List<Parameter> parameters = analizadorDeMetodo.ParametrosDeEntrada();
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                System.out.println("     - Variable de Entrada: " + parameter.getId().getName());
            }
        }

        analizadorDeMetodo.SentenciaReturn();
        analizadorDeMetodo.VariablesDeEstado();
    }
}
