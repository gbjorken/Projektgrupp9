package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class RoleType_Localized implements Serializable {
    @Id
    @SequenceGenerator(name = "roleLocalizedIdSeq", 
            sequenceName = "ROLELOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "roletypeName", nullable = false)
    private String roletypeName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roletype", referencedColumnName = "id", nullable = false)
    private RoleType roletype;

    public RoleType_Localized(){
    }
    
    public RoleType_Localized(String roletypeName, Locale locale,
                          RoleType roletype)
    {
        this.roletypeName = roletypeName;
        this.locale = locale;
        this.roletype = roletype;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getLocale(){
        return locale.getLangCode();
    }
    
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    public Integer getRoleType(){
        return roletype.getId();
    }
    
    public void setRoleType(RoleType roletype){
        this.roletype = roletype;
    }
    
    public String getRoleTypeName(){
        return roletypeName;
    }
    
    public void setRoleTypeName(String roletypeName){
        this.roletypeName = roletypeName;
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
        if (!(object instanceof RoleType_Localized)) {
            return false;
        }
        RoleType_Localized other = (RoleType_Localized) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Role_Localized[ id=" + id + " ]";
    }
}
