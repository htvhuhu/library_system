package mpp.service;

import java.util.Arrays;
import java.util.Map;

import mpp.constant.Message;
import mpp.dao.DataAccessFactory;
import mpp.dao.LoginDao;
import mpp.exception.LoginException;
import mpp.model.Role;
import mpp.model.User;

public class LoginService extends Service {
	private LoginDao loginDao = (LoginDao)DataAccessFactory.getDataAccess(LoginDao.class);
	
	public Role login(String username, char[] password) throws LoginException {
		Map<String, User> map = loginDao.getAllUser();
		// implementation
		if 	(!map.containsKey(username)) {
            throw new LoginException(String.format(Message.ERR_INVALID_USERNAME, username));
        }
        char[] originalPassword = map.get(username).getPassword();

        if (!Arrays.equals(originalPassword, password)) {
            throw new LoginException(Message.ERR_INVALID_USERNAME);
        }
        return map.get(username).getRole();
	}
}
