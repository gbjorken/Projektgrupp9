package controller;

import DTO.ApplicationDTO;
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

@Stateless
public class Controller 
{
    @EJB
    private LoginAndRegisterDAO loginAndRegisterDAO;
    @EJB
    private JobDAO jobDAO;
    @EJB
    private ApplicationDAO applicationDAO;
    
    /*public Boolean loginAsApplicant(String username, String password)
    {
        return loginAndRegisterDAO.loginAsApplicant(username, password);
    }
    
    public Boolean loginAsRecruiter(String username, String password)
    {
        return loginAndRegisterDAO.loginAsRecruiter(username, password);
    }*/
    
    public Boolean register(String name, String surname, String ssn, String email, String username, String password)
    {
        return loginAndRegisterDAO.register(name, surname, ssn, email, username, password);
    }
    
    public List<JobDTO> getJobs(String lang)
    {
        return jobDAO.getJobs(lang);
    }
    
    public String getJobNameById(Integer id, String lang)
    {
        return jobDAO.getJobNameById(id, lang);
    }
    
    public String getStatusNameById(Integer id, String lang)
    {
        return applicationDAO.getStatusNameById(id, lang);
    }
    
    public List<CompetenceDTO> getAllCompetences(String lang)
    {
        return applicationDAO.getAllCompetences(lang);
    }
    
    public Boolean createApplication(ArrayList<String> competenceList, 
                                     ArrayList<String> yearsList,
                                     ArrayList<String> fromDateList,
                                     ArrayList<String> toDateList,
                                     String username, Integer jobId)
    {
        return applicationDAO.createApplication(competenceList, yearsList, 
                                   fromDateList, toDateList, username, jobId);
    }
    
    public List<ApplicationDTO> getApplicationsByUsername(String username)
    {
        return applicationDAO.getApplicationsByUsername(username);
    }
    
    public List<CompetenceProfileDTO> getCompetenceProfileByApplicationId(Integer id)
    {
        return applicationDAO.getCompetenceProfileByApplicationId(id);
    }
    
    public String getCompetenceNameById(Integer id, String lang)
    {
        return applicationDAO.getCompetenceNameById(id, lang);
    }
}
