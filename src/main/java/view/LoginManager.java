package view;

import controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Klassen är vyn för alla typer av registreringar.
 */
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
    
    /**
     * Conversation scoped bean start.
     * Alla värden sparas.
     */
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * Conversation scoped bean stop.
     * Alla sparade värden tas bort.
     */
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    /**
     * Skriver in sökandes användarnamn.
     * @param username Användarnamn
     */
    public void setApplicantUsername(String username){
        this.applicantUsername = username;
    }
    
    /**
     * Returnerar sökandes anvädarnamn.
     * @return Användarnamn
     */
    public String getApplicantUsername(){
        return applicantUsername;
    }
    
    /**
     * Skriver in sökandes lösenord.
     * @param password Löenord
     */
    public void setApplicantPassword(String password){
        this.applicantPassword = password;
    }
    
    /**
     * Returnerar sökandes lösenord.
     * @return Lösenord
     */
    public String getApplicantPassword(){
        return applicantPassword;
    }
    
    /**
     * Skriver in rekryterares användarnamn.
     * @param username Användarnamn
     */
    public void setRecruiterUsername(String username){
        this.recruiterUsername = username;
    }
    
    /**
     * Returnerar rekryterarens användarnamn.
     * @return Användarnamn
     */
    public String getRecruiterUsername(){
        return recruiterUsername;
    }
    
    /**
     * Skriver in rekryterarens lösenord.
     * @param password Lösenord
     */
    public void setRecruiterPassword(String password){
        this.recruiterPassword = password;
    }
    
    /**
     * Returnerar rekryterarens lösenord.
     * @return Lösenord
     */
    public String getRecruiterPassword(){
        return recruiterPassword;
    }
    
    /**
     * Logga in som sökande
     */
    public void loginAsApplicant() {
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
    }
    
    /**
     * Loggar ut en sökande.
     */
    public void logoutAsApplicant(){
        loginAsApplicantSuccess = false;
        logoutSuccess = true;
        stopConversation();
    }
    
    /**
     * Är av typen boolean om login av sökande lyckats eller ej.
     * @return TrueOrFalse
     */
    public boolean getLoginAsApplicantSuccess(){
        return loginAsApplicantSuccess;
    }
    
    /**
     * Är av typen boolean om utloggning gått bra eller ej.
     * @return TrueOrFalse
     */
    public boolean getLogoutSuccess(){
        return logoutSuccess;
    }
    
    /**
     * Loggar in rekryteraren med parametrarna användarnamn och lösenord.
     * Vid misslyckad inloggning skrivs meddelande ut till användaren.
     * Vid lyckad inloggning tas användaren vidare i systemet.
     */
    public void loginAsRecruiter(){
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
    }
    
    /**
     * Loggar ut en rekryterare.
     */
    public void logoutAsRecruiter(){
        loginAsRecruiterSuccess = false;
        logoutSuccess = true;
        stopConversation();
    }
    
    /**
     * Är av typen boolean om login av rekryteraren lyckats eller ej.
     * @return TrueOrFalse
     */
    public boolean getLoginAsRecruiterSuccess(){
        return loginAsRecruiterSuccess;
    }
    
    /**
     * Returnerar applikationsmeddelanden.
     * @return Meddelande
     */
    public String getApplicantMessage(){
        return applicantMessage;
    }
    
    /**
     * Skriver in applikationsmeddelanden.
     * @param message Meddelande
     */
    public void setApplicantMessage(String message){
        this.applicantMessage = message;
    }
    
    /**
     * Returnerar rekryterarnasmeddelande. 
     * @return Meddelande
     */
    public String getRecruiterMessage(){
        return recruiterMessage;
    }
    
    /**
     * Skriver in meddelande om rekryteraren.
     * @param message Meddelande
     */
    public void setRecruiterMessage(String message){
        this.recruiterMessage = message;
    }
}
