package model;

import DTO.PersonDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Person implements PersonDTO, Serializable 
{
    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;
    @Basic(optional = false)
    @Column(name = "surname", nullable = false)
    private String surname;
    @Basic(optional = false)
    @Column(name = "ssn", length = 13, nullable = false)
    private String ssn;
    @Basic(optional = false)
    @Column(name = "email", nullable = false)
    private String email;
    
    @Id
    @Basic(optional = false)
    @Column(name="username", nullable = false)
    private String username;
    
    @Basic(optional = false)
    @Column(name="password", nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Application> applications;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private UserRole userRole;
    
    public Person(){
    }
    
    public Person(String name, String surname, 
            String ssn, String email, String username, String password)
    {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setSurname(String surname){
        this.surname = surname; 
    }
    
    public String getSSN(){
        return ssn;
    }
    
    public void setSSN(String ssn){
        this.ssn = ssn;
    }

    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Integer getUserRole(){
        return userRole.getId();
    }
    
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return this.username.equals(other.username);
    }

    @Override
    public String toString() {
        return "model.Person[ username=" + username + " ]";
    }
}