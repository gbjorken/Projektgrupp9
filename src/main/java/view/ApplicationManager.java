package view;

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

/**
 * Klassen ApplicationManager tar hand om allt som rör applikationer i vyn.
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
    private ArrayList<Double> yearsList = new ArrayList<>();
    private ArrayList<String> fromDateList = new ArrayList<>();
    private ArrayList<String> toDateList = new ArrayList<>();
    
    private String competence;
    private Double years;
    private Date startDate;
    private Date endDate;
    private Boolean showDateMessage;
    private Boolean enableButton;
    
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
     * Returnerar antal år för en kompetens.
     * @return Antal år för en kompetens
     */
    public Double getYears(){
        return years;
    }
    
    /**
     * Skriver in antal år för en kompetens.
     * @param years Antal år för en kompetens
     */
    public void setYears(Double years){
        this.years = years;
    }
    
    private Competence[] comList;
    
    /**
     * 
     * @return 
     */
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
    
    /**
     * Är av typen boolean. 
     * Om nuvarande kompetenslista innehåller kompetenser kommer "Lägg till"
     * knappen att vara aktiverad.
     * Om nuvarande kompetenslista blir tom kommer
     * "Lägg till" knappen att bli inaktiverad.
     * @return Kompetenslistans längd
     */
    public Boolean getEnableButton(){
        return comList.length > 0;
    }
    
    /**
     * Lägg till kompetens till kompetenslistan.
     * @return JSF version 2.2 bug - Tom sträng
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
     * Användaren väljer datum för tillgänglighet.
     * @param event Event lyssnare
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    /**
     * Returnerar startdatumet för tillgänglighet.
     * @return Startdatum
     */
    public Date getStartDate() {
        return startDate;
    }
 
    /**
     * Skriver in startdatumet för tillgänglighet.
     * @param startDate Startdatum
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Returnerar slutdatumet för tillgänglighet.
     * @return Slutdatum
     */
    public Date getEndDate() {
        return endDate;
    }
 
    /**
     * * Returnerar slutdatumet för tillgänglighet.
     * @param endDate Slutdatum
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Lägg till datum.
     * @return JSF version 2.2 bug - Tom sträng
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
     * Checkar om det finns namn kvar i kompetenslistan eller inte.
     * Används av "Lägg till" knappen som en "boolean hjälpare".
     * @return Listans längd
     */
    public ArrayList<String> getCompetenceList(){
        ArrayList<String> al = new ArrayList<>();
        
        String c;
        for(int i = 0; i < competenceList.size(); i++)
        {
            c = competenceList.get(i);
            for(int j = 0; j < compList.size(); j++)
            {
                if(c.equals(compList.get(i).getCompetence().toString()))
                {
                    al.add(compList.get(i).getCompetenceName());
                    break;
                }
            }
        }    
        
        return al;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getFromDateList(){
        return fromDateList;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getToDateList(){
        return toDateList;
    }
    
    /**
     * 
     * @return 
     */
    public Boolean getShowDateMessage(){
        return showDateMessage;
    }           
}