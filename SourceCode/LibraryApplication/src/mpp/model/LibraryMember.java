package mpp.model;

import java.util.List;

public class LibraryMember extends Person {
	private String memberId;
	private List<CheckoutRecord> checkoutRecords;

	public List<CheckoutRecord> getCheckoutRecords() {
		return checkoutRecords;
	}

	public void setCheckoutRecord(List<CheckoutRecord> checkoutRecord) {
		this.checkoutRecords = checkoutRecords;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
