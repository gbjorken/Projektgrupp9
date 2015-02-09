package controller;

import Integration.LoginDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LoginFacade 
{
    @EJB
    private LoginDAO loginDAO;
    
    public Boolean loginAsApplicant(String username, String password)
    {
        return loginDAO.loginAsApplicant(username, password);
    }
    
    public Boolean loginAsRecruiter(String username, String password)
    {
        return loginDAO.loginAsRecruiter(username, password);
    }
}
