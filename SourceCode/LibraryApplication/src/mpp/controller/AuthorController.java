package mpp.controller;

import java.util.List;

import mpp.model.Author;
import mpp.service.AuthorService;
import mpp.service.ServiceFactory;

public class AuthorController {
	private AuthorService authorService = (AuthorService) ServiceFactory.getService(AuthorService.class);
	
	public List<Author> getAuthors() {
		return authorService.getAuthors();
	}
	
	public List<String> getAuthorNameList() {
		return authorService.getAuthorNameList();
	}
}
