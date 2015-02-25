package model;

import DTO.JobDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Klassen skapar jobbnamn och användarens preferens angående språk väljs.
 */
@Entity
public class Job_Localized implements Serializable, JobDTO 
{
    @Id
    @SequenceGenerator(name = "jobLocalizedIdSeq", 
            sequenceName = "JOBLOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "jobName", nullable = false, unique = true)
    private String jobName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    private Job job;

    /**
     * Metoden som kallas då jobbet skrivs in. 
     * @param jobName Tjänstens namn
     * @param locale Språket
     * @param job Jobbet
     */
    public Job_Localized(String jobName, Locale locale,
                          Job job)
    {
        this.jobName = jobName;
        this.locale = locale;
        this.job = job;
    }

    /**
     * Default konstruktor.
     */
    public Job_Localized() {}
    
    /**
     * Returnerar ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Returnerar vilket språk som valts.
     * @return SpråkID
     */
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Väljer vilket språk jobbet ska presenteras på.
     * @param locale SpråkID
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Returnerar jobbets ID
     * @return JobbID
     */
    public Integer getJob(){
        return job.getId();
    }
    
    /**
     * Skickar in jobbet.
     * @param job Jobbet
     */
    public void setJob(Job job){
        this.job = job;
    }
    
    /**
     * Returnerar typen av jobbets namn.
     * @return Typen av jobbnamnet
     */
    public String getJobTypeName(){
        return jobName;
    }
    
    /**
     * Skriver in jobbets namn.
     * @param jobName Jobbets namn
     */
    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    /**
     * Det är hash överallt.
     * @return Hashkoden
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check om rätt kompetensprofil har hittats eller ej.
     * @param object Kompetensens profil som inparameter.
     * @return Boolean om true eller false
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Job_Localized)) {
            return false;
        }
        Job_Localized other = (Job_Localized) object;
        return this.id.equals(other.id);
    }

    /**
     * Konverterar en kompetens profils ID till en sträng i en mening.
     * @return ID som sträng
     */
    @Override
    public String toString() {
        return "model.Job_Localized[ id=" + id + " ]";
    }
}
