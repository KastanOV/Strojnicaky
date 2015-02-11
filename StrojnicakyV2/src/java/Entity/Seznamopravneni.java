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
import javax.persistence.ManyToMany;
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
@Table(name = "seznamopravneni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seznamopravneni.findAll", query = "SELECT s FROM Seznamopravneni s"),
    @NamedQuery(name = "Seznamopravneni.findByShortname", query = "SELECT s FROM Seznamopravneni s WHERE s.shortname = :shortname"),
    @NamedQuery(name = "Seznamopravneni.findByName", query = "SELECT s FROM Seznamopravneni s WHERE s.name = :name")})
public class Seznamopravneni implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "shortname")
    private String shortname;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "seznamopravneniCollection")
    private Collection<Hotovostfunkce> hotovostfunkceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seznamopravneniShortname")
    private Collection<Opravneni> opravneniCollection;

    public Seznamopravneni() {
    }

    public Seznamopravneni(String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Hotovostfunkce> getHotovostfunkceCollection() {
        return hotovostfunkceCollection;
    }

    public void setHotovostfunkceCollection(Collection<Hotovostfunkce> hotovostfunkceCollection) {
        this.hotovostfunkceCollection = hotovostfunkceCollection;
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
        hash += (shortname != null ? shortname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seznamopravneni)) {
            return false;
        }
        Seznamopravneni other = (Seznamopravneni) object;
        if ((this.shortname == null && other.shortname != null) || (this.shortname != null && !this.shortname.equals(other.shortname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Seznamopravneni[ shortname=" + shortname + " ]";
    }
    
}
