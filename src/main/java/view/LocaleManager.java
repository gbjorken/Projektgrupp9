package view;

import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Sets the correct locale for the entire application.
 */
@Named(value="locale")
@ApplicationScoped
public class LocaleManager {
    public void changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getLanguageCode()));
    }

    private String getLanguageCode() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
    }
}
