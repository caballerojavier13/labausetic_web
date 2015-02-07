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

    private List<Nodo> Nodos;
    private boolean getGrafo = true;
    /**
     * Creates a new instance of MatrizAdjunta
     */
    public MatrizAdjunta() {
        
    }
    
    public List<Nodo> getNodos(){
        if(getGrafo){
            Nodos = GeneradorGrafoFlujo.getGrafo();
            
            Nodos = ordernarNodos(Nodos);
            getGrafo = false;
        }
        return  Nodos;
    }
    
    
    private List<Nodo> ordernarNodos(List<Nodo> listado){
        List<Nodo> Resultado = new ArrayList();
        List<Nodo> temporal = listado;
        while(temporal.size() > 0){
            int menor = findMenor(temporal);
            Resultado.add(temporal.get(menor));
            temporal.remove(menor);
        }
        
        return Resultado;
    }
    
    private int findMenor(List<Nodo> listado){
        List<Nodo> temporal = listado;
        int Resultado = 0;
        int menor = temporal.get(0).getId();
        
        for(int i=1; i< temporal.size();i++){
            if(temporal.get(i).getId() < menor){
                menor = temporal.get(i).getId();
                Resultado = i;
            }
        }
        return Resultado;
    }
    
    public int listContains(List<Nodo> listado, Nodo nodo){
        int Resultado = 0;
        for(int i=0; i<listado.size(); i++){
            if(listado.get(i).getId() == nodo.getId()){
                Resultado = 1;
                break;
            }
        }
        return Resultado;
    }
    
}
