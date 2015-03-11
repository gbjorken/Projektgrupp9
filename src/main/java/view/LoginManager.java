package view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Klassen tar hand om in- och utloggning av användare, och sparar flaggor för XHTML-sidor.
 */
@ManagedBean(name="loginManager")
@SessionScoped
public class LoginManager implements Serializable {
    private static final long serialVersionUID=16247164405L;

    private String username;
    private String password;
    private Boolean loginAsApplicantSuccess=false;
    private Boolean loginAsRecruiterSuccess=false;
    private Boolean logoutSuccess=true;
    private Boolean showApplicantMessage=false;
    private Boolean showRecruiterMessage=false;

    /**
     * Hämtar HTTPServlet-förfrågan för att logga in via JDBC-realmen på Glassfish-servern.
     * Sätter flaggorna lämpligt efter användarens roll.
     */
    public void login() {
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(username, password);
            logoutSuccess=false;
            if(request.isUserInRole("applicant")) { loginAsApplicantSuccess=true; }
            else if(request.isUserInRole("recruiter")) { loginAsRecruiterSuccess=true; }
        } catch(ServletException se) {
            showApplicantMessage=true;
            username=null;
        }
    }

    /**
     * Hämtar HTTP-sessionen för att invalidera inloggningen.
     * Sätter flaggorna lämpligt efter användarens roll.
     */
    public void logout() {
        HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(loginAsApplicantSuccess) { loginAsApplicantSuccess=false; }
        else if(loginAsRecruiterSuccess) { loginAsRecruiterSuccess=false; }
        if(session!=null) { session.invalidate(); }
        logoutSuccess = true;
    }

    /**
     * Hämtar användarens namn.
     * @return Användarens namn
     */
    public String getUsername() {
        return username;
    }

    /**
     * Ställer användarnamnet. Används för inloggning.
     * @param username Nytt användarnamn
     */
    public void setUsername(String username) {
        this.username=username;
    }

    /**
     * Hämtar användarens lösenord.
     * @return Lösenordet för användaren
     */
    public String getPassword() {
        return password;
    }

    /**
     * Ställer användaren lösenord. Används för inloggning.
     * @param password Nytt lösenord
     */
    public void setPassword(String password) {
        this.password=password;
    }


    /**
     * Hämtar flaggan för visning av felaktig inloggning för ansökande.
     * @return Flagga angående felaktig inloggning
     */
    public Boolean getShowApplicantMessage() {
        return showApplicantMessage;
    }

    /**
     * Hämtar flaggan för visning av felaktig inloggning för rekryterande.
     * @return Flagga angående felaktig inloggning
     */
    public Boolean getShowRecruiterMessage() {
        return showRecruiterMessage;
    }

    /**
     * Hämtar flaggan för lyckad inloggning av ansökande.
     * @return Flagga angående lyckad inloggning av ansökande
     */
    public boolean getLoginAsApplicantSuccess() {
        return loginAsApplicantSuccess;
    }

    /**
     * Hämtar flaggan för lyckad inloggning av rekryterande.
     * @return Flagga angående lyckad inloggning av rekryterande
     */
    public boolean getLoginAsRecruiterSuccess() {
        return loginAsRecruiterSuccess;
    }

    /**
     * Hämtar flaggan för lyckad utloggning av användare.
     * @return Flagga angående lyckad utloggning
     */
    public boolean getLogoutSuccess() {
        return logoutSuccess;
    }
}
