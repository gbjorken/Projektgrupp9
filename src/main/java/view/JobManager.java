package view;

import DTO.JobDTO;
import controller.Controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Klassen behandlar allt som har med jobb att göra via vyn.
 */
@Named("jobManager")
@SessionScoped
public class JobManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private List<JobDTO> jobs;
    private String currentJob;
    private Integer currentJobId;
    
    /**
     * Returnerar en lista med jobb typer.
     * @return Jobblista
     */
    public List getJobs(){
        jobs = controller.getJobs(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        
        List<String> list = new ArrayList();
        for (JobDTO job : jobs) {
            list.add(job.getJobTypeName());
        }
        
        return list;
    }
    
    /**
     * Returnerar nuvarande jobb.
     * @return Nuvarande jobb
     */
    public String getCurrentJob()
    {
        currentJob = controller.getJobNameById(currentJobId, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        return currentJob;
    }
    
    /**
     * Skriver in nuvarande jobb.
     * @param currentJob Nuvarande jobb
     */
    public void setCurrentJobId(String currentJob)
    {
        for (JobDTO job : jobs) 
            if (currentJob.equals(job.getJobTypeName())) 
                currentJobId = job.getJob();
    }
}