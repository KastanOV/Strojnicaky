/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Users;
import SessionBeans.LoginSBLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class LoginController {
    @EJB
    LoginSBLocal loginSB;

    Users loggedUser;

    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
    /**
     * Creates a new instance of login
     */
    public LoginController() {
        if(loggedUser == null){
            loggedUser = new Users();
        }
    }
    public void checkLogin (){
        if(loggedUser.getLname() == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect("faces/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void doLogin() {
        loggedUser = loginSB.tryLogin(loggedUser);
        if(loggedUser.getLname() != null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect("faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
