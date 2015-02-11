/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "hotovost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotovost.findAll", query = "SELECT h FROM Hotovost h"),
    @NamedQuery(name = "Hotovost.findByIdhotovost", query = "SELECT h FROM Hotovost h WHERE h.idhotovost = :idhotovost"),
    @NamedQuery(name = "Hotovost.findByStart", query = "SELECT h FROM Hotovost h WHERE h.start = :start"),
    @NamedQuery(name = "Hotovost.findByEnd", query = "SELECT h FROM Hotovost h WHERE h.end = :end")})
public class Hotovost implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhotovost")
    private Integer idhotovost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start")
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end")
    @Temporal(TemporalType.DATE)
    private Date end;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotovostIdhotovost")
    private Collection<Clenovehotovosti> clenovehotovostiCollection;

    public Hotovost() {
    }

    public Hotovost(Integer idhotovost) {
        this.idhotovost = idhotovost;
    }

    public Hotovost(Integer idhotovost, Date start, Date end) {
        this.idhotovost = idhotovost;
        this.start = start;
        this.end = end;
    }

    public Integer getIdhotovost() {
        return idhotovost;
    }

    public void setIdhotovost(Integer idhotovost) {
        this.idhotovost = idhotovost;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @XmlTransient
    public Collection<Clenovehotovosti> getClenovehotovostiCollection() {
        return clenovehotovostiCollection;
    }

    public void setClenovehotovostiCollection(Collection<Clenovehotovosti> clenovehotovostiCollection) {
        this.clenovehotovostiCollection = clenovehotovostiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhotovost != null ? idhotovost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotovost)) {
            return false;
        }
        Hotovost other = (Hotovost) object;
        if ((this.idhotovost == null && other.idhotovost != null) || (this.idhotovost != null && !this.idhotovost.equals(other.idhotovost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Hotovost[ idhotovost=" + idhotovost + " ]";
    }
    
}
