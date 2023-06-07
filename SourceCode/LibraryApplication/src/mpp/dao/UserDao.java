package mpp.dao;

import mpp.model.User;

public class UserDao extends DataAccess {

	public User getAllUser() {
		readFromFile(StorageType.USER);
		
		return null;
	}
}
