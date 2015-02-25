package view;

import controller.Controller;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named("loginManager")
@ManagedBean
public class LoginManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    private Controller controller;
    
    private String username;
    private String password;
    private Boolean loginAsApplicantSuccess = false;
    private Boolean loginAsRecruiterSuccess = false;
    private Boolean logoutSuccess = true;
    private Boolean showApplicantMessage = false;
    private Boolean showRecruiterMessage = false;
    
    public LoginManager()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean getLoginAsApplicantSuccess(){
        return loginAsApplicantSuccess;
    }
    
    public boolean getLogoutSuccess(){
        return logoutSuccess;
    }
    
    public boolean getLoginAsRecruiterSuccess(){
        return loginAsRecruiterSuccess;
    }
    
    public Boolean getShowApplicantMessage(){
        return showApplicantMessage;
    }
    
    public void setShowApplicantMessage(Boolean showApplicantMessage){
        this.showApplicantMessage = showApplicantMessage;
    }
    
    public Boolean getShowRecruiterMessage(){
        return showRecruiterMessage;
    }
    
    public void setShowRecruiterMessage(Boolean showRecruiterMessage){
        this.showRecruiterMessage = showRecruiterMessage;
    }
    
    public String login()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try 
        {
            //Login via the Servlet Context
            request.login(username, password);

            if(request.isUserInRole("applicant"))
            {
                loginAsApplicantSuccess = true;
            }
            else if(request.isUserInRole("recruiter"))
            {
                loginAsRecruiterSuccess = true;
            }
            logoutSuccess = false;
        } 
        catch (ServletException e) 
        {   
            showApplicantMessage = true;
            username = null;
            e.printStackTrace();
        }
        return "";
    }
    
    public void logout()
    {
        if(loginAsApplicantSuccess)
        {
            loginAsApplicantSuccess = false;
        }
        else if(loginAsRecruiterSuccess)
        {
            loginAsRecruiterSuccess = false;
        }
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
                session.invalidate();
        }
        
        logoutSuccess = true;
    }
}
