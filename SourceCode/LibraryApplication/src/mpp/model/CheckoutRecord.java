package mpp.model;
import java.time.LocalDate;

public class CheckoutRecord {
	private static final long serialVersionUID = 2007775174275387181L;
	private String borrowerId; // library member id
	private LocalDate dueDate;
	private BookCopy bookCopy;
    private LocalDate checkoutDate;
    
    public BookCopy getBookCopy() {
		return bookCopy;
	}
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
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
	public CheckoutRecord(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate, String borrowerId) {
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.borrowerId = borrowerId;
	}
}