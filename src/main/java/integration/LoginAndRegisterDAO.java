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