/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class LoginSB implements LoginSBLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Users tryLogin(Users u) {
        //String passTmp = HashPassword.md5Hash(u.getPassword());
        try{
            Users tmp = (Users) em.createNativeQuery("select * from users where login = ?login and password = ?pass", Users.class)
                .setParameter("login", u.getLogin())
                .setParameter("pass", u.getPassword())
                .getSingleResult();
            u.setPassword(u.getPassword());
            return tmp;
        } catch(Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Chyba při přihlašování", "Zřejmě jste vložil špatné přihlašovací údaje. Zkuste to prosím znovu.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return u;
        }
    }
    @Override
    public Users initAplication(Users u) {
       
        em.persist(u);
        em.flush();
        return u;
    }
}
