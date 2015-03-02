package model;

import DTO.AvailabilityDTO;
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
 * Tillgänglighet för en anställd eller sökande att jobba
 * under vissa perioder på arbetsplatsen.
 */
@Entity
public class Availability implements Serializable, AvailabilityDTO 
{
    @Id
    @SequenceGenerator(name = "availabilityIdSeq", sequenceName = "AVAILABILITY_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "availabilityIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "from_date", length = 10, nullable = false)
    private String from_date;

    @Basic(optional = false)
    @Column(name = "to_date", length = 10, nullable = false)
    private String to_date;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "application", referencedColumnName = "id", nullable = false)
    private Application application;
    
    /**
     * Metoden som kallas och tar inparametrar då en tillgänglighet skapas.
     * @param from_date Från vilket datum kan personen jobba
     * @param to_date Till vilket datum som personen kan jobba
     * @param application Vilka tider som tillhör applikation
     */
    public Availability(String from_date, String to_date, Application application)
    {
        this.from_date = from_date;
        this.to_date = to_date;
        this.application = application;
    }

    /**
     * Default konstruktor.
     */
    public Availability() {}
    
    /**
     * Returnerar ett nummer från kolumnen ID ur en specifik tillgänglighet från en sökande.
     * @return ID
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Returerar ett startdatum för en arbetande att arbeta.
     * @return Startdatum
     */
    public String getFromDate(){
        return this.from_date;
    }
    
    /**
     * Skriver in i databasen vilket datum en sökande kan börja arbeta.
     * @param from_date Startdatum
     */
    public void setFromDate(String from_date){
        this.from_date = from_date;
    }
    
    /**
     * Returnerar datumet då sökande inte längre kan jobba.
     * @return Slutdatum
     */
    public String getToDate(){
        return this.to_date;
    }
    
    /**
     * Skriver in i databasen vilket datum sökande inte längre kan arbeta.
     * @param to_date Slutdatum
     */
    public void setToDate(String to_date){
        this.to_date = to_date;
    }
    
    /**
     * Returnerar ID:et från en viss applikation.
     * @return Applikations ID.
     */
    public Integer getApplication(){
        return application.getId();
    }
    
    /**
     * Skriver in applikationen i databasen.
     * @param application Applikationen
     */
    public void setApplication(Application application){
        this.application = application;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + id + " ]";
    }
}
