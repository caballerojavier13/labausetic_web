/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author javier
 */
@ManagedBean
@SessionScoped
public class complejidad_ciclomatica_bean {
    private int complejidad;
    /**
     * Creates a new instance of complejidad_ciclomatica_bean
     */
    public complejidad_ciclomatica_bean() {

    }

    public int getComplejidad() {
        try {
            Primero_en_profundidad_bean primero_bean = new Primero_en_profundidad_bean();
            
            primero_bean.start();
            
            complejidad = primero_bean.getAlgoritmo().size();
            
        } catch (NullPointerException e) {
            complejidad = 0;
        }
        return complejidad;
    }
    
    
}
