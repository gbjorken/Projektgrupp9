package view;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Klassen lägger in rätt språk för hela applikationen.
 */
@Named(value="locale")
@SessionScoped
public class LocaleManager implements Serializable
{
    private String lang;
    
    /**
     * Variabeln lang tar in det valda språket. 
     * Valt av antingen användaren eller defaulten.
     */
    @PostConstruct
    public void init(){
        lang = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
    }
    
    /**
     * Returnerar det nuvarande språket.
     * @return Språket
     */
    public String getLang() {
        return lang;
    }

    /**
     * Skriver in angivet språk.
     * @param lang Språket
     */
    public void setLang(String lang) {
        this.lang = lang;
    }
    
    /**
     * Ändrar till annat språk.
     */
    public void changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(lang));
    }
}
