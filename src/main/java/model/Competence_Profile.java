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
public class Competence_Profile implements Serializable {
    @Id
    @SequenceGenerator(name = "competenceProfileIdSeq", 
            sequenceName = "COMPETENCEPROFILE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceProfileIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "years_of_experience" ,nullable = false, columnDefinition="Decimal(4,2)")
    private Double years_of_experience;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "competence", referencedColumnName = "id", nullable = false)
    private Competence competence;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "application", referencedColumnName = "id", nullable = false)
    private Application application;
    
    public Competence_Profile(){
    }
    
    public Competence_Profile(Double years_of_experience, Competence competence,
                              Application application)
    {
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        this.application = application;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getCompetence(){
        return competence.getId();
    }
    
    public void setCompetence(Competence competence){
        this.competence = competence;
    }
    
    public Double getYearsOfExperience(){
        return years_of_experience;
    }
    
    public void setYearsOfExperience(Double years_of_experience){
        this.years_of_experience = years_of_experience;
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
        if (!(object instanceof Competence_Profile)) {
            return false;
        }
        Competence_Profile other = (Competence_Profile) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Competence_profile[ id=" + id + " ]";
    }
}
