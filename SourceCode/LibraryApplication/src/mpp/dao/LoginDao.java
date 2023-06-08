package mpp.dao;

import java.util.List;
import java.util.*;
import mpp.model.User;

public class LoginDao extends DataAccess {

	@SuppressWarnings("unchecked")
	public Map<String, User> getAllUser() {
		return (Map<String, User>)readFromFile(StorageType.USER);
	}
	
	public void saveUsers(List<User> userList) {
		Map<String, User> users = new HashMap<>();
		userList.forEach(user -> users.put(user.getUserName(), user));
		saveToFile(StorageType.USER, users);
	}
}
