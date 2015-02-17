package view;

import DTO.CompetenceDTO;
import DTO.JobDTO;
import controller.Controller;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private ArrayList<String> competenceList = new ArrayList<>();
    private ArrayList<Double> yearsList = new ArrayList<>();
    private ArrayList<String> fromDateList = new ArrayList<>();
    private ArrayList<String> toDateList = new ArrayList<>();
    
    private String competence;
    private Double years;
    private Date startDate;
    private Date endDate;
    
    public String getCompetence(){
        return competence;
    }
    
    public void setCompetence(String competence){
        this.competence = competence;
    }
    
    public Double getYears(){
        return years;
    }
    
    public void setYears(Double years){
        this.years = years;
    }
    
    private Competence[] comList;
    
    public Competence[] getCompetenceValue() 
    {
        List<CompetenceDTO> list = 
                controller.getAllCompetences(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        
        comList = new Competence[list.size()];
        String comName, comId;
        for(int i = 0; i < list.size(); i++)
        {
            comName = list.get(i).getCompetenceName();
            comId = list.get(i).getCompetence().toString();
            comList[i] = new Competence(comName, comId);
        }
        return comList;
    }
    
    public String addCompetence()
    {
        competenceList.add(competence);
        yearsList.add(years);
        competence = null;
        years = null;
        return "";
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    public Date getStartDate() {
        return startDate;
    }
 
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String addDates()
    {
        fromDateList.add(startDate.toString());
        toDateList.add(endDate.toString());
        startDate = null;
        endDate = null;
        return "";
    }
}