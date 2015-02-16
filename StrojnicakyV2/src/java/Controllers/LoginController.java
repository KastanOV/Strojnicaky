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
    private Boolean loggedUserIsAdmin;

    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
    /**
     * Creates a new instance of login
     * @return 
     */
    public Boolean getLoggedUserIsAdmin() {
        return loggedUserIsAdmin;
    }

    public void setLoggedUserIsAdmin(Boolean loggedUserIsAdmin) {
        this.loggedUserIsAdmin = loggedUserIsAdmin;
    }
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
        if(loggedUser.getRole().equals("admin")){
            setLoggedUserIsAdmin(true);
        }else {
            setLoggedUserIsAdmin(false);
        }
        if(loggedUser.getLname() != null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void doLogout(){
        loggedUser = new Users();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect("faces/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void initAplication(){
        Users tmpUser = new Users();
        tmpUser.setCeta(1000);
        tmpUser.setFname("admin");
        tmpUser.setLname("admin");
        tmpUser.setLogin("admin");
        tmpUser.setOec("admin");
        tmpUser.setPassword("admin");
        tmpUser.setPhone("admin");
        tmpUser.setRole("admin");
        tmpUser.setRota(1000);
        loginSB.initAplication(tmpUser);
    }
}
