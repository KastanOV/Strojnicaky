/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "opravneni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opravneni.findAll", query = "SELECT o FROM Opravneni o"),
    @NamedQuery(name = "Opravneni.findById", query = "SELECT o FROM Opravneni o WHERE o.id = :id"),
    @NamedQuery(name = "Opravneni.findByDatumexpirace", query = "SELECT o FROM Opravneni o WHERE o.datumexpirace = :datumexpirace")})
public class Opravneni implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumexpirace")
    @Temporal(TemporalType.DATE)
    private Date datumexpirace;
    @JoinColumn(name = "seznamopravneni_shortname", referencedColumnName = "shortname")
    @ManyToOne(optional = false)
    private Seznamopravneni seznamopravneniShortname;
    @JoinColumn(name = "users_login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public Opravneni() {
    }

    public Opravneni(Integer id) {
        this.id = id;
    }

    public Opravneni(Integer id, Date datumexpirace) {
        this.id = id;
        this.datumexpirace = datumexpirace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatumexpirace() {
        return datumexpirace;
    }

    public void setDatumexpirace(Date datumexpirace) {
        this.datumexpirace = datumexpirace;
    }

    public Seznamopravneni getSeznamopravneniShortname() {
        return seznamopravneniShortname;
    }

    public void setSeznamopravneniShortname(Seznamopravneni seznamopravneniShortname) {
        this.seznamopravneniShortname = seznamopravneniShortname;
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
        if (!(object instanceof Opravneni)) {
            return false;
        }
        Opravneni other = (Opravneni) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Opravneni[ id=" + id + " ]";
    }
    
}
