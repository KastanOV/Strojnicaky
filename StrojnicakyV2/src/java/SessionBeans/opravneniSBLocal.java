/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Opravneni;
import Entity.Seznamopravneni;
import Entity.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface opravneniSBLocal {
    public List<Opravneni> getOpravneniByUser(Users u);
    public void deleteOpravneni(Opravneni o);
    public List<Seznamopravneni> getAllOpravneni();
    public void deleteOpravneni(Seznamopravneni s);
    public Seznamopravneni saveSeznamOpravneni(Seznamopravneni s);
    public List<Seznamopravneni> AutoCompleteHelper(String s);
    public void addOpravneni(Opravneni s);
    public void saveOpravneni(Opravneni o);
    public Seznamopravneni getSenzmaopravneniByID(String s);
}
