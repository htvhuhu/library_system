package mpp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
	private static final long serialVersionUID = 7253471815232599121L;
	private String isbn;
	private String title;
	private List<Author> authors;
	private int maxCheckoutLength;
	private List<BookCopy> copies;
	
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.maxCheckoutLength = maxCheckoutLength;
		
		BookCopy copy = new BookCopy(1, true, this);
		this.copies = new ArrayList<>();
		this.copies.add(copy);
	}
	public String getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	public List<BookCopy> getCopies() {
		return copies;
	}
	
}
