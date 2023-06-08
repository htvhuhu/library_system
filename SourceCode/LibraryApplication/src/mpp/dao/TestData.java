package mpp.dao;

import mpp.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestData {
	List<User> loginUsers = new ArrayList<>();
	
	public static void main(String[] args) {
		BookDao bookDao = BookDao.getInstance();
		Author author = new Author();
		author.setFirstName("Van");
		author.setLastName("Cao");
		
		List<Author> authors = new ArrayList<>();
		authors.add(author);

		Book book = new Book("isbn01", "Gone With The Wind", authors, 10, new ArrayList<>());

		bookDao.addBook(book);
		
		//Check books
		BookDao.getInstance().getAllBooks().forEach(b -> {
			System.out.println("Book: " + b.toString());
		});
	}
	
	private void createLoginUsers() {
		loginUsers.add(new User("lib", "111", Role.LIBRARIAN));
		loginUsers.add(new User("admin", "222", Role.ADMIN));
		loginUsers.add(new User("both", "333", Role.BOTH));
	}

}
