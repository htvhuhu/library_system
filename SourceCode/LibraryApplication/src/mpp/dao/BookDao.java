package mpp.dao;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import mpp.model.Book;

public class BookDao extends DataAccess {
	private final StorageType storageType = StorageType.BOOK;

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        this.saveToFile(this.storageType, books);
        this.loadBooks();
    }

    public Book getBook(String isbn) {
        return this.books.get(isbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<Book>(this.books.values());
    }

    public void updateBook(Book book) {
        //addBook(book);
    }

    public void deleteBook(String isbn) {
        this.books.remove(isbn);
        this.saveToFile(this.storageType, books);
        this.loadBooks();
    }

    private void loadBooks() {
        this.books = (Map<String, Book>)this.readFromFile(this.storageType);
    }
    
    private Map<String, Book> books;
    
    public Map<String, Book> getBooks() {
		return books;
	}

	public static BookDao getInstance() {
		if(instance == null) {
			return new BookDao();
		}
		return instance;
	}
	private static BookDao instance;
    private BookDao(){
    	loadBooks();
    }
}
