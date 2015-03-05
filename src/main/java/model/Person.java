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

/**
 * Klassen person skapar personer med sina personliga attribut.
 */
@Entity
public class Person implements PersonDTO, Serializable 
{
    @Basic(optional = false)
    @Column(name = "fname", nullable = false)
    private String fname;
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
    
    /**
     * Default konstruktor.
     */
    public Person(){
    }
    
    /**
     * Metoden som kallas vid skapande av en ny person.
     * @param fname Förnamn
     * @param surname Efternamn
     * @param ssn Personnummer
     * @param email E-post
     * @param username Användarnamn
     * @param password Lösenord
     */
    public Person(String fname, String surname, 
            String ssn, String email, String username, String password)
    {
        this.fname = fname;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Returnerar ett förnamn på en person.
     * @return Förnamn
     */
    public String getName(){
        return fname;
    }
    
    /**
     * Skriver in ett förnamn på en person.
     * @param name Förnamn
     */
    public void setName(String name){
        this.fname = name;
    }
    
     /**
     * Returnerar ett efternamn på en person.
     * @return Efternamn
     */
    public String getSurname(){
        return surname;
    }
    
    /**
     * Skriver in ett efternamn på en person.
     * @param surname Efternamn
     */
    public void setSurname(String surname){
        this.surname = surname; 
    }
    
    /**
     * Returnerar en persons personnummer.
     * @return Personnummer
     */
    public String getSSN(){
        return ssn;
    }
    
    /**
     * Skriver in en persons personnummer.
     * @param ssn Personnummer
     */
    public void setSSN(String ssn){
        this.ssn = ssn;
    }
    
    /**
     * Returnerar en persons E-post adress.
     * @return E-post adress
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Skriver in en persons E-post adress.
     * @param email E-post adress
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Returnerar en persons användarnamn.
     * @return Användarnamn
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * Anger en persons användarnamn.
     * @param username Användarnamn
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Returnerar en persons lösenord.
     * @return Lösenord
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * Skriver in en persons lösenord.
     * @param password Lösenord
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Returnerar en persons rolltyp - rekryterare / ansökande.
     * @return Rolltyp
     */
    public Integer getUserRole(){
        return userRole.getId();
    }
    
     /**
     * Skriver in en persons rolltyp - rekryterare / ansökande.
     * @param userRole Rolltyp 
     */
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