package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Klassen Applikation. Applikationer skapas i denna klass.
 * @author Ansoa
 */
@Entity
public class Application implements Serializable {
    @Id
    @SequenceGenerator(name = "applicationIdSeq", sequenceName = "APPLICATION_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicationIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "date_made", length = 10, nullable = false)
    private String date_made;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private Collection<Competence_Profile> competenceProfile;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private Collection<Availability> availability;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    private Person person;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    private Job job;
    
    //@ManyToOne(optional = false)
   // @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    //private Job job;
    
    /**
     * Tom konstruktor.
     */
    public Application(){
    }
    
    /**
     * Inparametrar då en applikation görs.
     * @param date_made Datumet applikationen gjordes.
     * @param person Vem som gjorde applikationen.
     * @param status Statusen på om applikationen blivit recenserad av rekryterare.
     * @param job Vilken tjänst / typ av job.
     */
    public Application(String date_made, Person person, Status status, Job job)
    {
        this.date_made = date_made;
        this.person = person;
        this.status = status;
        this.job = job;
    }
    
    
    /**
     * Returnerar ett nummer av kolumnen ID i en applikation från en sökande.
     * @return Raden ids nummer.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returnerar datumet då applikationen från en sökande gjordes.
     * @return skapande datum.
     */
    public String getDateMade(){
        return date_made;
    } 
    
    /**
     * Skriver in datumet i databasen då applikationen gjordes.
     * @param date_made dåvarande datum
     */
    public void setDateMade(String date_made){
        this.date_made = date_made;
    }
    
    /**
     * Returnerar användarens ID av den som gjorde applikationen.
     * @return personens id
     */
    public Integer getPerson(){
        return person.getId();
    }
    
    /**
     * Skriver in personen i databasen som gjorde applikationen.
     * @param person själva personen
     */
    public void setPerson(Person person){
        this.person = person;
    }
    
    /**
     * Berättar om applikationen blivit godkänd eller inte.
     * @return id:ets status 
     */
    public Integer getStatus(){
        return status.getId();
    }
    
    /**
     * Skriver in status på applikationen i databasen om applikationen
     * eller recenserad eller ej.
     * @param status på id:et.
     */
    public void setStatus(Status status){
        this.status = status;
    }
    
    /**
     * Returnerar ett ID från databasen om ett specifikt jobb.
     * @return ID om jobb
     */
    public Integer getJob(){
        return job.getId();
    }
    
    /**
     * Skriver in i databasen om en specifikt jobbtjänst.
     * @param job Tjänst
     */
    public void setJob(Job job){
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Application[ id=" + id + " ]";
    }
}
