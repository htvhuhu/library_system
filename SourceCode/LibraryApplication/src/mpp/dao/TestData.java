package mpp.dao;

import mpp.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestData {

	private LoginDao loginDao = (LoginDao)DataAccessFactory.getDataAccess(LoginDao.class);
	private BookDao bookDao = (BookDao) DataAccessFactory.getDataAccess(BookDao.class);
	private AuthorDao authorDao = (AuthorDao) DataAccessFactory.getDataAccess(AuthorDao.class);
	
	public static void main(String[] args) {
		TestData td = new TestData();
		td.createBookData();
//		td.libraryMemberData();
		td.createUserData();
		td.createAuthorData();
		
		List<Author> authors = AuthorDao.getInstance().getAllAuthors();
		for (Author a: authors) {
			System.out.println(a.getFirstName());
		}
	}
	
	private void createAuthorData() {
		// TODO Auto-generated method stub
		authorDao.saveAuthors(allAuthors);
	}
	///create books
	public void createBookData() {
		bookDao.saveBooks(allBooks);
	}
	
	public void createUserData() {
		loginDao.saveUsers(allUsers);
	}
	
	//create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);
		
	}
	
	///////////// DATA //////////////
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "", "A happy man is he.", UUID.randomUUID().toString()));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "", "A happy wife is she.", UUID.randomUUID().toString()));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "", "Thinker of thoughts.", UUID.randomUUID().toString()));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "", "Author of childrens' books.", UUID.randomUUID().toString()));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "", "Known for her clever style.", UUID.randomUUID().toString()));
		}
	};
	
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));		
		}
	};
	
	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz".toCharArray(), Role.LIBRARIAN));
			add(new User("102", "abc".toCharArray(), Role.ADMIN));
			add(new User("103", "111".toCharArray(), Role.BOTH));
		}
	};

}
