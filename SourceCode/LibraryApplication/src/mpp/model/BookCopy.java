package mpp.model;

public class BookCopy {
	private int bookCopyID;
	private boolean isAvailable;

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
