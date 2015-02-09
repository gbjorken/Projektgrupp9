package Integration;

import DTO.PersonDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoginDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
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
    
    private List<PersonDTO> checkUsernameAndPassword(String username, String password)
    {
        Query query = em.createQuery("SELECT p FROM Person AS p "
                + "WHERE p.username = ?1 AND p.password = ?2", PersonDTO.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        return query.getResultList();
    }
    
    private String getRoleTypeName(int roleTypeId)
    {
        Query query = em.createQuery("SELECT rtl.roletypeName FROM RoleType_Localized AS rtl "
            + "WHERE rtl.roletype = (SELECT rt FROM RoleType AS rt WHERE rt.id = ?1) AND "
            + "rtl.locale = (SELECT l FROM Locale l WHERE l.lang_code = ?2)", String.class);
        query.setParameter(1, roleTypeId);
        query.setParameter(2, "en");
        String roleTypeName = (String)query.getSingleResult();
        
        return roleTypeName;
    }
}        