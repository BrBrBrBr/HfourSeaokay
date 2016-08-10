/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hfourseaokay;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author brinlee
 */
@Entity
@Table(name = "DRUGDATA", catalog = "", schema = "BRINLEE")
@NamedQueries({
    @NamedQuery(name = "Drugdata.findAll", query = "SELECT d FROM Drugdata d"),
    @NamedQuery(name = "Drugdata.findByName", query = "SELECT d FROM Drugdata d WHERE d.name = :name"),
    @NamedQuery(name = "Drugdata.findBySchedule", query = "SELECT d FROM Drugdata d WHERE d.schedule = :schedule"),
    @NamedQuery(name = "Drugdata.findByPrice", query = "SELECT d FROM Drugdata d WHERE d.price = :price"),
    @NamedQuery(name = "Drugdata.findByQuantity", query = "SELECT d FROM Drugdata d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "Drugdata.findByDaysTillExpiry", query = "SELECT d FROM Drugdata d WHERE d.daysTillExpiry = :daysTillExpiry")})
public class Drugdata implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Schedule")
    private Integer schedule;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Double price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Days_Till_Expiry")
    private Integer daysTillExpiry;

    public Drugdata() {
    }

    public Drugdata(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        Integer oldSchedule = this.schedule;
        this.schedule = schedule;
        changeSupport.firePropertyChange("schedule", oldSchedule, schedule);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        Double oldPrice = this.price;
        this.price = price;
        changeSupport.firePropertyChange("price", oldPrice, price);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        Integer oldQuantity = this.quantity;
        this.quantity = quantity;
        changeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public Integer getDaysTillExpiry() {
        return daysTillExpiry;
    }

    public void setDaysTillExpiry(Integer daysTillExpiry) {
        Integer oldDaysTillExpiry = this.daysTillExpiry;
        this.daysTillExpiry = daysTillExpiry;
        changeSupport.firePropertyChange("daysTillExpiry", oldDaysTillExpiry, daysTillExpiry);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drugdata)) {
            return false;
        }
        Drugdata other = (Drugdata) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hfourseaokay.Drugdata[ name=" + name + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
