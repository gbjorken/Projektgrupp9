package model;

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
 * Entitet "Status_Localized" lagrar "hired or fired" statusen på olika språk.
 * Den är kopplad till "Status" och "Locale"-entiteternas "id"-kolumner och använder dessa främmande nycklar för att identifiera språket och statusen.
 */
@Entity
public class Status_Localized implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "statusLocalizedIdSeq", 
            sequenceName = "STATUSLOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "statusName", nullable = false, unique = true)
    private String statusName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;
    
    /**
     * Konstruktorn som används då vi lägger till något nytt.
     * @param statusName Namn på status
     * @param locale Typ av språk
     * @param status Statusen av typen av språk
     */
    public Status_Localized(String statusName, Locale locale, Status status){
        this.statusName = statusName;
        this.locale = locale;
        this.status = status;
    }

    /**
     * Default konstruktor.
     */
    public Status_Localized() {}
        
    /**
     * Returnerar "id" (primärnyckeln) från kolumnen "id".
     * @return ID kod
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returnerar kod för vilket språk som används.
     * @return Språkkod
     */
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Skriver in koden för valt språk.
     * @param locale Språkkod
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Returnerar den främmande nyckeln för vilken status ordet motsvarar.
     * @return Språkkod
     */
    public Integer getStatus(){
        return status.getId();
    }
    
    /**
     * Skriver in vad statusen är på detta språk.
     * @param status HiredOrFired på relevant språk
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * Returnerar namnet på statusen.
     * @return status Namnet på statusen
     */
    public String getStatusName(){
        return statusName;
    }

    /**
     * Skriver in statusens namn.
     * @param statusName Status namn
     */
    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Status_Localized)) {
            return false;
        }
        Status_Localized other = (Status_Localized) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Status_Localized[ id=" + id + " ]";
    }
}
