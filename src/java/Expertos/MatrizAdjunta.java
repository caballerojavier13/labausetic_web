/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expertos;

import GrafoFlujo.GeneradorGrafoFlujo;
import GrafoFlujo.Nodo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author javier
 */
@ManagedBean
@RequestScoped
public class MatrizAdjunta {

    /**
     * Creates a new instance of MatrizAdjunta
     */
    public MatrizAdjunta() {
        
    }
    
    public List<Nodo> getNodos(){
        return ordernarNodos(GeneradorGrafoFlujo.getGrafo());
    }
    
    
    private List<Nodo> ordernarNodos(List<Nodo> listado){
        return listado;
    }
}
