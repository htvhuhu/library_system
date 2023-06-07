package mpp.dao;

import mpp.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TestData {
	List<User> loginUsers = new ArrayList<>();
	
	public static void main(String[] args) {
		List<User> users = new ArrayList<>();
		User user1 = new User("01", "lib", "111", Role.LIBRARIAN);
		User user2 = new User("02", "admin", "222", Role.ADMIN);
		User user3 = new User("03", "admin", "222", Role.ADMIN);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		UserDao userDao = new UserDao();
		userDao.loadUserMap(users);
		
		HashMap<String, User> userMap = userDao.readUsersMap();
		System.out.println("Check userMap");
		if (!userMap.isEmpty()) {
			for (Entry<String, User> entry: userMap.entrySet()) {
				System.out.println("User: " + entry.getKey() + "  " + entry.getValue());
			}
		}
		
//		BookDao bookDao = new BookDao();
//		HashMap<String, Book> bookMap = bookDao.readBooksMap();
//		System.out.println("Check bookMap");
//		if (!bookMap.isEmpty()) {
//			for (Entry<String, Book> entry: bookMap.entrySet()) {
//				System.out.println("Book: " + entry.getKey() + "  " + entry.getValue());
//			}
//		}
	}
	
//	private void createLoginUsers() {
//		loginUsers.add(new User("lib", "111", Role.LIBRARIAN));
//		loginUsers.add(new User("admin", "222", Role.ADMIN));
//		loginUsers.add(new User("admin", "222", Role.ADMIN));
//	}

}
