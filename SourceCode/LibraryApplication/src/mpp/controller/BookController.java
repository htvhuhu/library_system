package mpp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
	private MemberService memberService = (MemberService) ServiceFactory.getService(MemberService.class);

	public List<LibraryMember> getAllMembers() {
		return memberService.getMembers();
	}
	
	public List<LibraryMember> getMember(String memberId) {
		 var result = memberService.getMember(memberId) == null ? new ArrayList<LibraryMember>() : new ArrayList<>(Arrays.asList(memberService.getMember(memberId)));
		 return result;
	}
	
	public String  checkoutBook(String memberId, String isbn) {
		var members = this.getMember(memberId);
		if(members == null || members.size() == 0)
		{
			return "Member not found.";
		}
        LibraryMember member = this.getMember(memberId).stream().findFirst().orElse(null);
        if (member == null) {
            return "Member not found.";
        }

        var copy = bookService.getAvailableCopy(isbn);
        if (copy == null) {
            return "Book not found or no available copies.";
        }

        

        copy.updateAvailable(false);
        bookService.updateBook(copy.getBook());

        CheckoutRecord record = new CheckoutRecord(copy, LocalDate.now(), LocalDate.now().plusDays(copy.getBook().getMaxCheckoutLength()), memberId);
        member.getCheckoutRecords().add(record);
        memberService.updateMember(member);
        
        return "Checkout successful! Due date: " + record.getDueDate();
    }
	

    public List<CheckoutRecord> getCheckoutRecord(String memberId) {
        LibraryMember member = memberService.getMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return null;
        }

        List<CheckoutRecord> records = member.getCheckoutRecords();
        return records;
        /*
       
        }*/
    }
    
    public void PrintCheckoutRecord(String memberId, List<BookCopy> bookCopies, List<CheckoutRecord> checkoutRecords) {
    	 if (checkoutRecords.isEmpty()) {
             System.out.println("No checkout records found for member " + memberId);
             return;
         }

         System.out.printf("\n%-50s %-20s %-20s %-50s %-20s %-20s%n", "Title", "ISBN", "BookCopyID", "Borrower", "Checkout Date", "Due Date");
         for (BookCopy bookCopy: bookCopies) {
             var book = bookCopy.getBook();
             CheckoutRecord checkoutRecord = checkoutRecords.stream()
             		.filter(cr -> cr.getBookCopy().getBook().getIsbn().compareToIgnoreCase(bookCopy.getBook().getIsbn())==0
             		&& cr.getBookCopy().getBookCopyID() ==  (bookCopy.getBookCopyID()))
             		.sorted((cr1, cr2)->cr2.getCheckoutDate().compareTo(cr1.getCheckoutDate()))
             		.findFirst().orElse(null);
        	 
             System.out.printf("%-50s %-20s %-20s %-50s %-20s %-20s%n", 
            		 book.getTitle(),
            		 book.getIsbn(),
            		 checkoutRecord.getBookCopy().getBookCopyID(), 
            		 memberService.getMember(checkoutRecord.getBorrowerId()) == null ? "N/A" : memberService.getMember(checkoutRecord.getBorrowerId()).toString(),
            		 checkoutRecord.getCheckoutDate(), 
            		 checkoutRecord.getDueDate());
         }
    }
	
    public List<BookCopy> searchBookCopies(String isbn) {
        return bookService.searchBookCopies(isbn);
    }
    
    public List<BookCopy> getAllBookCopies() {
        return bookService.searchBookCopies(null);
    }
    
    public Book searchBook(String isbn) {
        return bookService.searchBook(isbn);
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
