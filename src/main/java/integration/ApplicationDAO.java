package integration;

import DTO.CompetenceDTO;
import DTO.JobDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ApplicationDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    public List<CompetenceDTO> getAllCompetences(String lang)
    {
        Query query = em.createQuery("SELECT cl FROM Competence_Localized AS cl "
                + "WHERE cl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1)");
        query.setParameter(1, lang);
        return query.getResultList();
    }       
}        