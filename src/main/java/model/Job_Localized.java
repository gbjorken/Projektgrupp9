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
public class Job_Localized implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "jobLocalizedIdSeq", 
            sequenceName = "JOBLOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "jobName", nullable = false)
    private String jobName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    private Job job;
    
    public Job_Localized(){
    }
    
    public Job_Localized(String jobName, Locale locale,
                                Job job)
    {
        this.jobName = jobName;
        this.locale = locale;
        this.job = job;
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
    
    public Integer getCompetence(){
        return job.getId();
    }
    
    public void setCompetence(Competence competence){
        this.job = job;
    }
    
    public String getCompetenceName(){
        return jobName;
    }
    
    public void setCompetenceName(String competenceName){
        this.jobName = competenceName;
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
        if (!(object instanceof Job_Localized)) {
            return false;
        }
        Job_Localized other = (Job_Localized) object;
        return this.id.equals(other.id);    
    }

    @Override
    public String toString() {
        return "model.Job_Localized[ id=" + id + " ]";
    }    
}
