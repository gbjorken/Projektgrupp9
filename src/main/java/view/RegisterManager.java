package view;

import view.validators.ValidSSN;
import controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import view.validators.ValidEmail;

/**
 * Klassen behandlar alla registreringar som görs via vyn.
 */
@Named("registerManager")
@ConversationScoped
public class RegisterManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private String name;
    private String surname;
    @ValidSSN
    private String ssn;
    @ValidEmail
    private String email;
    private String username;
    private String password;
    private String repeatPassword;
    private Boolean registerSuccess = false;
    private Boolean showPasswordMessage;
    private Boolean showMessage;
    private Boolean registrationFailed = false;
    
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
     * Skriver in användarens förnamn.
     * @param name Namn
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Returnerar användarens förnamn.
     * @return Namn
     */
    public String getName(){
        return name;
    }
    
    /**
     * Skriver in användarens efternamn.
     * @param surname Efternamn
     */
    public void setSurname(String surname){
        this.surname = surname;
    }
    
    /**
     * Returnerar användarens efternamn.
     * @return Efternamn
     */
    public String getSurname(){
        return surname;
    }
    
    /**
     * Skriver in användarens personnummer.
     * @param ssn Personnummer
     */
    public void setSsn(String ssn){
        this.ssn = ssn;
    }
    
    /**
     * Returnerar användarens personnummer.
     * @return Personnummer
     */
    public String getSsn(){
        return ssn;
    }
    
    /**
     * Skriver in användarens E-post adress.
     * @param email E-post adress
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Returnerar användarens E-post adress.
     * @return E-post adress
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Skriver in användarens användarnamn.
     * @param username Användarnamn
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Returnerar användarens användarnamn.
     * @return Användarnamn
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * Skriver in användarens lösenord.
     * @param password Lösenord
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Returnerar användarens lösenord.
     * @return Lösenord
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * Skriver in användarens verifiering av lösenord.
     * @param repeatPassword Verifiera lösenord
     */
    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }
    
    /**
     * Returnerar användarens verifierade lösenord.
     * @return Verifierat lösenord
     */
    public String getRepeatPassword(){
        return repeatPassword;
    }
    
    /**
     * Skriver in en typ av meddelande. Parametern är en boolean.
     * @param show Meddelande
     */
    public void setShowMessage(Boolean show){
        this.showMessage = show;
    }
    
    /**
     * Är av typen boolean. Returnerar ett meddelande om True. 
     * @return TrueOrFalse
     */
    public Boolean getShowMessage(){
        return showMessage;
    }
    
    /**
     * Är av typen boolean. Returnerar True om registreringen är godkänd.
     * @return TrueOrFalse
     */
    public Boolean getRegisterSuccess(){
        return registerSuccess;
    }
    
    /**
     * Skriver in lösenordsmeddelande. Parametern är av typen boolean.
     * @param show TrueOrFalse
     */
    public void setShowPasswordMessage(Boolean show){
        this.showPasswordMessage = show;
    }
    
    /**
     * Är av typen boolean. Returnerar True om meddelande ska visas om
     * lösenordet gått igenom.
     * @return TrueOrFalse
     */
    public Boolean getShowPasswordMessage(){
        return showPasswordMessage;
    } 
    
    /**
     * Checkar om det inskrivna lösenordet i första lösenords boxen
     * matchar med det andra lösenordet i den andra lösenords boxen.
     * Om båda _inte_ matchar skrivs lösenordsmeddelande ut.
     * @return JSF version 2.2 bug - Returnerar tom sträng
     */
    public String register()
    {
        if(!(password.equals(repeatPassword)))
        {
            showPasswordMessage = true; 
            return "";
        }
        
        try
        {
            controller.register(name, surname, ssn, email, username, password);
            registerSuccess = true;
        }
        catch(Exception e)
        {
            showMessage = true;
            registrationFailed = true;
            registerSuccess = false;
        }
        return "";
    }
    
    /**
     * Meddelar om registreringen lyckades eller inte.
     * @return true om registreringen lyckades, annars false
     */
    public Boolean getRegistrationFailed()
    {
        return registrationFailed;
    }
    
    /**
     * Anger om registreringen lyckades eller inte.
     * @param regFailed true om registreringen lyckades, annars false
     */
    public void setRegistrationFailed(Boolean regFailed)
    {
        this.registrationFailed = regFailed;
    }
}