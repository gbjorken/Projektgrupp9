package view;

import controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("loginManager")
@ConversationScoped
public class LoginManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    private Controller controller;
    
    private String applicantUsername;
    private String applicantPassword;
    private String recruiterUsername;
    private String recruiterPassword;
    private Boolean loginAsApplicantSuccess = false;
    private Boolean loginAsRecruiterSuccess = false;
    private Boolean logoutSuccess = true;
    private String applicantMessage;
    private String recruiterMessage;
        
    @Inject
    private Conversation conversation; 
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private String jsf22Bugfix() {
        return "";
    }
    
    public void setApplicantUsername(String username){
        this.applicantUsername = username;
    }
    
    public String getApplicantUsername(){
        return applicantUsername;
    }
    
    public void setApplicantPassword(String password){
        this.applicantPassword = password;
    }
    
    public String getApplicantPassword(){
        return applicantPassword;
    }
    
    public void setRecruiterUsername(String username){
        this.recruiterUsername = username;
    }
    
    public String getRecruiterUsername(){
        return recruiterUsername;
    }
    
    public void setRecruiterPassword(String password){
        this.recruiterPassword = password;
    }
    
    public String getRecruiterPassword(){
        return recruiterPassword;
    }
    
    /*public void loginAsApplicant(){
        startConversation();
        loginAsApplicantSuccess = controller.
                loginAsApplicant(applicantUsername, applicantPassword);
        
        if(loginAsApplicantSuccess)
            logoutSuccess = false;
        
        if(!loginAsApplicantSuccess)
        {
            applicantMessage = "Inloggningen misslyckades";
            applicantUsername = null;
            stopConversation();
        }
    }*/
    
    public void logoutAsApplicant(){
        loginAsApplicantSuccess = false;
        logoutSuccess = true;
        stopConversation();
    }
    
    public boolean getLoginAsApplicantSuccess(){
        return loginAsApplicantSuccess;
    }
    
    public boolean getLogoutSuccess(){
        return logoutSuccess;
    }
    
    /*public void loginAsRecruiter(){
        startConversation();
        loginAsRecruiterSuccess = controller.
                loginAsRecruiter(recruiterUsername, recruiterPassword);
        
        if(loginAsRecruiterSuccess)
            logoutSuccess = false;
        
        if(!loginAsRecruiterSuccess)
        {
            recruiterMessage = "Inloggningen misslyckades";
            recruiterUsername = null;
            stopConversation();
        }
    }*/
    
    public void logoutAsRecruiter(){
        loginAsRecruiterSuccess = false;
        logoutSuccess = true;
        stopConversation();
    }
    
    public boolean getLoginAsRecruiterSuccess(){
        return loginAsRecruiterSuccess;
    }
    
    public String getApplicantMessage(){
        return applicantMessage;
    }
    
    public void setApplicantMessage(String message){
        this.applicantMessage = message;
    }
    
    public String getRecruiterMessage(){
        return recruiterMessage;
    }
    
    public void setRecruiterMessage(String message){
        this.recruiterMessage = message;
    }
}
