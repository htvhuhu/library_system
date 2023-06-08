package mpp.model;

import java.io.Serializable;

public class Author extends Person implements Serializable {
	private static final long serialVersionUID = -582114718380722541L;
	private String credentials;
	private String bio;
	
	public Author(String firstName, String lastName, String phoneNumber, Address address, String credentials, String bio) {
        super(firstName, lastName, phoneNumber, address);
        this.credentials = credentials;
        this.bio = bio;
    }

	public String getCredentials() {
		return credentials;
	}

	public String getBio() {
		return bio;
	}
	
}
