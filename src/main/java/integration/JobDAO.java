package integration;

import DTO.JobDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Klassen skickar förfrågningar till databasen om jobb.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JobDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    /**
     * Listar alla typer av jobb.
     * @param lang Språkkod
     * @return Lista av tillgängliga jobb
     */
    public List<JobDTO> getJobs(String lang)
    {
        Query query = em.createQuery("SELECT jl FROM Job_Localized AS jl "
                + "WHERE jl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1)");
        query.setParameter(1, lang);
        return query.getResultList();
    }
    
    /**
     * Letar fram ett jobb baserat på en ID kod.
     * @param id Jobb ID
     * @param lang Språkkod
     * @return Ett jobb filtrerat på ID
     */
    public String getJobNameById(Integer id, String lang)
    {
        Query query = em.createQuery("SELECT jl.jobName FROM Job_Localized AS jl "
                + "WHERE jl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1) "
                + "AND jl.job = (SELECT j FROM Job AS j WHERE j.id = ?2)");
        query.setParameter(1, lang);
        query.setParameter(2, id);
        return (String)query.getSingleResult();
    }
}        