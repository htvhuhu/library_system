package mpp.model;

import java.io.Serializable;

public class BookCopy implements Serializable{
	private static final long serialVersionUID = 2009215174275309181L;
	private int bookCopyID;
	private Book book;
	private boolean isAvailable;
	
	public BookCopy(int bookCopyID, boolean isAvailable, Book book) {
		this.bookCopyID = bookCopyID;
		this.isAvailable = isAvailable;
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}

	public void updateAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public int getBookCopyID() {
		return bookCopyID;
	}

}
