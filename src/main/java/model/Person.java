package model;

import DTO.PersonDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Klassen person skapar personer med sina personliga attribut.
 */
@Entity
public class Person implements PersonDTO, Serializable {
    @Id
    @SequenceGenerator(name = "personIdSeq", sequenceName = "PERSON_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personIdSeq")
    @Column(name = "id")
    private Integer id;
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
    @Basic(optional = false)
    @Column(name="username", unique = true, nullable = false)
    private String username;
    @Basic(optional = false)
    @Column(name="password", nullable = false)
    private String password;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roletype", referencedColumnName = "id", nullable = false)
    private RoleType roletype;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Application> applications;
    
    /**
     * Metoden som kallas vid skapande av en ny person.
     * @param name Förnamn
     * @param surname Efternamn
     * @param ssn Personnummer
     * @param email E-post
     * @param username Användarnamn
     * @param password Lösenord
     * @param roletype Typ av roll. Rekryterare eller ansökande
     */
    public Person(String name, String surname, 
            String ssn, String email, String username, String password,
            RoleType roletype)
    {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roletype = roletype;
    }
    
    /**
     * Returnerar ett ID nummer från kolumnen ID.
     * @return ID kod
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returnerar ett förnamn på en person.
     * @return Förnamn
     */
    public String getName(){
        return name;
    }
    
    /**
     * Skriver in ett förnamn på en person.
     * @param name Förnamn
     */
    public void setName(String name){
        this.name = name;
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
     * Skriver in en persons användarnamn.
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
    public Integer getRoleType(){
        return roletype.getId();
    }
    
    /**
     * Skriver in en persons rolltyp - rekryterare / ansökande.
     * @param roletype Rolltyp 
     */
    public void setRoleType(RoleType roletype){
        this.roletype = roletype;
    }
    
    /**
     * Det är hash överallt.
     * @return Hashkoden
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check om rätt person har hittats eller ej.
     * @param object Integer av språk som inparameter.
     * @return Boolean om true eller false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return this.id.equals(other.id);
    }

    /**
     * Konverterar en persons ID till en sträng i en mening.
     * @return ID som sträng
     */
    @Override
    public String toString() {
        return "model.Person[ id=" + id + " ]";
    }
}
