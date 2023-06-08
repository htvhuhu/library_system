package mpp.controller;

import mpp.exception.LoginException;
import mpp.model.Role;
import mpp.service.LoginService;
import mpp.service.ServiceFactory;

public class LoginController {
	private LoginService loginService = (LoginService) ServiceFactory.getService(LoginService.class);
	
	public Role login(String username, char[] password) throws LoginException {
        return loginService.login(username, password);
    }
}
