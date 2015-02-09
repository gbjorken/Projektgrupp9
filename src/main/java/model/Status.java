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
public class Status implements Serializable {
    @Id
    @SequenceGenerator(name = "statusIdSeq", sequenceName = "STATUS_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Status_Localized> statusLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Application> applications;
    
    public Status(){
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Status[ id=" + id + " ]";
    }    
}
