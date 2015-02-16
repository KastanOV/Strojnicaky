/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface LoginSBLocal {
    public Users tryLogin(Users u);
    public Users initAplication(Users u);
}
