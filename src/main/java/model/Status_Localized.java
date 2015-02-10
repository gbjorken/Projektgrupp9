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
    
    public Status_Localized(){
    }
    
    public Status_Localized(String statusName, Locale locale, Status status){
        this.statusName = statusName;
        this.locale = locale;
        this.status = status;
    }
        
    public Integer getId() {
        return id;
    }

    public String getLocale(){
        return locale.getLangCode();
    }
    
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    public Integer getStatus(){
        return status.getId();
    }
    
    public void setStatus(Status status){
        this.status = status;
    }
    
    public String getStatusName(){
        return statusName;
    }
    
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
