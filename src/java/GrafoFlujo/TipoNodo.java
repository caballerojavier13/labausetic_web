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
public enum TipoNodo {

  CONDITION,
  OPERATION,
  START,
  END;

    private int valor;
  @Override
  public String toString() {

    switch (this) {
      case CONDITION:
        return "condition";
      case OPERATION:
        return "operation";
      case START:
        return "start";
      case END:
        return "end";
      default:

        return null;

    }
  }

  public int toInt() {

    switch (this) {
      case CONDITION:
        return 1;
      case OPERATION:
        return 2;
      case START:
        return 3;
      case END:
        return 4;
      default:
        return 0;

    }
  }

  public int getValor(TipoNodo tp) {
    return tp.toInt();
  }

}
