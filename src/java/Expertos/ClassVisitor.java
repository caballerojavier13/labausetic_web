package Expertos;

import DTO.Clase;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ClassVisitor extends VoidVisitorAdapter {

  private Clase clase;

  public ClassVisitor() {

  }

  public Clase getClase() {
    return clase;
  }

  public void setClase(Clase clase) {
    this.clase = clase;
  }

  @Override
  public  void visit(ClassOrInterfaceDeclaration n, Object arg) {
    if (!n.isInterface()) {

      String className = n.getName();
      clase = new Clase(className);
      String extendsClass;
      try {
        extendsClass = n.getExtends().get(0).toString();
      } catch (Exception e) {
        extendsClass = "";
      }
      String packageName = (String) arg;
      String mergedName;
      if (packageName.length() > 0) {
        mergedName = packageName + "." + className;
      } else {
        mergedName = className;
      }
      //System.out.println(mergedName + " ---> " + extendsClass);
      for (int i = 0; i < n.getMembers().size(); i++) {
        VisitInner(n.getMembers().get(i), mergedName);
      }
    } else {
    }
  }

  private void VisitInner(BodyDeclaration n, String prefix) {
    if (n instanceof ClassOrInterfaceDeclaration) {
      ClassOrInterfaceDeclaration c = (ClassOrInterfaceDeclaration) n;
      if (!c.isInterface()) {
        String className = c.getName();
        String extendsClass;
        try {
          extendsClass = c.getExtends().get(0).toString();
        } catch (Exception e) {
          extendsClass = "";
        }
        String packageName = prefix;
        System.out.println(packageName + "." + className + " ---> " + extendsClass);
        for (int i = 0; i < c.getMembers().size(); i++) {
          VisitInner(c.getMembers().get(i), packageName + "." + c.getName());
        }
      }
    } else {
      if (n instanceof MethodDeclaration) {
        MethodDeclaration m = (MethodDeclaration) n;
        for (int i = 0; i < m.getBody().getStmts().size(); i++) {
          if (m.getBody().getStmts().get(i) instanceof TypeDeclarationStmt) {
            InputStream is = new ByteArrayInputStream(m.getBody().getStmts().get(i).toString().getBytes());
            CompilationUnit cu = null;
            try {
              new ClassVisitor().visit(cu, prefix);
            } catch (Exception e) {
              System.out.println(e.toString());
            }
          }
        }
      }
    }
  }
}
