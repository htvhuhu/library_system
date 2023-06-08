package mpp.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6435532191628144729L;
	private String userName;
	private char[] password;
	private Role role;
	
	public User(String userName, char[] password, Role role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public char[] getPassword() {
		return password;
	}
	public Role getRole() {
		return role;
	}
}
