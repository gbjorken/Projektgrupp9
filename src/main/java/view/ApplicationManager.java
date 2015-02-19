package view;

import DTO.ApplicationDTO;
import DTO.CompetenceDTO;
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
import view.validators.ValidYear;

@Named("applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private List<CompetenceDTO> compList;
    private ArrayList<String> competenceList = new ArrayList<>();
    private ArrayList<String> yearsList = new ArrayList<>();
    private ArrayList<String> competenceAndYearList;
    private ArrayList<String> fromDateList = new ArrayList<>();
    private ArrayList<String> toDateList = new ArrayList<>();
    private ArrayList<String> startDateAndEndDateList;
    private List<ApplicationDTO> applicationList;
    private ApplicationDTO specificApplication;
    
    private String competence;
    @ValidYear
    private String years;
    private Date startDate;
    private Date endDate;
    private Boolean showDateMessage;
    private Boolean confirmSuccess;
    
    public String getCompetence(){
        return competence;
    }
    
    public void setCompetence(String competence){
        this.competence = competence;
    }
    
    public String getYears(){
        return years;
    }
    
    public void setYears(String years){
        this.years = years;
    }
    
    private Competence[] comList;
    public Competence[] getCompetenceValue() 
    {
        compList = controller.
                getAllCompetences(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        
        comList = new Competence[compList.size()];
        String comName, comId;
        Boolean skip = false;
        ArrayList<Competence> alComp = new ArrayList<>();
        for (CompetenceDTO compList1 : compList) 
        {
            comId = compList1.getCompetence().toString();
            for (String competenceList1 : competenceList) {
                if (comId.equals(competenceList1)) {
                    skip = true;
                    break;
                }
            }
            if (!skip) {
                comName = compList1.getCompetenceName();
                alComp.add(new Competence(comName, comId));
            }
            skip = false;
        }
        comList = alComp.toArray(new Competence[alComp.size()]);
        return comList;
    }
    
    public Boolean getEnableButton(){
        return comList.length > 0;
    }
    
    public String addCompetence()
    {
        competenceList.add(competence);
        yearsList.add(years);
        competence = null;
        years = null;
        return "";
    }
    
    public ArrayList<String> getCompetenceAndYearList()
    {
        confirmSuccess = false;
        ArrayList<String> al = new ArrayList<>();
        competenceAndYearList = new ArrayList<>();
        
        String c;
        for(int i = 0; i < competenceList.size(); i++)
        {
            c = competenceList.get(i);
            for(int j = 0; j < compList.size(); j++)
            {
                if(c.equals(compList.get(j).getCompetence().toString()))
                {
                    al.add(compList.get(j).getCompetenceName());
                    break;
                }
            }
        }
        
        for(int i = 0; i < al.size(); i++)
        {
            competenceAndYearList.add(al.get(i) + " " + yearsList.get(i));
        }
        
        return competenceAndYearList;
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
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
        if(startDate.after(endDate))
        {
            showDateMessage = true;
            return "";
        }
        
        showDateMessage = false;    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        fromDateList.add(df.format(startDate));
        toDateList.add(df.format(endDate));
        startDate = null;
        endDate = null;
        return "";
    }
    
    public Boolean getShowDateMessage(){
        return showDateMessage;
    }
    
    public ArrayList<String> getStartDateAndEndDateList()
    {
        ArrayList<String> al = new ArrayList<>();
        startDateAndEndDateList = new ArrayList<>(); 
        
        for(int i = 0; i < fromDateList.size(); i++)
        {
            startDateAndEndDateList.add(fromDateList.get(i) + " --- " + toDateList.get(i));
        }
        
        return startDateAndEndDateList;
    }
    
    public String removeCurrentComp(String currentComp)
    {
        String[] arr;
        String c = "";
        String value = "";
        
        arr = currentComp.split(" ");
        int j = 0;
        String y = "";
        //Skapa kompetensens namn som en strÃ¤ng samt plocka ut Ã¥r
        while(true)
        {
            try 
            {
                Double.parseDouble(arr[j]);
                y = arr[j];
                break;
            } 
            catch (NumberFormatException nfe) 
            {
                c += arr[j] + " ";
            }
            j++;
        }
        
        //Plocka ut kompetensens id
        for (CompetenceDTO compList1 : compList) {
            if ((compList1.getCompetenceName() + " ").equals(c)) 
            {
                value = compList1.getCompetence().toString();
                break;
            }
        }
        
        //Tar fram på vilken position i competenceList 
        //som den specifika kompetensen finns
        int pos = 0;
        for(int i = 0; i < competenceList.size(); i++)
        {
            if(competenceList.get(i).equals(value))
            {
                pos = i;
                break;
            }
        }
        competenceList.remove(value);
        yearsList.remove(pos);
        
        return "";
    }
    
    public String removeCurrentPeriod(String currentPeriod)
    {
        String[] arr = currentPeriod.split(" ");
        for(int i = 0; i < fromDateList.size(); i++)
        {
            if(fromDateList.get(i).equals(arr[0]) && toDateList.get(i).equals(arr[2]))
            {
                fromDateList.remove(i);
                toDateList.remove(i);
                break;
            }
        }
        return "";
    }
    
    public String clearAll()
    {
        competenceList = new ArrayList<>();
        yearsList = new ArrayList<>();
        fromDateList = new ArrayList<>();
        toDateList = new ArrayList<>();
        confirmSuccess = false;
        return "";
    }
    
    public String confirmApplication()
    {
        String username = 
               FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        Integer jobId = Integer.parseInt(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobId"));
        controller.createApplication
          (competenceList, yearsList, fromDateList, toDateList, username, jobId);
        
        confirmSuccess = true;
        return "";
    }
    
    public Boolean getConfirmSuccess(){
        return confirmSuccess;
    }
    
    public List<ApplicationDTO> getApplicationList(String username)
    {
        return controller.getApplicationsByUsername(username);
    }
    
    public String getJobNameById(Integer id)
    {
        return controller.getJobNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
    }
    
    public String getStatusNameById(Integer id)
    {
        return controller.getStatusNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
    }
    
    public void setSpecificApplication(ApplicationDTO specificApplication){
        this.specificApplication = specificApplication;
    }
    
    public ApplicationDTO getSpecificApplication(){
        return specificApplication;
    }
}