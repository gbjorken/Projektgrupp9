package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Klassen locale bestämmer vilket språk användaren ska se hemsidan på.
 */
@Entity
public class Locale implements Serializable {
    @Id
    @SequenceGenerator( name = "localeIdSeq", sequenceName = "LOCALE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "localeIdSeq" )
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "lang_code", unique = true, nullable = false)
    private String lang_code;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Status_Localized> statusLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Competence_Localized> competenceLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Job_Localized> jobLocalized;
    
    /**
     * Tar in språkets kod.
     * @param lang_code Språkkod
     */
    public Locale(String lang_code){
        this.lang_code = lang_code;
    }

    /**
     * Default konstruktor.
     */
    public Locale() {}
    
    /**
     * Returnerar kolumnen IDs rad.
     * @return ID
     */
    public Integer getId() {
        return id;
    }
  
    /**
     * Returnerar språkets kod.
     * @return ID språk
     */
    public String getLangCode(){
        return lang_code;
    }
    
    /**
     * Skriver in språkets kod.
     * @param lang_code Språkets ID
     */
    public void setLangCode(String lang_code){
        this.lang_code = lang_code;
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
     * Check om rätt språk har hittats eller ej.
     * @param object Integer av språk som inparameter.
     * @return Boolean om true eller false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        return this.id.equals(other.id);
    }

    /**
     * Konverterar ett språk ID till en sträng i en mening.
     * @return ID som sträng
     */
    @Override
    public String toString() {
        return "model.Locale[ id=" + id + " ]";
    }
}
