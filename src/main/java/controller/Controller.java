package controller;

import DTO.ApplicationDTO;
import DTO.AvailabilityDTO;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
import DTO.JobDTO;
import integration.ApplicationDAO;
import integration.JobDAO;
import integration.LoginAndRegisterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Controller som tar emot anrop från vyn och returnerar 
 * dem vidare till integrationslagret, samt returnerar tillbaka.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Controller {
    @EJB
    private LoginAndRegisterDAO loginAndRegisterDAO;
    @EJB
    private JobDAO jobDAO;
    @EJB
    private ApplicationDAO applicationDAO;

    /**
     * Anropar loginAndRegisterDAO för att testa logga in som ansökande.
     * @param username Användarnamn
     * @param password Lösenord
     * @return Boolean angående det gick utan problem eller inte
     */
    public Boolean loginAsApplicant(String username, String password)
    {
        return loginAndRegisterDAO.loginAsApplicant(username, password);
    }
    
    /**
     * Anropar loginAndRegisterDAO för att testa logga in som rekryterare.
     * @param username Användarnamn
     * @param password Lösenord
     * @return Boolean angående det gick utan problem eller inte
     */
    public Boolean loginAsRecruiter(String username, String password)
    {
        return loginAndRegisterDAO.loginAsRecruiter(username, password);
    }
    
    /**
     * Anropar loginAndRegisterDAO för att registrera ny sökande.
     * @param name Förnamn
     * @param surname Efternamn
     * @param ssn Personnummer
     * @param email E-post
     * @param username Användarnamn
     * @param password Lösenord
     * @return Boolean angående det gick utan problem eller inte
     */
    public Boolean register(String name, String surname, String ssn, String email, String username, String password)
    {
        return loginAndRegisterDAO.register(name, surname, ssn, email, username, password);
    }
    
    /**
     * Hämtar lista med tillgängliga jobb.
     * @param lang Språkkod
     * @return Lista med alla tillgängliga jobb
     */
    public List<JobDTO> getJobs(String lang)
    {
        return jobDAO.getJobs(lang);
    }
    
    /**
     * Hämtar namnet på ett jobb med ett angivet id.
     * @param id Jobbets id
     * @param lang Språkkod
     * @return Namnet på jobbet
     */
    public String getJobNameById(Integer id, String lang)
    {
        return jobDAO.getJobNameById(id, lang);
    }
    
    /**
     * Hämtar namnet på en status med ett angivet id.
     * @param id Statusens id
     * @param lang Språkkod
     * @return Namnet på statusen.
     */
    public String getStatusNameById(Integer id, String lang)
    {
        return applicationDAO.getStatusNameById(id, lang);
    }
    
    /**
     * Hämtar en lista med alla kompetenser.
     * @param lang Språkkod
     * @return En lista med alla kompetenser
     */
    public List<CompetenceDTO> getAllCompetences(String lang)
    {
        return applicationDAO.getAllCompetences(lang);
    }
    
    /**
     * Metod som anropar applicationDAO för att skapa en ny ansökan.
     * @param competenceList Lista över sökandens kompetens
     * @param yearsList Lista över år för kompetenserna
     * @param fromDateList Lista över startdatum
     * @param toDateList Lista över slutdatum
     * @param username Användarnamnet
     * @param jobId Jobbets id-nr
     * @return Boolean angående det gick utan problem eller inte
     */
    public Boolean createApplication(ArrayList<String> competenceList, 
                                     ArrayList<String> yearsList,
                                     ArrayList<String> fromDateList,
                                     ArrayList<String> toDateList,
                                     String username, Integer jobId)
    {
        return applicationDAO.createApplication(competenceList, yearsList, 
                                   fromDateList, toDateList, username, jobId);
    }
    
    /**
     * Hämtar genomförda ansökningar för en sökande.
     * @param username Användarnamn
     * @return Lista med genomförda ansökningar för en sökande
     */
    public List<ApplicationDTO> getApplicationsByUsername(String username)
    {
        return applicationDAO.getApplicationsByUsername(username);
    }
    
    /**
     * Hämtar lista med kompetensprofiler för en ansökan
     * @param id Ansökans id-nr
     * @return Lista med kompetensprofiler
     */
    public List<CompetenceProfileDTO> getCompetenceProfileByApplicationId(Integer id)
    {
        return applicationDAO.getCompetenceProfileByApplicationId(id);
    }
    
    /**
     * Hämtar namnet för en kompetens via id.
     * @param id Id för en kompetens
     * @param lang Språkkod
     * @return Namnet på kompetensen
     */
    public String getCompetenceNameById(Integer id, String lang)
    {
        return applicationDAO.getCompetenceNameById(id, lang);
    }
    
    /**
     * Hämtar en lista med tillgänglighetsperioder för en specifik ansökan.
     * @param id Id:t för den specifika ansökan
     * @return Lista med tillgänglighetsperioder
     */
    public List<AvailabilityDTO> getAvailabilityByApplicationId(Integer id)
    {
        return applicationDAO.getAvailabilityByApplicationId(id);
    }
}
