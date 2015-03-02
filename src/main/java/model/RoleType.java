package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Klassen rolltyp bestämmer om en person är rekryterare eller ansökande.
 */
@Entity
public class RoleType implements Serializable 
{        
    @Id
    @Basic(optional = false)
    @Column(name="name", nullable = false)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roletype")
    private Collection<UserRole> userRoles;
    
    /**
     * Default konstruktor.
    */
    public RoleType() {
    }
    
    /**
     * Skriver in namnet på typen av roll.
     * @param name Rollnamn
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Returnerar namnet på typen av roll.
     * @return Rollnamn
     */
    public String getName(){
        return name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RoleType)) {
            return false;
        }
        RoleType other = (RoleType) object;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "model.RoleType[ name=" + name + " ]";
    }
}