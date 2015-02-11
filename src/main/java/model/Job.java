package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Job implements Serializable {
    @Id
    @SequenceGenerator(name = "jobIdSeq", sequenceName = "JOB_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Collection<Job_Localized> jobLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Collection<Application> applications;
    
    public Job(){
    }
    
    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Job[ id=" + id + " ]";
    }    
}
