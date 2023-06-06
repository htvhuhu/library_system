package mpp.controller;

import mpp.service.BookService;
import mpp.service.ServiceFactory;

public class BookController {
	private BookService bookService = (BookService) ServiceFactory.getService(BookService.class);
	
	public void addBook() {
		bookService.addBook();
	}
}
