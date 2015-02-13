package controller;

import Integration.LoginAndRegisterDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Controller 
{
    @EJB
    private LoginAndRegisterDAO loginAndRegisterDAO;
    
    public Boolean loginAsApplicant(String username, String password)
    {
        return loginAndRegisterDAO.loginAsApplicant(username, password);
    }
    
    public Boolean loginAsRecruiter(String username, String password)
    {
        return loginAndRegisterDAO.loginAsRecruiter(username, password);
    }
    
    public Boolean register(String name, String surname, String ssn, String email, String username, String password)
    {
        return loginAndRegisterDAO.register(name, surname, ssn, email, username, password);
    }        
}
