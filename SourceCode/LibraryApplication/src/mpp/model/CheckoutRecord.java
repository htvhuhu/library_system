package mpp.model;
import java.time.LocalDate;

public class CheckoutRecord {
	private static final long serialVersionUID = 2007775174275387181L;
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
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
	public CheckoutRecord(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

    // constructor, getters, setters, etc.
}