/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Seznamopravneni;
import SessionBeans.opravneniSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class OpravneniController {
    @EJB
    private opravneniSBLocal oprSB;
    
    private Seznamopravneni editedOpr;

    public Seznamopravneni getEditedOpr() {
        return editedOpr;
    }

    public void setEditedOpr(Seznamopravneni editedOpr) {
        this.editedOpr = editedOpr;
    }
    /**
     * Creates a new instance of OpravneniController
     */
    public OpravneniController() {
    }
    
    public List<Seznamopravneni> getAllopravneni(){
        return oprSB.getAllOpravneni();
    }
    public void deleteOpravneni(Seznamopravneni s){
        oprSB.deleteOpravneni(s);
    }
    public void saveOpravneni(){
        oprSB.saveSeznamOpravneni(editedOpr);
        RequestContext.getCurrentInstance().addCallbackParam("saved", true);
    }
    public Seznamopravneni prepareNewOpr(){
        editedOpr = new Seznamopravneni();
        return editedOpr;
    }
}
