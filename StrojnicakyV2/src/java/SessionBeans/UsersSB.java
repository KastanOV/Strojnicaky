/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class UsersSB implements UsersSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Users getUser(String value){
        return em.find(Users.class, value);
    }

    @Override
    public List<Users> getUsers(Users u) {
        StringBuilder query = new StringBuilder();
        query.append("select * from users ");
        if(u.getRole().equals("admin") || u.getRole().equals("observ")){
            return em.createNativeQuery(query.toString(), Users.class).getResultList();
        } else if (u.getRole().equals("vr")){
            query.append(" where rota = ?rota");
            return em.createNativeQuery(query.toString(), Users.class)
                    .setParameter("rota", u.getRota())
                    .getResultList();
        }else if (u.getRole().equals("vc")){
            query.append(" where ceta = ?ceta");
            return em.createNativeQuery(query.toString(), Users.class)
                    .setParameter("ceta", u.getCeta())
                    .getResultList();
        }
        return null;
    }
    
    @Override
    public Users saveUser(Users u) {
        if(u.getLogin() == null){
            getFreeLogin(u);
            createPassword(u);
            em.persist(u);
            em.flush();
            return u;
        } else {
            em.merge(u);
            em.flush();
            return u;
        }
    }
    
    
    
    private void createPassword(Users s){
        char[] symbols;
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
        Random random = new Random();
        
        StringBuilder value = new StringBuilder();

        for (int idx = 0; idx < 10; ++idx) 
            value.append(symbols[random.nextInt(symbols.length)]);
        s.setPassword(value.toString());
    }
    
    private void getFreeLogin(Users s){
        String LoginPrefix = removeDiak(s.getLname()
                .substring(0,3)
                .toUpperCase()
                .trim());
        Long numberOfLogin = (Long) em.createNativeQuery("SELECT COUNT(*) FROM users WHERE login LIKE ?createLogin")
                .setParameter("createLogin", LoginPrefix + "%")
                .getSingleResult();
        s.setLogin(LoginPrefix + getPostFix(String.valueOf(numberOfLogin)));
    }
    private String getPostFix(String Postfix){
        switch(Postfix.length()){
            case 1 : return "00" + Postfix;
            case 2 : return "0" + Postfix;
            default : return Postfix;    
        }
    };
    private String removeDiak(String retazec){
       String retazecBD="";
       String sdiak="áäčďěéíĺžňóöôŕřšťúüýžźÁÄČĎĚÉÍĹŇÓÖÔŔŘŤÚÜÝŠŽŐőÖöŰűÜü";
       String bdiak="aacdeeillnooorrstuuyzzAACDEEILNOOORRTUUYSZOoOoUuUu";
       for (int l=0;l<retazec.length();l++){
           if (sdiak.indexOf(retazec.charAt(l))!=-1)
               retazecBD+=bdiak.charAt(sdiak.indexOf(retazec.charAt(l)));
           else
               retazecBD+=retazec.charAt(l);
       }
       return retazecBD;
   }
}
