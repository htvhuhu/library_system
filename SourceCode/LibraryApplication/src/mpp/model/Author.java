package mpp.model;

import java.io.Serializable; 

public class Author extends Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String credentials;
	private String shortBio;

	

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getShortBio() {
		return shortBio;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
}
