/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DTO.Metodo;
import Expertos.Redirector;
import GrafoFlujo.GeneradorGrafoFlujo;
import GrafoFlujo.Nodo;
import PrimeroEnProfundidad.NodoAlgoritmo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author javier
 */
@ManagedBean
@RequestScoped
public class Primero_en_profundidad_bean {

    private List<NodoAlgoritmo> grafo;

    private List<List<Nodo>> algoritmo;

    /**
     * Creates a new instance of PrimeroEnProfundidad
     */
    public Primero_en_profundidad_bean() {
        grafo = new ArrayList();
        Metodo refMetodo;
        try {
            refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();
            List<Nodo> grafo_temp = new GeneradorGrafoFlujo().parseBody(refMetodo.getBody().getBody());
            for (int i = 0; i < grafo_temp.size(); i++) {
                //System.out.println(grafo_temp.get(i).getId());
                grafo.add(new NodoAlgoritmo(grafo_temp.get(i)));
            }
            algoritmo = find_caminos_alternativos();
        } catch (Exception e) {
            new Redirector().RedireccionarHome();
        }

    }

    private List<List<Nodo>> find_caminos_alternativos() {
        List<List<Nodo>> Resultado = new ArrayList();
        boolean backtrack = true;
        NodoAlgoritmo nodo_temp;
        NodoAlgoritmo lastbacktrack = null;
        try {
            while (backtrack) {
                limpiarVisitado(grafo);
                backtrack = false;
                nodo_temp = grafo.get(0);
                List<Nodo> temp = new ArrayList();
                temp.add(nodo_temp.getNodo());
                nodo_temp.setVisitado(true);
                boolean loop = true;
                while (loop) {
                    if (nodo_temp.getBacktrack() > 0) {
                        backtrack = true;
                        lastbacktrack = nodo_temp;
                    }
                    if (nodo_temp.getNodo().getSiguiente().size() > 0) {
                        int Id = nodo_temp.getNodo().getSiguiente().get(nodo_temp.getBacktrack()).getId();
                        nodo_temp = findNodo(grafo, Id);
                        if (nodo_temp.isVisitado()) {
                            if (siguientesVisitados(nodo_temp)) {                                
                                loop = false;
                            } else {
                                temp.add(nodo_temp.getNodo());
                                nodo_temp.setVisitado(true);
                                
                                nodo_temp = firstSiguienteNoVisitado(nodo_temp);
                                temp.add(nodo_temp.getNodo());
                                nodo_temp.setVisitado(true);
                            }
                        }else{
                                temp.add(nodo_temp.getNodo());
                                nodo_temp.setVisitado(true);
                        }
                    } else {
                        loop = false;
                    }
                }
                Resultado.add(temp);
                if (backtrack) {
                    lastbacktrack.oneBacktrack();
                }
            }
            return Resultado;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private NodoAlgoritmo findNodo(List<NodoAlgoritmo> listado, int Id) {
        NodoAlgoritmo Resultado = null;
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getNodo().getId() == Id) {
                Resultado = listado.get(i);
                break;
            }
        }
        return Resultado;
    }

    public List<NodoAlgoritmo> getGrafo() {
        return grafo;
    }

    public List<List<Nodo>> getAlgoritmo() {
        return algoritmo;
    }

    public void ejecutarAlgoritmo() {
        algoritmo = find_caminos_alternativos();
    }

    private boolean siguientesVisitados(NodoAlgoritmo nodo) {
        boolean Resultado = true;
        List<Nodo> siguiente = nodo.getNodo().getSiguiente();
        for (int i = 0; i < siguiente.size(); i++) {
            if (!findNodo(this.grafo, siguiente.get(i).getId()).isVisitado()) {
                Resultado = false;
                break;
            }
        }
        return Resultado;
    }

    private NodoAlgoritmo firstSiguienteNoVisitado(NodoAlgoritmo nodo) {
        NodoAlgoritmo Resultado = null;
        List<Nodo> siguiente = nodo.getNodo().getSiguiente();
        for (int i = 0; i < siguiente.size(); i++) {
            NodoAlgoritmo temp = findNodo(this.grafo, siguiente.get(i).getId());
            if (!temp.isVisitado()) {
                Resultado = temp;
                break;
            }
        }
        return Resultado;
    }

    private void limpiarVisitado(List<NodoAlgoritmo> grafo) {
        for (int i = 0; i < grafo.size(); i++) {
            grafo.get(i).setVisitado(false);
        }
    }

}
