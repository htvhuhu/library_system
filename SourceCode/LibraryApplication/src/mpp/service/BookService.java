package mpp.service;
import java.util.List;
import java.util.Map;

import mpp.dao.BookDao;
import mpp.dao.DataAccessFactory;
import mpp.dao.LoginDao;
import mpp.model.Author;
import mpp.model.Book;
import mpp.model.BookCopy;

public class BookService extends Service{
	private BookDao bookDao = (BookDao)DataAccessFactory.getDataAccess(BookDao.class);
	
    private List<Book> books;
    
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
    		 var bookCopies = isbn == null || isbn.isEmpty() ? 
    				 books.stream()
 	                .flatMap(book -> book.getCopies().stream())
    				 : 		books.stream()
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
    
    public Book searchBook(String isbn) {
    	try {
    		return isbn == null || isbn.isEmpty() ? 
    				books.stream()
                    .findFirst()
                    .orElse(null)
                    
    				:books.stream()
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
        
    }
    
    public void addCopyToBook(String isbn) {
        Book book = searchBook(isbn);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        int newCopyNumber = book.getCopies().size() + 1;
        book.getCopies().add(new BookCopy(newCopyNumber, true,book));
        
        //Update after add copy
        bookDao.addBook(book);
    }
    
    public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        var book = new Book(isbn, title, maxCheckoutLength, authors);
        books.add(book);
        bookDao.addBook(book);
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void deleteBook(String isbn)
	{
		var book = this.searchBook(isbn);
		if (book != null)
			books.remove(book);
	}
    
    public void updateBook(Book book)
	{		
    	deleteBook(book.getIsbn());
		addBook(book);
		bookDao.saveBooks(books);
	}
	
    public Map<String, Book> getAllBooks() {
        return bookDao.getAllBook();
    }
}

