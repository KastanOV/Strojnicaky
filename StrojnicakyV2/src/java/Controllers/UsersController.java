/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Opravneni;
import Entity.Seznamopravneni;
import Entity.Users;
import SessionBeans.UsersSBLocal;
import SessionBeans.opravneniSBLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class UsersController {
    
    @EJB
    private UsersSBLocal usersSB;
    @EJB
    private opravneniSBLocal oprSB;
    private Users loggedUser;
    private Users editedUser;
    private Boolean editButton;
    private Boolean loggedUserIsAdmin;
    private Date expirationDateTmp;

    public Date getExpirationDateTmp() {
        return expirationDateTmp;
    }

    public void setExpirationDateTmp(Date expirationDateTmp) {
        this.expirationDateTmp = expirationDateTmp;
    }
    
    private String AutoComplHelper;

    public String getAutoComplHelper() {
        return AutoComplHelper;
    }

    public void setAutoComplHelper(String AutoComplHelper) {
        this.AutoComplHelper = AutoComplHelper;
    }

    public void saveUser(){
        usersSB.saveUser(editedUser);
        RequestContext.getCurrentInstance().addCallbackParam("saved", true);
    }
    public List<String> completeText(String query){
        List<Seznamopravneni> tmp = oprSB.AutoCompleteHelper(query);
        List<String> retVal = new ArrayList<>();
        for(Seznamopravneni item : tmp){
            retVal.add(item.getShortname());
        }
        return retVal;
    }
    public void addOpravneniForUser(){
        Opravneni tmp = new Opravneni();
        Seznamopravneni oprTmp = null;
        try{
            oprTmp = oprSB.getSenzmaopravneniByID(AutoComplHelper);
        } catch (Exception e) {
            System.err.println("Error");
        }
        tmp.setDatumexpirace(expirationDateTmp);
        tmp.setSeznamopravneniShortname(oprTmp);
        tmp.setUsersLogin(editedUser);
        oprSB.addOpravneni(tmp);
    }
    public Boolean getEditButton() {
        if(loggedUser.getRole().equals("admin") || loggedUser.getRole().equals("vc")){
            editButton = true;
        } else {
            editButton = false;
        }
        return editButton;
    }
    public void viewEditUsersDialog() {
        RequestContext.getCurrentInstance().openDialog("UsersEditDialog");
    }
    public void setEditButton(Boolean editButton) {
        this.editButton = editButton;
    }

    public UsersController() {
        AutoComplHelper = new String();
    }
    
    public List<Users> getUsers(){
        return usersSB.getUsers(loggedUser);
    }
    public void saveOpravneni(Opravneni o){
        //TODO Nekde tu je chyba jak cip 
        oprSB.saveOpravneni(o);
    }
    public List<Opravneni> getOpravneni(){
        if(editedUser != null){
            return oprSB.getOpravneniByUser(editedUser);
        } else {
            return null;
        }
    }
    public void deleteOpravneni(Opravneni o){
        oprSB.deleteOpravneni(o);
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
    public Boolean getLoggedUserIsAdmin() {
        return loggedUserIsAdmin;
    }

    public void setLoggedUserIsAdmin(Boolean loggedUserIsAdmin) {
        this.loggedUserIsAdmin = loggedUserIsAdmin;
    }
    public void testmethod(){
        
    }
}
