package mpp.dao;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void deleteBook(String isbn) {
        this.books.remove(isbn);
        this.saveToFile(this.storageType, books);
        this.loadBooks();
    }

    @SuppressWarnings("unchecked")
	private void loadBooks() {
    	try {
            this.books = (Map<String, Book>)this.readFromFile(this.storageType);
		} catch (Exception e) {
			this.books = new HashMap<String, Book>();
		}
    }
    
    private Map<String, Book> books;
    
    public Map<String, Book> getBooks() {
		return books;
	}

	static BookDao getInstance() {
		if(instance == null) {
			return new BookDao();
		}
		return instance;
	}
	private static BookDao instance;
    private BookDao(){
    	loadBooks();
    }
    
	@SuppressWarnings("unchecked")
	public Map<String, Book> getAllBook() {
		return (Map<String, Book>)readFromFile(StorageType.BOOK);
	}
	
	public void saveBooks(List<Book> bookList) {
		Map<String, Book> books = new HashMap<>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToFile(StorageType.BOOK, books);
		loadBooks();
	}
}
