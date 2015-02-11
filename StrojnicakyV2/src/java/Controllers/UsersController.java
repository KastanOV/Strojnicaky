/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Users;
import SessionBeans.UsersSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class UsersController {
    
    @EJB
    private UsersSBLocal usersSB;
    
    private LoginController logCtrl;

    public LoginController getLogCtrl() {
        return logCtrl;
    }

    public void setLogCtrl(LoginController logCtrl) {
        this.logCtrl = logCtrl;
        loggedUser = logCtrl.getLoggedUser();
    }
    
    private Users loggedUser;
    private Users editedUser;
    private Boolean editButton;
    
    public void saveUser(){
        usersSB.saveUser(editedUser);
    }

    public Boolean getEditButton() {
        if(loggedUser.getRole().equals("admin") || loggedUser.getRole().equals("vc")){
            editButton = true;
        } else {
            editButton = false;
        }
        return editButton;
    }

    public void setEditButton(Boolean editButton) {
        this.editButton = editButton;
    }

    public UsersController() {
    }
    
    public List<Users> getUsers(){
        return usersSB.getUsers(loggedUser);
    }
    
    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
    public Users getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(Users editedUser) {
        this.editedUser = editedUser;
    }
    public void prepareUser(){
        editedUser = new Users();
        if(loggedUser.getRole().equals("vc")){
            editedUser.setCeta(loggedUser.getCeta());
            editedUser.setRota(loggedUser.getRota());
            editedUser.setRole("uz");
        }
    }
}
