package mpp.view.librarymember;
import java.util.EventObject;

public class FormEvent extends EventObject {
	private static final long serialVersionUID = 7508376620225487762L;
	private String memberId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private String zipCode;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(String number, String firstName, String lastName, String phone, String street, String city,
			String state, String zip, Object source) {
		super(source);

		this.memberId = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zip;
	}
	
	public FormEvent(String memberId, Object source) {
		super(source);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}
