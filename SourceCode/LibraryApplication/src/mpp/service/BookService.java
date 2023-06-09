package mpp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mpp.dao.BookDao;
import mpp.dao.MemberDao;
import mpp.model.Author;
import mpp.model.Book;
import mpp.model.BookCopy;
import mpp.model.LibraryMember;

public class BookService extends Service{
	
    private List<Book> books;
    private BookDao bookDao = BookDao.getInstance();
    //private BookDao bookDao = BookDao.getInstance();

    
    public BookService() {
    	this.books = this.bookDao.getAllBooks();
    	System.out.println("Created Book Service!");
    }

    public BookCopy getAvailableCopy(String isbn) {
    	try {
    		var bookCopies = this.searchBookCopies(isbn);
            if (bookCopies == null) {
                return null;
            }
        	return bookCopies.stream()
                    .filter(BookCopy::isAvailable)
                    .findFirst()
                    .orElse(null);
		} catch (Exception e) {
			return null;
		}
    	
    }
    
    public List<BookCopy> searchBookCopies(String isbn) {
    	try {
    		 var bookCopies = books.stream()
    	                .filter(book -> book.getIsbn().equals(isbn))
    	                .flatMap(book -> book.getCopies().stream());
    	        if (bookCopies == null) {
    	            return null;
    	        }
    	        return bookCopies.toList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
       
    }
    
    public Book getBook(String isbn) {
    	try {
    		return books.stream()
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
        
    }
    
    public void addCopyToBook(String isbn) {
        Book book = getBook(isbn);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        int newCopyNumber = book.getCopies().size() + 1;
        book.getCopies().add(new BookCopy(newCopyNumber, true,book));
    }
    
    public void addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numberOfCopies) {
        var book = new Book(isbn, title, authors, maxCheckoutLength,null);
    	List<BookCopy> copies = IntStream.rangeClosed(1, numberOfCopies)
            .mapToObj(i -> new BookCopy(i, true, book))
            .collect(Collectors.toList());
    	book.setCopies(copies);
        books.add(book);
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void deleteBook(String isbn)
	{
		var book = this.getBook(isbn);
		if (book != null)
			books.remove(book);
	}
    
    public void updateBook(Book book)
	{		
    	deleteBook(book.getIsbn());
		addBook(book);
	}
	
}

