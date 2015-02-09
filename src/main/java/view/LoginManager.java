package view;

import controller.LoginFacade;
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
    private LoginFacade loginFacade;
    
    private String applicantUsername;
    private String applicantPassword;
    private String recruiterUsername;
    private String recruiterPassword;
    private boolean loginAsApplicantSuccess;
    private boolean loginAsRecruiterSuccess;
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
    
    public void loginAsApplicant(){
        startConversation();
        loginAsApplicantSuccess = loginFacade.
                loginAsApplicant(applicantUsername, applicantPassword);
        if(!loginAsApplicantSuccess)
        {
            applicantMessage = "Inloggningen misslyckades";
            applicantUsername = null;
            stopConversation();
        }
    }
    
    public boolean getLoginAsApplicantSuccess(){
        return loginAsApplicantSuccess;
    }
    
    public void loginAsRecruiter(){
        startConversation();
        loginAsRecruiterSuccess = loginFacade.
                loginAsRecruiter(recruiterUsername, recruiterPassword);
        if(!loginAsRecruiterSuccess)
        {
            recruiterMessage = "Inloggningen misslyckades";
            recruiterUsername = null;
            stopConversation();
        }
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