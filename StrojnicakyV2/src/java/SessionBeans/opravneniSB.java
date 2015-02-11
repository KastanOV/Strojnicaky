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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class opravneniSB implements opravneniSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Opravneni> getOpravneniByUser(Users u){
        return em.createNativeQuery("SELECT * FROM opravneni where Users_Login = ?login", Opravneni.class)
                .setParameter("login", u.getLogin())
                .getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void deleteOpravneni(Opravneni o) {
        em.remove(em.find(Opravneni.class, o.getId()));
    }

    @Override
    public List<Seznamopravneni> getAllOpravneni() {
        return em.createNativeQuery("SELECT * FROM seznamopravneni", Seznamopravneni.class).getResultList();
    }

    @Override
    public void deleteOpravneni(Seznamopravneni s) {
        em.remove(em.find(Seznamopravneni.class, s.getShortname()));
    }

    @Override
    public Seznamopravneni saveSeznamOpravneni(Seznamopravneni s) {
        try{
            em.merge(s);
        } catch (Exception e){
            em.persist(s);
        }
        return s;
    }

    @Override
    public List<Seznamopravneni> AutoCompleteHelper(String s) {
        s = s + "%";
        return em.createNativeQuery("SELECT * FROM strojnicaky.seznamopravneni where shortname like ?ss", Seznamopravneni.class)
                .setParameter("ss", s)
                .getResultList();
        
    }

    @Override
    public void addOpravneni(Opravneni s) {
        try{
            em.persist(s);
        } catch (Exception e){
            System.err.println("Chybka se vloudila");
        }
        
    }

    @Override
    public Seznamopravneni getSenzmaopravneniByID(String s) {
        return em.find(Seznamopravneni.class, s);
    }

    @Override
    public void saveOpravneni(Opravneni o) {
        Opravneni tmp = em.find(Opravneni.class, o.getId());
        tmp.setDatumexpirace(o.getDatumexpirace());
        try{
            em.merge(tmp);
            em.flush();
        }catch (Exception e){
            System.out.println("Nasrat");
        }
        
    }
}
