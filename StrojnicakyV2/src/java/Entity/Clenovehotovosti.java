/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "clenovehotovosti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clenovehotovosti.findAll", query = "SELECT c FROM Clenovehotovosti c"),
    @NamedQuery(name = "Clenovehotovosti.findById", query = "SELECT c FROM Clenovehotovosti c WHERE c.id = :id")})
public class Clenovehotovosti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "hotovost_idhotovost", referencedColumnName = "idhotovost")
    @ManyToOne(optional = false)
    private Hotovost hotovostIdhotovost;
    @JoinColumn(name = "hotovostfunkce_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hotovostfunkce hotovostfunkceId;
    @JoinColumn(name = "users_login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public Clenovehotovosti() {
    }

    public Clenovehotovosti(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotovost getHotovostIdhotovost() {
        return hotovostIdhotovost;
    }

    public void setHotovostIdhotovost(Hotovost hotovostIdhotovost) {
        this.hotovostIdhotovost = hotovostIdhotovost;
    }

    public Hotovostfunkce getHotovostfunkceId() {
        return hotovostfunkceId;
    }

    public void setHotovostfunkceId(Hotovostfunkce hotovostfunkceId) {
        this.hotovostfunkceId = hotovostfunkceId;
    }

    public Users getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(Users usersLogin) {
        this.usersLogin = usersLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clenovehotovosti)) {
            return false;
        }
        Clenovehotovosti other = (Clenovehotovosti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Clenovehotovosti[ id=" + id + " ]";
    }
    
}
