package mpp.model;

import java.io.Serializable;

public class Author extends Person implements Serializable {
	private static final long serialVersionUID = -582114718380722541L;
	private String bio;
	private String authorId;
	
	public Author(String firstName, String lastName, String phoneNumber, Address address, String bio, String authorId) {
        super(firstName, lastName, phoneNumber, address);
        this.bio = bio;
        this.authorId = authorId;
    }

	public String getBio() {
		return bio;
	}

	public String getAuthorId() {
		return authorId;
	}
}
