package view;

import controller.Controller;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="loginManager")
@SessionScoped
public class LoginManager implements Serializable {
    private static final long serialVersionUID=16247164405L;

    @EJB
    private Controller controller;

    private String username;
    private String password;
    private Boolean loginAsApplicantSuccess=false;
    private Boolean loginAsRecruiterSuccess=false;
    private Boolean logoutSuccess=true;
    private Boolean showApplicantMessage=false;
    private Boolean showRecruiterMessage=false;


    public String loged() {
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(username, password);
            logoutSuccess=false;
            if(request.isUserInRole("applicant")) { loginAsApplicantSuccess=true; }
            else if(request.isUserInRole("recruiter")) { loginAsRecruiterSuccess=true; }
        } catch(ServletException se) {
            showApplicantMessage=true;
            username=null;
        } return "";
    }
    public void unlog() {
        HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(loginAsApplicantSuccess) { loginAsApplicantSuccess=false; }
        else if(loginAsRecruiterSuccess) { loginAsRecruiterSuccess=false; }
        if(session!=null) { session.invalidate(); }
        logoutSuccess = true;
    }

    public String getUsername() {
        return username;
    } public void setUsername(String username) {
        this.username=username;
    }
    public String getPassword() {
        return password;
    } public void setPassword(String password) {
        this.password=password;
    }
    public Boolean getShowApplicantMessage() {
        return showApplicantMessage;
    } public void setShowApplicantMessage(Boolean showApplicantMessage) {
        this.showApplicantMessage = showApplicantMessage;
    }
    public Boolean getShowRecruiterMessage() {
        return showRecruiterMessage;
    } public void setShowRecruiterMessage(Boolean showRecruiterMessage) {
        this.showRecruiterMessage=showRecruiterMessage;
    }
    public boolean getLoginAsApplicantSuccess() {
        return loginAsApplicantSuccess;
    }
    public boolean getLoginAsRecruiterSuccess() {
        return loginAsRecruiterSuccess;
    }
    public boolean getLogoutSuccess() {
        return logoutSuccess;
    }
}
