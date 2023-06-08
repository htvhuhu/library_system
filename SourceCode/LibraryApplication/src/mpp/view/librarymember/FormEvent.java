package mpp.view.librarymember;
import java.util.EventObject;

public class FormEvent extends EventObject {

	private String number;
	private String firstName;
	private String lastName;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zip;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(String number, String firstName, String lastName, String phone, String street, String city,
			String state, String zip, Object source) {
		super(source);

		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public FormEvent(String number, Object source) {
		super(source);

		this.number = number;
		
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	

}
