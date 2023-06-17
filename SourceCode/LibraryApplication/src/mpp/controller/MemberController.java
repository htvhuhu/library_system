package mpp.controller;

import java.util.List;

import mpp.model.Address;
import mpp.model.LibraryMember;
import mpp.service.MemberService;
import mpp.service.ServiceFactory;
import mpp.view.librarymember.FormEvent;

public class MemberController {
	private MemberService service = (MemberService) ServiceFactory.getService(MemberService.class);

	public void saveMember(FormEvent ev, int type) {
		if (type == 0) {
			Address address = new Address(ev.getStreet(), ev.getCity(), ev.getState(), ev.getZipCode());
			LibraryMember member = new LibraryMember(ev.getMemberId(), 
													 ev.getFirstName(),
													 ev.getLastName(),
													 ev.getPhoneNumber(),
													 address);
			service.addMember(member);
		} else if (type == 1) {
			 // delete
			service.deleteMember(ev.getMemberId());
		} else if (type == 2) {
			// update
			Address address = new Address(ev.getStreet(), ev.getCity(), ev.getState(), ev.getZipCode());
			LibraryMember member = new LibraryMember(ev.getMemberId(),
													 ev.getFirstName(),
													 ev.getLastName(),
													 ev.getPhoneNumber(),
													 address);
			service.updateMember(member);
		}

		service.save();
	}

	public List<LibraryMember> getMembers() {
		return service.getMembers();
	}

	public LibraryMember getMember(String number) {
		return service.getMember(number);
	}
}
