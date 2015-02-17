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

/**
 * Klassen tar in värden för hur lång tid en ansökande
 * har jobbat inom en specifik tjänst.
 */
@Entity
public class Competence implements Serializable {
    @Id
    @SequenceGenerator(name = "competenceIdSeq", sequenceName = "COMPETENCE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competence_Localized> competenceLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competence_Profile> competenceProfile;
    
    /**
     * Tom konstruktor.
     */
    public Competence(){
    }
    
    /**
     * Returnerar ett värde på ID som berättar om vilken
     * rad kompentensens värde ligger på.
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Hashassins creed.
     * @return Hashen
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check om rätt värde har hittats eller ej.
     * @param object Värdet som inparameter.
     * @return Boolean om true eller false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        return this.id.equals(other.id);
    }

    /**
     * Konverterar ett ID till en sträng i en mening.
     * @return ID som sträng
     */
    @Override
    public String toString() {
        return "model.Competence[ id=" + id + " ]";
    }
}
