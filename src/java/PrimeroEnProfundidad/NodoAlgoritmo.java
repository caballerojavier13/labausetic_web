/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimeroEnProfundidad;

import GrafoFlujo.Nodo;

/**
 *
 * @author javier
 */
public class NodoAlgoritmo {
    private Nodo nodo;
    private int backtrack;
    private boolean visitado;
    private int siguietes_visitados;
    
    public NodoAlgoritmo() {
        this.visitado = false;
        siguietes_visitados = 0;
    }


    public NodoAlgoritmo(Nodo nodo) {
        this.nodo = nodo;
        this.backtrack = nodo.getSiguiente().size() - 1;
        this.visitado = false;
        siguietes_visitados = 0;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
        this.backtrack = nodo.getSiguiente().size() - 1;
    }

    public int getBacktrack() {
        return backtrack;
    }
 
    public void oneBacktrack(){
        this.backtrack -= 1;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public int getSiguietes_visitados() {
        return siguietes_visitados;
    }

    public void setSiguietes_visitados(int siguietes_visitados) {
        this.siguietes_visitados = siguietes_visitados;
    }
    
    
}
