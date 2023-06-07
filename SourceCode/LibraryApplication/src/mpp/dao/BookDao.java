package mpp.dao;

import java.util.HashMap;
import java.util.List;

import mpp.model.Book;

public class BookDao extends DataAccess {
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) DataAccess.readFromFile(StorageType.BOOK);
	}
	
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		DataAccess.saveToFile(StorageType.BOOK, books);
	}

}
