package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * Klass för att skapa och lagra användarroller.
 */
@Entity
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "userRoleIdSeq", sequenceName = "USERROLE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "person", referencedColumnName = "username", nullable = false)   
    private Person person;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roletype", referencedColumnName = "name", nullable = false)
    private RoleType roletype;  
    
    /**
     * Default konstruktor
     */
    public UserRole(){
    }
    
    /**
     * Konstruktor för UserRole
     * @param person Den person denna UserRole berör
     * @param roletype Den rolltyp som personen ska ha
     */
    public UserRole(Person person, RoleType roletype){
        this.person = person;
        this.roletype = roletype;
    }
    
    /**
     * Sätter id:t för användarrollen.
     * @return Id:t för användarrollen
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Hämtar användarnamnet för personen.
     * @return Användarnamnet för den person som denna UserRole berör
     */
    public String getPerson(){
        return person.getUsername();
    }
    
    /**
     * Anger person för denna UserRole
     * @param person Person
     */
    public void setPerson(Person person){
        this.person = person;
    }
    
    /**
     * Hämtar rolltypen för denna UserRole.
     * @return Rolltypen som denna UserRole berör
     */
    public String getRoleType(){
        return roletype.getName();
    }
    
    /**
     * Sätter rolltypen för denna UserRole.
     * @param roletype Rolltyp för denna UserRole
     */
    public void setRoleType(RoleType roletype){
        this.roletype = roletype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.UserRole[ id=" + id + " ]";
    }   
}