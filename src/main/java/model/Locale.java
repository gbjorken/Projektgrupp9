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
    private Collection<RoleType_Localized> roleLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Status_Localized> statusLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Competence_Localized> competenceLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
    private Collection<Job_Localized> jobLocalized;
    
    public Locale(){
    }
    
    public Locale(String lang_code){
        this.lang_code = lang_code;
    }
    
    public Integer getId() {
        return id;
    }
  
    public String getLangCode(){
        return lang_code;
    }
    
    public void setLangCode(String lang_code){
        this.lang_code = lang_code;
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
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Locale[ id=" + id + " ]";
    }
}
