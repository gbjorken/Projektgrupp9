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
    
    /*public Boolean loginAsApplicant(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            if(getRoleTypeName(listPersons.get(0).getRoleType()).equals("applicant"))
                return true;
        }
        return false;
    }
    
    public Boolean loginAsRecruiter(String username, String password)
    {
        List<PersonDTO> listPersons = checkUsernameAndPassword(username, password);
        if(listPersons.size() > 0)
        {
            if(getRoleTypeName(listPersons.get(0).getRoleType()).equals("recruiter"))
                return true;
        }
        return false;
    }*/
    
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