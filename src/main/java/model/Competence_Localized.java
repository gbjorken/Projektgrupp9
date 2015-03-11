package model;

import DTO.CompetenceDTO;
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
 * Klassen innehåller alla översättningar av tjänstenamn.
 */
@Entity
public class Competence_Localized implements Serializable, CompetenceDTO {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "competenceLocalizedIdSeq", 
            sequenceName = "COMPETENCELOZALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "competenceName", nullable = false, unique = true)
    private String competenceName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "competence", referencedColumnName = "id", nullable = false)
    private Competence competence;
    
    /**
     * Tar in parametrar som innehåller nummer som i sin tur berättar om 
     * vilken typ av språk som ska användas för retur metoderna nedan.
     * @param competenceName Tjänstens namn
     * @param locale Vilken typ av språk
     * @param competence Antal år i denna specifika tjänst
     */
    public Competence_Localized(String competenceName, Locale locale,
                                Competence competence)
    {
        this.competenceName = competenceName;
        this.locale = locale;
        this.competence = competence;
    }

    /**
     * Default konstruktor.
     */
    public Competence_Localized() {}
    
    /**
     * Returnerar id som berättar vilken rad som ska användas.
     * @return ID nummer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returnerar det specifika numret som finns i kolumnen 
     * localize som berättar om vilket språk som är valt av
     * användaren eller default.
     * @return Språkets nummer
     */
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Siffra som representerar ett visst språk skickas in.
     * @param locale Siffra som representerar ett visst språk
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Returnerar ID:et för en specifik kompetens.
     * @return ID nummer
     */
    public Integer getCompetence(){
        return competence.getId();
    }
    
    /**
     * Skriver en specifik kompetens.
     * @param competence Kompetensen
     */
    public void setCompetence(Competence competence){
        this.competence = competence;
    }
    
    /**
     * Returnerar kompetensens namn.
     * @return Kompetensens namn
     */
    public String getCompetenceName(){
        return competenceName;
    }
    
    /**
     * Skriver in en specifik kompetensens namn. 
     * @param competenceName Kompetensens namn
     */
    public void setCompetenceName(String competenceName){
        this.competenceName = competenceName;
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
        if (!(object instanceof Competence_Localized)) {
            return false;
        }
        Competence_Localized other = (Competence_Localized) object;
        return this.id.equals(other.id);    
    }

    @Override
    public String toString() {
        return "model.Competence_Localized[ id=" + id + " ]";
    }    
}
