package mpp.model;

import java.util.List;

public class Book {
	public Book(String isbn, String title, List<Author> authors, int maxCheckoutLength, List<BookCopy> copies) {
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.maxCheckoutLength = maxCheckoutLength;
		this.copies = copies;
	}
	private String isbn;
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
	public List<BookCopy> getCopies() {
		return copies;
	}
	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}
	private String title;
	private List<Author> authors;
	private int maxCheckoutLength;
	private List<BookCopy> copies;
}
