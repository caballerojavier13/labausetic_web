/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expertos;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author javier
 */
public class Redirector {

    public void RedireccionarHome() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ExternalContext ec = facesContext.getExternalContext();

        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "index.xhtml");
    }

}
