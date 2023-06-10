package mpp.dao;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import mpp.model.Author;

public class AuthorDao extends DataAccess {
	private final StorageType storageType = StorageType.AUTHOR;

    public void addAuthor(Author author) {
        authors.put(author.getAuthorId(), author);
        this.saveToFile(this.storageType, authors);
        this.loadAuthors();
    }

    public Author getAuthor(String isbn) {
        return this.authors.get(isbn);
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<Author>(this.authors.values());
    }

    public void updateAuthor(Author author) {
        //addAuthor(author);
    }

    public void deleteAuthor(String isbn) {
        this.authors.remove(isbn);
        this.saveToFile(this.storageType, authors);
        this.loadAuthors();
    }

    @SuppressWarnings("unchecked")
	private void loadAuthors() {
        this.authors = (Map<String, Author>)this.readFromFile(this.storageType);
    }
    
    private Map<String, Author> authors;
    
    public Map<String, Author> getAuthors() {
		return authors;
	}

	static AuthorDao getInstance() {
		if(instance == null) {
			return new AuthorDao();
		}
		return instance;
	}
	private static AuthorDao instance;
    private AuthorDao(){
    	loadAuthors();
    }
    
	@SuppressWarnings("unchecked")
	public Map<String, Author> getAllAuthorMap() {
		return (Map<String, Author>)readFromFile(StorageType.AUTHOR);
	}
	
	public void saveAuthors(List<Author> authorList) {
		Map<String, Author> authors = new HashMap<>();
		authorList.forEach(author -> authors.put(author.getAuthorId(), author));
		saveToFile(StorageType.AUTHOR, authors);
	}
}
