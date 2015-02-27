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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author javier
 */
@ManagedBean
@SessionScoped
public class Primero_en_profundidad_bean {

    private List<NodoAlgoritmo> grafo;
    private int camino_seleccionado;
    private List<List<Nodo>> algoritmo;

    /**
     * Creates a new instance of PrimeroEnProfundidad
     */
    public Primero_en_profundidad_bean() {

    }

    public String start() {
        grafo = new ArrayList();
        Metodo refMetodo;
        try {
            refMetodo = ((FileBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileBean")).getRefSelectedMetodo();
            List<Nodo> grafo_temp = new GeneradorGrafoFlujo().parseBody(refMetodo.getBody().getBody());
            for (int i = 0; i < grafo_temp.size(); i++) {
                grafo.add(new NodoAlgoritmo(grafo_temp.get(i)));
            }
            algoritmo = find_caminos_alternativos();
            camino_seleccionado = 0;
        } catch (Exception e) {
            new Redirector().RedireccionarHome();
        }
        return "algoritmo_primero_profundidad";
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
                        nodo_temp = findNodoAlgortimo(grafo, Id);
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
                        } else {
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

    private NodoAlgoritmo findNodoAlgortimo(List<NodoAlgoritmo> listado, int Id) {
        NodoAlgoritmo Resultado = null;
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getNodo().getId() == Id) {
                Resultado = listado.get(i);
                break;
            }
        }
        return Resultado;
    }

    private Nodo findNodo(List<Nodo> listado, int Id) {
        Nodo Resultado = null;
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getId() == Id) {
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
    
    public boolean inAlgoritmoActual(int Id) {
        boolean Resultado = false;
        for(int  i=0; i< algoritmo.get(camino_seleccionado).size(); i++){
            if(algoritmo.get(camino_seleccionado).get(i).getId() == Id){
                Resultado = true;
                break;
            }
        }
        return Resultado;
    }

    public void ejecutarAlgoritmo() {
        algoritmo = find_caminos_alternativos();
    }

    private boolean siguientesVisitados(NodoAlgoritmo nodo) {
        boolean Resultado = true;
        List<Nodo> siguiente = nodo.getNodo().getSiguiente();
        for (int i = 0; i < siguiente.size(); i++) {
            if (!findNodoAlgortimo(this.grafo, siguiente.get(i).getId()).isVisitado()) {
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
            NodoAlgoritmo temp = findNodoAlgortimo(this.grafo, siguiente.get(i).getId());
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

    public String dibujarCamino() {
        String diagrama = ParseSentencias(grafo);

        return diagrama;
    }

    private String ParseSentencias(List<NodoAlgoritmo> nodos) {

        String resultado = "{ \n";
        resultado += "\"nodes\": { \n";
        for (int i = 0; i < nodos.size(); i++) {
            if (findNodo(algoritmo.get(camino_seleccionado), nodos.get(i).getNodo().getId()) == null) {
                if (i == nodos.size() - 1) {
                    resultado += "\"" + nodos.get(i).getNodo().getId() + "\": {} \n";
                } else {
                    resultado += "\"" + nodos.get(i).getNodo().getId() + "\": {}, \n";
                }
            } else {
                if (i == nodos.size() - 1) {
                    resultado += "\"" + nodos.get(i).getNodo().getId() + "\": {\"color\":\"red\"} \n";
                } else {
                    resultado += "\"" + nodos.get(i).getNodo().getId() + "\": {\"color\":\"red\"}, \n";
                }
            }
        }
        resultado += "},\n";
        resultado += "\"edges\": {\n";
        for (int i = 0; i < nodos.size(); i++) {
            if (i == nodos.size() - 1) {
                resultado += NodosAsociados(nodos.get(i).getNodo(), true);
            } else {
                resultado += NodosAsociados(nodos.get(i).getNodo(), false);
            }
        }
        resultado += "}";
        return resultado + "\n}";
    }

    private String NodosAsociados(Nodo n, boolean ultimo) {
        String resultado = "\"" + n.getId() + "\": {";
        List<Nodo> listado = n.getSiguiente();
        for (int i = 0; i < listado.size(); i++) {
            if (nexoCamino(n, listado.get(i))) {
                if (i == listado.size() - 1) {
                    resultado += "\"" + listado.get(i).getId() + "\": {\"color\":\"red\"} \n";
                } else {
                    resultado += "\"" + listado.get(i).getId() + "\": {\"color\":\"red\"}, \n";
                }
            } else {
                if (i == listado.size() - 1) {
                    resultado += "\"" + listado.get(i).getId() + "\": {} \n";
                } else {
                    resultado += "\"" + listado.get(i).getId() + "\": {}, \n";
                }
            }
        }
        if (ultimo) {
            return resultado + "}\n";
        } else {
            return resultado + "},\n";
        }
    }

    public int getCamino_seleccionado() {
        return camino_seleccionado;
    }
    
    public void setCamino_seleccionado(int camino_seleccionado) {
        this.camino_seleccionado = camino_seleccionado;
    }
    
    public String verCamino_alternativo(int camino_seleccionado) {
        this.camino_seleccionado = camino_seleccionado;
        return "./primero_en_profundidad/grafo_flujo.xhtml";
    }

    private boolean nexoCamino(Nodo nodo, Nodo siguiente) {
        boolean Resultado = false;

        List<Nodo> listado = algoritmo.get(camino_seleccionado);

        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getId() == nodo.getId()) {
                if (i + 1 < listado.size() && listado.get(i + 1).getId() == siguiente.getId()) {
                    Resultado = true;
                    break;
                }
            }
        }

        return Resultado;
    }

}
