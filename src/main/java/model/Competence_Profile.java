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
 * Klassen skapar kompetens profiler.
 */
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
    
    /**
     * Metoden som kallas med inparametrar om år av erfarenhet, namn på tjänst
     * och applikation. Denna klass skapar en egen profil för varje användare.
     * @param years_of_experience Antal år inom en viss tjänst.
     * @param competence En viss tjänsts namn
     * @param application Applikationen som gjorts av sökande
     */
    public Competence_Profile(Double years_of_experience, Competence competence,
                              Application application)
    {
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        this.application = application;
    }
    
    /**
     * Returnerar ett ID.
     * @return ID nummer
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Returnerar ID för specifik kompetens.
     * @return ID av kompetens
     */
    public Integer getCompetence(){
        return competence.getId();
    }
    
    /**
     * Skriver in en viss kompetens.
     * @param competence Kompetensens namn
     */
    public void setCompetence(Competence competence){
        this.competence = competence;
    }
    
    /**
     * Returnerar antalet år som en specifik person jobbat inom en
     * viss tjänst.
     * @return antal år i en tjänst
     */
    public Double getYearsOfExperience(){
        return years_of_experience;
    }
    
    /**
     * Skriver in antalet år inom en viss tjänst.
     * @param years_of_experience antal år i en tjänst 
     */
    public void setYearsOfExperience(Double years_of_experience){
        this.years_of_experience = years_of_experience;
    }
    
    /**
     * Returnerar ID för en specifik applikation
     * @return ID av applikation
     */
    public Integer getApplication(){
        return application.getId();
    }
    
    /**
     * Skriver in applikationen.
     * @param application Applikationen
     */
    public void setApplication(Application application){
        this.application = application;
    }

    /**
     * Det är hash överallt.
     * @return Hashkoden
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check om rätt kompetensprofil har hittats eller ej.
     * @param object Kompetensens profil som inparameter.
     * @return Boolean om true eller false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence_Profile)) {
            return false;
        }
        Competence_Profile other = (Competence_Profile) object;
        return this.id.equals(other.id);
    }
    
    /**
     * Konverterar en kompetens profils ID till en sträng i en mening.
     * @return ID som sträng
     */
    @Override
    public String toString() {
        return "model.Competence_profile[ id=" + id + " ]";
    }
}
