package view;

import DTO.ApplicationDTO;
import DTO.AvailabilityDTO;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
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

/**
 * Klassen ApplicationManager tar hand om allt som rÃ¶r applikationer i vyn.
 */
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
    private ApplicationDTO specificApplication;
    
    private String competence;
    @ValidYear
    private String years;
    private Date startDate;
    private Date endDate;
    private Boolean showDateMessage;
    private Boolean confirmSuccess;
    private Boolean goToConfirm = false;
    private Boolean clickOnConfirm = false;
    private Boolean confirmFailed = false;
    
    /**
     * Returnerar en kompetens.
     * @return Kompetens
     */
    public String getCompetence(){
        return competence;
    }
    
    /**
     * Skriver in en kompetens.
     * @param competence Kompetens
     */
    public void setCompetence(String competence){
        this.competence = competence;
    }
    
    /**
     * Returnerar antal Ã¥r fÃ¶r en kompetens.
     * @return Antal Ã¥r fÃ¶r en kompetens
     */
    public String getYears(){
        return years;
    }
    
    /**
     * Skriver in antal Ã¥r fÃ¶r en kompetens.
     * @param years Antal Ã¥r fÃ¶r en kompetens
     */
    public void setYears(String years){
        this.years = years;
    }
    
    private Competence[] comList;
    
    /**
     * HÃ¤mtar alla mÃ¶jliga kompetenser och skapar en lista med dem.
     * Denna lista visas sedan i en dropbox i vyn fÃ¶r ny ansÃ¶kan.
     * @return En lista med olika kompetenser
     */
    public Competence[] getCompetenceValue() 
    {
        comList = null;
        try
        {
            compList = controller.
                getAllCompetences(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
            comList = new Competence[compList.size()];
            String comName, comId;
            Boolean skip = false;
            ArrayList<Competence> alComp = new ArrayList<>();
            for(CompetenceDTO compList1 : compList) {
                comId = compList1.getCompetence().toString();
                for(String competenceList1 : competenceList) {
                    if(comId.equals(competenceList1)) {
                        skip = true;
                        break;
                    }
                }
                if(!skip) {
                    comName = compList1.getCompetenceName();
                    alComp.add(new Competence(comName, comId));
                }
                skip = false;
            }
            comList = alComp.toArray(new Competence[alComp.size()]);
        }
        catch(Exception e)
        {}
        return comList;
    }
    
    /** 
     * Om nuvarande kompetenslista innehÃ¥ller kompetenser kommer "LÃ¤gg till"
     * knappen att vara aktiverad.
     * Om nuvarande kompetenslista blir tom kommer
     * "LÃ¤gg till" knappen att bli inaktiverad.
     * @return true om det finns icke valda kompetenser, annars false
     */
    public Boolean getEnableButton(){
        return comList.length > 0;
    }
    
    /**
     * LÃ¤gg till kompetens till den privata kompetenslistan.
     * @return JSF version 2.2 bug - Tom strÃ¤ng
     */
    public String addCompetence()
    {
        competenceList.add(competence);
        yearsList.add(years);
        competence = null;
        years = null;
        return "";
    }
    
    /**
     * AnvÃ¤nds fÃ¶r att visa en lista med den privata kompetensen i vyn.
     * @return en lista med den valda kompetensen 
     */
    public ArrayList<String> getCompetenceAndYearList()
    {
        clickOnConfirm = false;
        confirmSuccess = false;
        
        ArrayList<String> al = new ArrayList<>();
        competenceAndYearList = new ArrayList<>();
        
        String c;
        for(int i = 0; i < competenceList.size(); i++) {
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
        
        for(int i = 0; i < al.size(); i++) {
            competenceAndYearList.add(al.get(i) + " " + yearsList.get(i));
        }
        
        return competenceAndYearList;
    }

    /**
     * HÃ¤mtar en lista med kompetenser fÃ¶r en specifik ansÃ¶kan
     * @param id Id:t fÃ¶r den specifika ansÃ¶kan
     * @return Lista med kompetenser och Ã¥r fÃ¶r respektive
     */
    public List<CompetenceProfileDTO> getCompetenceAndYearList(Integer id)
    {
        List<CompetenceProfileDTO> l = null;
        try
        {
            l = controller.getCompetenceProfileByApplicationId(id);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * HÃ¤ndelselyssnare fÃ¶r nÃ¤r anvÃ¤ndaren vÃ¤ljer datum fÃ¶r tillgÃ¤nglighet.
     * @param event Event lyssnare
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    /**
     * Returnerar startdatumet fÃ¶r tillgÃ¤nglighet.
     * @return Startdatum
     */
    public Date getStartDate() {
        return startDate;
    }
 
    /**
     * Skriver in startdatumet fÃ¶r tillgÃ¤nglighet.
     * @param startDate Startdatum
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Returnerar slutdatumet fÃ¶r tillgÃ¤nglighet.
     * @return Slutdatum
     */
    public Date getEndDate() {
        return endDate;
    }
 
    /**
     * * Returnerar slutdatumet fÃ¶r tillgÃ¤nglighet.
     * @param endDate Slutdatum
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * LÃ¤gg till datum i tillgÃ¤nglighetslistan.
     * @return JSF version 2.2 bug - Tom strÃ¤ng
     */
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
    
    /**
     * Anger om ett felmeddellande ska visas fÃ¶r start- och slutdatum
     * @return true om ett fel har uppstÃ¥tt vid valet av start och slutdatum
     */
    public Boolean getShowDateMessage(){
        return showDateMessage;
    }
    
    /**
     * Skapar en lista av start och slutdatumena, fÃ¶r att visa denna lista i vyn.
     * @return En lista med de valda start och slutdatumena.
     */
    public ArrayList<String> getStartDateAndEndDateList()
    {
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        
        ArrayList<String> al = new ArrayList<>();
        startDateAndEndDateList = new ArrayList<>(); 
        
        for(int i = 0; i < fromDateList.size(); i++)
        {
            startDateAndEndDateList.add(fromDateList.get(i) + " --- " + toDateList.get(i));
        }
        
        return startDateAndEndDateList;
    }
    
    /**
     * HÃ¤mtar en lista med tillgÃ¤nglighetsperioder fÃ¶r en specifik ansÃ¶kan.
     * @param id Id:t fÃ¶r den specifika ansÃ¶kan
     * @return Lista med tillgÃ¤nglighetsperioder
     */
    public List<AvailabilityDTO> getStartDateAndEndDateList(Integer id)
    {
        List<AvailabilityDTO> l = null;
        try
        {
            l = controller.getAvailabilityByApplicationId(id);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * Tar bort en kompetens som man tidigare har valt.
     * @param currentComp Den valda kompetensen som ska tas bort
     * @return JSF version 2.2 bug - Tom strÃ¤ng 
     */
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
        
        //Tar fram pÃ¥ vilken position i competenceList 
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
    
    /**
     * Ta bort en specifik period.
     * @param currentPeriod Den period som ska tas bort
     * @return JSF version 2.2 bug - Tom strÃ¤ng
     */
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
        
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        return "";
    }
    
    /**
     * Rensar alla valda kompetenser och perioder.
     * @return JSF version 2.2 bug - Tom strÃ¤ng
     */
    public String clearAll()
    {
        competenceList = new ArrayList<>();
        yearsList = new ArrayList<>();
        fromDateList = new ArrayList<>();
        toDateList = new ArrayList<>();
        confirmSuccess = false;
        goToConfirm = false;
        clickOnConfirm = false;
        return "";
    }
    
    /**
     * Kollar att den sÃ¶kande har angett minst en tillgÃ¤nglighetsperiod.
     * @return JSF version 2.2 bug - Tom strÃ¤ng 
     */
    public String checkValues()
    {
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        clickOnConfirm = true;
        return "";
    }
    
    /**
     * Anger om den sÃ¶kande kan gÃ¥ vidare till confirm.
     * @return true vid ja, annars flase
     */
    public Boolean getGoToConfirm()
    {
        return goToConfirm;
    }
    
    /**
     * Anger om den sÃ¶kande har klickat pÃ¥ confirm-knappen.
     * @return true om knappen har tryckts, annars false
     */
    public Boolean getClickOnConfirm()
    {
        return clickOnConfirm;
    }
    
    /**
     * Ã„r till fÃ¶r att godkÃ¤nna ansÃ¶kan, skickar ansÃ¶kan till databasen.
     * @return JSF version 2.2 bug - Tom strÃ¤ng 
     */
    public String confirmApplication()
    {
        String username = 
               FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        Integer jobId = Integer.parseInt(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobId"));
        try
        {
            controller.createApplication
                (competenceList, yearsList, fromDateList, toDateList, username, jobId);
            confirmSuccess = true;
            competenceList = new ArrayList<>();
            yearsList = new ArrayList<>();
            fromDateList = new ArrayList<>();
            toDateList = new ArrayList<>();
            goToConfirm = false;
            clickOnConfirm = false;
        }
        catch(Exception e)
        {
            confirmFailed = true;
        }        
        return "";
    }
    
    /**
     * Visar om ansÃ¶kan lyckades eller inte
     * @return true om ansÃ¶kan lyckades, annars false
     */
    public Boolean getConfirmSuccess(){
        return confirmSuccess;
    }
    
    /**
     * HÃ¤mtar en lista med genomfÃ¶rda ansÃ¶kningar fÃ¶r en specifik anvÃ¤ndare
     * @param username Den specifika anvÃ¤ndaren
     * @return En lista med alla genomfÃ¶rda ansÃ¶kningar
     */
    public List<ApplicationDTO> getApplicationList(String username)
    {
        List<ApplicationDTO> l = null;
        try
        {
            l = controller.getApplicationsByUsername(username);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * HÃ¤mtar ett jobbs namn via id
     * @param id Ett specifikt jobbs id
     * @return Det specifika jobbets namn
     */
    public String getJobNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getJobNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * HÃ¤mtar en kompetens namn via id
     * @param id En specifik kompetens id
     * @return Den specifika kompetensens namn
     */
    public String getCompetenceNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getCompetenceNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * HÃ¤mtar namnet pÃ¥ en status via id 
     * @param id Ett specifikt status id
     * @return Statusens namn
     */
    public String getStatusNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getStatusNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * Anger en specifik ansÃ¶kan, fÃ¶r att visa den
     * @param specificApplication Den specifika ansÃ¶kan
     */
    public void setSpecificApplication(ApplicationDTO specificApplication){
        this.specificApplication = specificApplication;
    }
    
    /**
     * HÃ¤mtar den specifika ansÃ¶kan
     * @return Den specifika ansÃ¶kan
     */
    public ApplicationDTO getSpecificApplication(){
        return specificApplication;
    }
    
    /**
     * Meddelar om ansÃ¶kan lyckades.
     * @return true om ansÃ¶kan lyckades, annars false
     */
    public Boolean getConfirmFailed()
    {
        return confirmFailed;
    }
    
    /**
     * Anger om ansÃ¶kan lyckades.
     * @param confirmFailed true om ansÃ¶kan lyckades, annars false.
     */
    public void setConfirmFailed(Boolean confirmFailed)
    {
        this.confirmFailed = confirmFailed;
    }
}