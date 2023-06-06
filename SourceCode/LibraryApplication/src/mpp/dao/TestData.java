package mpp.dao;

import mpp.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestData {
	List<User> loginUsers = new ArrayList<>();
	
	public static void main(String[] args) {
		

	}
	
	private void createLoginUsers() {
		loginUsers.add(new User("lib", "111", Role.LIBRARIAN));
		loginUsers.add(new User("admin", "222", Role.ADMIN));
		loginUsers.add(new User("both", "333", Role.BOTH));
	}

}
