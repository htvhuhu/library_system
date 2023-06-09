package mpp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import mpp.model.Author;
import mpp.model.Book;
import mpp.model.BookCopy;
import mpp.model.CheckoutRecord;
import mpp.model.LibraryMember;
import mpp.service.BookService;
import mpp.service.MemberService;
import mpp.service.ServiceFactory;

public class BookController {
	private BookService bookService = (BookService) ServiceFactory.getService(BookService.class);
	private MemberService memberService = new MemberService();
	
	
	public String  checkoutBook(String memberId, String isbn) {
        LibraryMember member = memberService.getMember(memberId);
        if (member == null) {
            return "Member not found.";
        }

        var copy = bookService.getAvailableCopy(isbn);
        if (copy == null) {
            return "Book not found or no available copies.";
        }

        CheckoutRecord record = new CheckoutRecord(copy, LocalDate.now(), LocalDate.now().plusDays(14), memberId);
        member.getCheckoutRecords().add(record);
        memberService.updateMember(member);
        
        copy.updateAvailable(false);
        bookService.updateBook(copy.getBook());

        return "Checkout successful! Due date: " + record.getDueDate();
    }
	

    public void printCheckoutRecord(String memberId) {
        LibraryMember member = memberService.getMember(memberId);
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
        return true;//today.isAfter(copy.getDueDate()) && copy.getBorrowerId() != null;
    }
    
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        bookService.addBook(isbn, title, maxCheckoutLength, authors);
        System.out.println("Book added successfully!");
    }
	
	public void addCopy(String isbn) {
		bookService.addCopyToBook(isbn);
        System.out.println("Copy added successfully!");
    }
	
	public Map<String, Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
