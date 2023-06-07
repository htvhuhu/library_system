package mpp.controller;

import java.time.LocalDate;
import java.util.List;

import mpp.model.Author;
import mpp.model.BookCopy;
import mpp.model.CheckoutRecord;
import mpp.model.LibraryMember;
import mpp.service.BookService;
import mpp.service.ServiceFactory;

public class BookController {
	private BookService bookService = (BookService) ServiceFactory.getService(BookService.class);
	
	public void checkoutBook(String memberId, String isbn) {
        LibraryMember member = bookService.getMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        var copy = bookService.getAvailableCopy(isbn);
        if (copy == null) {
            System.out.println("Book not found or no available copies.");
            return;
        }

        CheckoutRecord record = new CheckoutRecord(copy, LocalDate.now(), LocalDate.now().plusDays(14));
        member.getCheckoutRecords().add(record);
        copy.setAvailable(false);

        System.out.println("Checkout successful! Due date: " + record.getDueDate());
    }
	

    public void printCheckoutRecord(String memberId) {
        LibraryMember member = bookService.getMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        List<CheckoutRecord> records = member.getCheckoutRecords();
        if (records.isEmpty()) {
            System.out.println("No checkout records found for member " + memberId);
            return;
        }

        System.out.printf("%-20s %-20s %-20s%n", "Copy Number", "Checkout Date", "Due Date");
        for (CheckoutRecord record : records) {
            System.out.printf("%-20s %-20s %-20s%n", 
                record.getBookCopy().getBookCopyID(), 
                record.getCheckoutDate(), 
                record.getDueDate());
        }
    }
	
    public List<BookCopy> searchBookCopies(String isbn) {
        return bookService.searchBookCopies(isbn);
    }

    public boolean isCopyOverdue(BookCopy copy) {
        LocalDate today = LocalDate.now();
        return today.isAfter(copy.getDueDate()) && copy.getBorrowerId() != null;
    }
    
	public void addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numberOfCopies) {
        bookService.addBook(isbn, title, authors, maxCheckoutLength, numberOfCopies);
        System.out.println("Book added successfully!");
    }
	
	public void addCopy(String isbn) {
		bookService.addCopyToBook(isbn);
        System.out.println("Copy added successfully!");
    }
}
