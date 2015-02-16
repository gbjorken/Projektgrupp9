package view;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Sets the correct locale for the entire application.
 */
@Named(value="locale")
@SessionScoped
public class LocaleManager implements Serializable
{
    private String lang;
    
    @PostConstruct
    public void init(){
        lang = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
    public void changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(lang));
    }
}
