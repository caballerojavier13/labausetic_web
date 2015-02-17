/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DTO.Clase;
import DTO.Metodo;
import Expertos.HerramientasMetodo;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.Part;

/**
 *
 * @author javier
 */
@ManagedBean
@SessionScoped
public class FileBean implements Serializable {

    private Clase clase;
    private Part ArchivoNombre;

    private String ArchivoContenido;

    private Metodo refSelectedMetodo;

    private boolean hayArchivo;
    private CompilationUnit cu;
    private List<Metodo> metodos;

    /**
     * Creates a new instance of FileBean
     */
    public FileBean() {
        clase = null;
        metodos = null;
        ArchivoNombre = null;
        ArchivoContenido = "";
        hayArchivo = false;
    }

    public String start() {
        clase = null;
        metodos = null;
        ArchivoNombre = null;
        ArchivoContenido = "";
        hayArchivo = false;
        return "./select_proyecto.xhtml";
    }

    public Metodo getRefSelectedMetodo() {
        return refSelectedMetodo;
    }

    public boolean isHayArchivo() {
        return hayArchivo;
    }

    public void setHayArchivo(boolean hayArchivo) {
        this.hayArchivo = hayArchivo;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Part getArchivoNombre() {
        return ArchivoNombre;
    }

    public void setArchivoNombre(Part ArchivoNombre) {
        this.ArchivoNombre = ArchivoNombre;
    }

    public String getArchivoContenido() {
        return ArchivoContenido;
    }

    public void setArchivoContenido(String ArchivoContenido) {
        this.ArchivoContenido = ArchivoContenido;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    public void subirArchivo(AjaxBehaviorEvent event) {

        try {
            InputStream inputStream = ArchivoNombre.getInputStream();
            cu = JavaParser.parse(inputStream);
            ArchivoContenido = cu.toString();
            clase = new Clase(cu.getTypes().get(0).getName());
            clase.setRefCompilationUnit(cu);
            //Optenemos Los metodos del archivo
            metodos = HerramientasMetodo.GetMetodos(cu);
            refSelectedMetodo = metodos.get(0);
        } catch (Exception e) {
            System.err.println("ERROR!!!");
            hayArchivo = false;
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    public void mostrarMetodo(int i) {
        ArchivoContenido = metodos.get(i).getBody().toString();
        refSelectedMetodo = metodos.get(i);
    }

    public void mostrarClase() {
        ArchivoContenido = clase.getRefCompilationUnit().toString();
        refSelectedMetodo = null;
    }

}
