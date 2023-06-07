package mpp.model;

import java.time.LocalDate;

public class BookCopy {
	private int bookCopyID;
	private boolean isAvailable;
	private String borrowerId; // library member id
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

	private LocalDate dueDate;

	public boolean isAvailable() {
		return isAvailable;
	}

	public BookCopy(int bookCopyID, boolean isAvailable) {
		this.bookCopyID = bookCopyID;
		this.isAvailable = isAvailable;
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
}
