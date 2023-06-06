package mpp.service;

import mpp.dao.DataAccessFactory;
import mpp.dao.LoginDao;
import mpp.model.Role;
import mpp.model.User;

public class LoginService extends Service {
	private LoginDao loginDao = (LoginDao)DataAccessFactory.getDataAccess(LoginDao.class);
	
	public Role login(String username, char[] password) {
		User user = loginDao.getUser(username);
		// implementation
		
		return user.getRole();
	}
}
