package integration;

import DTO.PersonDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Person;
import model.RoleType;

/**
 * Klassen behandlar inloggning och registreringar för användarna mot
 * databasen.
 */
@Stateless
public class LoginAndRegisterDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    /**
     * Är av typen boolean. Om parametrarna matchar med databasen loggas 
     * användaren in som sökande.
     * @param username Användarnamn
     * @param password Lösenord
     * @return TrueOrFalse
     */
    public Boolean loginAsApplicant(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            if(getRoleTypeName(listPersons.get(0).getRoleType()).equals("applicant"))
                return true;
        }
        return false;
    }
    
    /**
     * Är av typen boolean. Om parametrarna matchar med databasen loggas 
     * användaren in som rekryterare.
     * @param username Användarnamn
     * @param password Lösenord
     * @return TrueOrFalse
     */
    public Boolean loginAsRecruiter(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            if(getRoleTypeName(listPersons.get(0).getRoleType()).equals("recruiter"))
                return true;
        }
        return false;
    }
    
    /**
     * Är av typen boolean. Registrerar användare i databasen och returnerar
     * True or false baserat på om det gått bra eller ej.
     * @param name Förnamn
     * @param surname Efternamn
     * @param ssn Personnummer
     * @param email E-post
     * @param username Användarnamn
     * @param password Lösenord
     * @return TrueOrFalse
     */
    public Boolean register(String name, String surname, String ssn, 
                            String email, String username, String password)
    {
        Query query = em.createNativeQuery("SELECT * FROM Person WHERE username = ?");
        query.setParameter(1, username);
        if(query.getResultList().size() > 0)
            return false;
        
        query = em.createNativeQuery("SELECT id FROM RoleType WHERE name = 'applicant'");
        Integer roleTypeId = (Integer)query.getSingleResult();
        
        Person person = new Person(name, surname, ssn, email, 
                        username, password, em.find(RoleType.class, roleTypeId));
        em.persist(person);
        
        return true;
    }
    
    /**
     * Checkar om användarnamn och lösenord av en person finns.
     * @param username Användarnamn
     * @param password Lösenord
     * @return Lista av resultat
     */
    private List<PersonDTO> checkUsernameAndPassword(String username, String password)
    {
        Query query = em.createQuery("SELECT p FROM Person AS p "
                + "WHERE p.username = ?1 AND p.password = ?2", PersonDTO.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        return query.getResultList();
    }
    
    /**
     * Returnerar rolltyp genom en förfrågan till databasen med hjälp av
     * en ID kod.
     * @param roleTypeId Rolltypens ID
     * @return Rolltypens namn
     */
    private String getRoleTypeName(int roleTypeId)
    {
        Query query = em.createNativeQuery("SELECT name FROM RoleType "
            + "WHERE id = ?");
        query.setParameter(1, roleTypeId);
        String roleTypeName = (String)query.getSingleResult();
        
        return roleTypeName;
    }
}        
