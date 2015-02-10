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
public class Competence_Localized implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "competenceLocalizedIdSeq", 
            sequenceName = "COMPETENCELOZALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "competenceName", nullable = false)
    private String competenceName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "competence", referencedColumnName = "id", nullable = false)
    private Competence competence;
    
    public Competence_Localized(){
    }
    
    public Competence_Localized(String competenceName, Locale locale,
                                Competence competence)
    {
        this.competenceName = competenceName;
        this.locale = locale;
        this.competence = competence;
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
        return competence.getId();
    }
    
    public void setCompetence(Competence competence){
        this.competence = competence;
    }
    
    public String getCompetenceName(){
        return competenceName;
    }
    
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
