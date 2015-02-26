package DTO;

/**
 * Gränssnitt som är DTO för person.
 */
public interface PersonDTO 
{
    /**
     * Hämtar id:t för personen.
     * @return Id:t för personen
     */
    public Integer getId();
    
    /**
     * Hämtar personens förnamn.
     * @return Personens förnamn
     */
    public String getName();
    
    /**
     * Hämtar personens efternamn.
     * @return Personens efternamn
     */
    public String getSurname();
    
    /**
     * Hämtar personens personnummer.
     * @return Personens personnummer
     */
    public String getSSN();
    
    /**
     * Hämtar personens email.
     * @return Personens email
     */
    public String getEmail();
    
    /**
     * Hämtar personens användarnamn.
     * @return Personens användarnamn
     */
    public String getUsername();
    
    /**
     * Hämtar personens lösenord
     * @return Personens lösenord
     */
    public String getPassword();
    
    /**
     * Hämtar id:t för personens rolltyp.
     * @return Id:t för personens rolltyp
     */
    public Integer getRoleType();
}
