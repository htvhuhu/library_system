package mpp.model;

import java.util.List;

public class Book {
	private String isbn;
	private String title;
	private List<Author> authors;
	private int maxCheckoutLength;
	private BookCopy[] copies;
}
