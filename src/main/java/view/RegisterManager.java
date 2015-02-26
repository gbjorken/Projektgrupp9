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
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setSurname(String surname){
        this.surname = surname;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setSsn(String ssn){
        this.ssn = ssn;
    }
    
    public String getSsn(){
        return ssn;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
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
    
    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }
    
    public String getRepeatPassword(){
        return repeatPassword;
    }
    
    public void setShowMessage(Boolean show){
        this.showMessage = show;
    }
    
    public Boolean getShowMessage(){
        return showMessage;
    }
    
    public Boolean getRegisterSuccess(){
        return registerSuccess;
    }
    
    public void setShowPasswordMessage(Boolean show){
        this.showPasswordMessage = show;
    }
    
    public Boolean getShowPasswordMessage(){
        return showPasswordMessage;
    } 
    
    public String register()
    {
        if(!(password.equals(repeatPassword)))
        {
            showPasswordMessage = true; 
            return "";
        }
        
        try
        {
            if(!controller.register(name, surname, ssn, email, username, password))
            {
                showMessage = true;
                registerSuccess = false;
                return "";
            }
            
            registerSuccess = true;
        }
        catch(Exception e)
        {
            
        }
        return "";
    }
}