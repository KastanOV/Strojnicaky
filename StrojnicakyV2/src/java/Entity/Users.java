/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
    @NamedQuery(name = "Users.findByFname", query = "SELECT u FROM Users u WHERE u.fname = :fname"),
    @NamedQuery(name = "Users.findByLname", query = "SELECT u FROM Users u WHERE u.lname = :lname"),
    @NamedQuery(name = "Users.findByOec", query = "SELECT u FROM Users u WHERE u.oec = :oec"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByPas", query = "SELECT u FROM Users u WHERE u.pas = :pas"),
    @NamedQuery(name = "Users.findByRota", query = "SELECT u FROM Users u WHERE u.rota = :rota"),
    @NamedQuery(name = "Users.findByCeta", query = "SELECT u FROM Users u WHERE u.ceta = :ceta")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "oec")
    private String oec;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "role")
    private String role;
    @Size(max = 45)
    @Column(name = "pas")
    private String pas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rota")
    private int rota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ceta")
    private int ceta;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usersLogin")
    private Collection<Clenovehotovosti> clenovehotovostiCollection;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usersLogin")
    private Collection<Opravneni> opravneniCollection;

    public Users() {
    }

    public Users(String login) {
        this.login = login;
    }

    public Users(String login, String fname, String lname, String oec, String phone, String role, int rota, int ceta) {
        this.login = login;
        this.fname = fname;
        this.lname = lname;
        this.oec = oec;
        this.phone = phone;
        this.role = role;
        this.rota = rota;
        this.ceta = ceta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getOec() {
        return oec;
    }

    public void setOec(String oec) {
        this.oec = oec;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!password.equals(this.password)){
            this.password = HashPassword.md5Hash(password);
            return;
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public int getRota() {
        return rota;
    }

    public void setRota(int rota) {
        this.rota = rota;
    }

    public int getCeta() {
        return ceta;
    }

    public void setCeta(int ceta) {
        this.ceta = ceta;
    }

    @XmlTransient
    public Collection<Clenovehotovosti> getClenovehotovostiCollection() {
        return clenovehotovostiCollection;
    }

    public void setClenovehotovostiCollection(Collection<Clenovehotovosti> clenovehotovostiCollection) {
        this.clenovehotovostiCollection = clenovehotovostiCollection;
    }

    @XmlTransient
    public Collection<Opravneni> getOpravneniCollection() {
        return opravneniCollection;
    }

    public void setOpravneniCollection(Collection<Opravneni> opravneniCollection) {
        this.opravneniCollection = opravneniCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ login=" + login + " ]";
    }
    
}
