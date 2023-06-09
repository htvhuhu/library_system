package mpp.model;

import java.io.Serializable;

public class Author extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String credentials;
	private String bio;
	private String authorId;
	
	public Author(String firstName, String lastName, String phoneNumber, Address address, String credentials, String bio, String authorId) {
        super(firstName, lastName, phoneNumber, address);
        this.credentials = credentials;
        this.bio = bio;
        this.authorId = authorId;
    }

	public String getCredentials() {
		return credentials;
	}

	public String getBio() {
		return bio;
	}

	public String getAuthorId() {
		return authorId;
	}
}
