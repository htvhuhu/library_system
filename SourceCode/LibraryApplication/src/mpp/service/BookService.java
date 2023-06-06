package mpp.service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mpp.model.Author;
import mpp.model.Book;
import mpp.model.BookCopy;
import mpp.model.LibraryMember;

public class BookService extends Service{
    private List<LibraryMember> members;
    private List<Book> books;

    public LibraryMember getMember(String memberId) {
        return members.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    public BookCopy getAvailableCopy(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .flatMap(book -> book.getCopies().stream())
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElse(null);
    }
    
    public Book getBook(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void addCopyToBook(String isbn) {
        Book book = getBook(isbn);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        int newCopyNumber = book.getCopies().size() + 1;
        book.getCopies().add(new BookCopy(newCopyNumber, true));
    }
    
    public void addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numberOfCopies) {
        List<BookCopy> copies = IntStream.rangeClosed(1, numberOfCopies)
            .mapToObj(i -> new BookCopy(i, true))
            .collect(Collectors.toList());
        books.add(new Book(isbn, title, authors, maxCheckoutLength,copies));
    }
}

