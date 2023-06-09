package mpp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMember extends Person implements Serializable {
	private static final long serialVersionUID = 2039215174175387181L;
	private String memberId;
	private List<CheckoutRecord> checkoutRecords = new ArrayList<CheckoutRecord>();

	public LibraryMember(String memberId, String firstName, String lastName, String phoneNumber, Address address) {
        super(firstName, lastName, phoneNumber, address);
        this.memberId = memberId;
    }
	
	public List<CheckoutRecord> getCheckoutRecords() {
		if(checkoutRecords == null) {
			checkoutRecords = new ArrayList<CheckoutRecord>();
		}
		return checkoutRecords;
	}

	public void setCheckoutRecord(List<CheckoutRecord> checkoutRecords) {
		this.checkoutRecords = checkoutRecords;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
