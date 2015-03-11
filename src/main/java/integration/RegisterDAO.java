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
import model.UserRole;

/**
 * Klassen behandlar inloggning och registreringar för användarna mot
 * databasen.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LoginAndRegisterDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    /**
     * Om parametrarna matchar med databasen loggas användaren in som sökande.
     * @param username Användarnamn
     * @param password Lösenord
     * @return TrueOrFalse
     */
    public Boolean loginAsApplicant(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            Query query = em.createNativeQuery("SELECT roletype FROM UserRole WHERE person = ?1");
            query.setParameter(1, username);
            String role = (String) query.getSingleResult();
            if(role.equals("applicant"))
                return true;
        }
        return false;
    }
    
    /**
     * Om parametrarna matchar med databasen loggas användaren in som rekryterare.
     * @param username Användarnamn
     * @param password Lösenord
     * @return TrueOrFalse
     */
    public Boolean loginAsRecruiter(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            Query query = em.createNativeQuery("SELECT roletype FROM UserRole WHERE person = ?1");
            query.setParameter(1, username);
            String role = (String) query.getSingleResult();
            if(role.equals("recruiter"))
                return true;
        }
        return false;
    }
    
    /**
     * Registrerar användare i databasen och returnerar
     * true eller false baserat på om det gått bra eller ej.
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
        Query query = em.createQuery("SELECT p FROM Person AS p WHERE p.username = ?1");
        query.setParameter(1, username);
        if(query.getResultList().size() > 0)
            return false;
        
        query = em.createQuery("SELECT rt FROM RoleType AS rt WHERE rt.name = 'applicant'");
        RoleType roletype = (RoleType)query.getSingleResult();
        
        Person person = new Person(name, surname, ssn, email, username, password);
        UserRole userRole = new UserRole(person, roletype);
        em.persist(userRole);
        
        person.setUserRole(em.find(UserRole.class, userRole.getId()));
        em.persist(person);
        
        return true;
    }
    
    private List<PersonDTO> checkUsernameAndPassword(String username, String password)
    {
        Query query = em.createQuery("SELECT p FROM Person AS p "
                + "WHERE p.username = ?1 AND p.password = ?2", PersonDTO.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        return query.getResultList();
    }
}
package integration;

import DTO.PersonDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Person;
import model.RoleType;
import model.UserRole;

@Stateless
public class LoginAndRegisterDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;

    public Boolean register(String name, String surname, String ssn, 
                            String email, String username, String password) {
        Query query = em.createQuery("SELECT p FROM Person AS p WHERE p.username = ?1");
        query.setParameter(1, username);
        if(query.getResultList().size() > 0)
            return false;
        
        query = em.createQuery("SELECT rt FROM RoleType AS rt WHERE rt.name = 'applicant'");
        RoleType roletype = (RoleType)query.getSingleResult();
        
        Person person = new Person(name, surname, ssn, email, username, encrypt(password));
        UserRole userRole = new UserRole(person, roletype);
        em.persist(userRole);
        
        person.setUserRole(em.find(UserRole.class, userRole.getId()));
        em.persist(person);
        
        return true;
    }

    private String encrypt(String password) {
        String encrypted="";
        try {
            java.security.MessageDigest digestion = java.security.MessageDigest.getInstance("SHA-256");
            digestion.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = digestion.digest();
            java.math.BigInteger bigInt = new java.math.BigInteger(1, digest);
            encrypted = bigInt.toString(16);
System.out.println("c: "+encrypted);
        } catch (java.security.NoSuchAlgorithmException | java.io.UnsupportedEncodingException ex) {
            System.out.println("e: "+ex.getMessage());
        }
        return encrypted;
    }

    /*private List<PersonDTO> checkUsernameAndPassword(String username, String password)
    {
        Query query = em.createQuery("SELECT p FROM Person AS p "
                + "WHERE p.username = ?1 AND p.password = ?2", PersonDTO.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        return query.getResultList();
    }
    
    private String getRoleTypeName(int roleTypeId)
    {
        Query query = em.createNativeQuery("SELECT name FROM RoleType "
            + "WHERE id = ?");
        query.setParameter(1, roleTypeId);
        String roleTypeName = (String)query.getSingleResult();
        
        return roleTypeName;
    }*/
}        
