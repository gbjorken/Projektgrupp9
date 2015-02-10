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
public class Availability implements Serializable {
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
    
    public Availability(){
    }
    
    public Availability(String from_date, String to_date, Application application)
    {
        this.from_date = from_date;
        this.to_date = to_date;
        this.application = application;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getFromDate(){
        return this.from_date;
    }
    
    public void setFromDate(String from_date){
        this.from_date = from_date;
    }
    
    public String getToDate(){
        return this.to_date;
    }
    
    public void setToDate(String to_date){
        this.to_date = to_date;
    }
    
    public Integer getApplication(){
        return application.getId();
    }
    
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
