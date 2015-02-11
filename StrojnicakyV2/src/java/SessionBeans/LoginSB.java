/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.HashPassword;
import Entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        String passTmp = HashPassword.md5Hash(u.getPassword());
        try{
            Users tmp = (Users) em.createNativeQuery("select * from users where login = ?login and password = ?pass", Users.class)
                .setParameter("login", u.getLogin())
                .setParameter("pass", passTmp)
                .getSingleResult();
            u.setPassword(passTmp);
            return tmp;
        } catch(Exception e){
            return u;
        }
    }
}
