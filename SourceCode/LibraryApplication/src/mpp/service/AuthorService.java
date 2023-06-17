package mpp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mpp.dao.AuthorDao;
import mpp.dao.DataAccessFactory;
import mpp.model.Author;

public class AuthorService extends Service {
	
	private AuthorDao authorDao = (AuthorDao) DataAccessFactory.getDataAccess(AuthorDao.class);

	// package level
	AuthorService() {
	}
	
	public List<Author> getAuthors() {
		Map<String, Author> authorMap = authorDao.getAllAuthorMap();
		List<Author> authors = new ArrayList<>();
		for (Entry<String, Author> entry: authorMap.entrySet()) {
			authors.add(entry.getValue());
		}
		return authors;
	}
	
	public List<String> getAuthorNameList() {
		Map<String, Author> authorMap = authorDao.getAllAuthorMap();
		List<String> authors = new ArrayList<>();
		for (Entry<String, Author> entry: authorMap.entrySet()) {
			authors.add(entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
		}
		return authors;
	}

	//TODO: Temp JList cannot hold Object, only hold String
	public Author getAuthorByName(String fullName) {
		return getAuthors().stream().filter( 
				author -> fullName.equals(author.getFirstName() + " " + author.getLastName())).findFirst().get();
	}
}
