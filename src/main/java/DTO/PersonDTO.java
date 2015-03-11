package DTO;

/**
 * Gränssnitt som är DTO för person.
 */
public interface PersonDTO 
{    
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
     * Hämtar personens användarroll
     * @return Personens roll
     */
    public Integer getUserRole();
}
