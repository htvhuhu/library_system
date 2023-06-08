package mpp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class BookCopy implements Serializable{
	private static final long serialVersionUID = 2009215174275309181L;
	private int bookCopyID;
	private boolean isAvailable;
	private String borrowerId; // library member id
	private LocalDate dueDate;

	public String getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getBookCopyID() {
		return bookCopyID;
	}

	public void setBookCopyID(int bookCopyID) {
		this.bookCopyID = bookCopyID;
		
	}

	public BookCopy(int bookCopyID, boolean isAvailable) {
		this.bookCopyID = bookCopyID;
		this.isAvailable = isAvailable;
	}

}
