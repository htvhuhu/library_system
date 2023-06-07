package mpp.service;

import mpp.dao.DataAccessFactory;
import mpp.dao.UserDao;
import mpp.model.Role;
import mpp.model.User;

public class UserService extends Service {
	private UserDao userDao = (UserDao)DataAccessFactory.getDataAccess(UserDao.class);
	
	public Role login(String username, char[] password) {
		User user = userDao.getAllUser();
		// implementation
		
		return user.getRole();
	}
}
