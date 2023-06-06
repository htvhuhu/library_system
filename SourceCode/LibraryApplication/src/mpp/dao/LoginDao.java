package mpp.dao;

import mpp.model.User;

public class LoginDao extends DataAccess {

	public User getAllUser() {
		readFromFile(StorageType.USER);
		
		return null;
	}
}
