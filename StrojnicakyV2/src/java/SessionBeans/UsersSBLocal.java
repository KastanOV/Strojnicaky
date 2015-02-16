/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface UsersSBLocal {
    public Users getUser(String value);
    public List<Users> getUsers(Users u);
    
    public Users saveUser(Users u);
}
