package mpp.dao;

import mpp.model.User;

public class LoginDao extends DataAccess {

	public User getUser(String userName) {
		readFromFile(StorageType.USER);
		
		return null;
	}
}
