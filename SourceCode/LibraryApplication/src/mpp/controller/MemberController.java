package mpp.controller;

import java.util.List;

import mpp.model.Address;
import mpp.model.LibraryMember;
import mpp.service.MemberService;
import mpp.service.ServiceFactory;
import mpp.view.librarymember.FormEvent;

public class MemberController {
	private MemberService service = (MemberService) ServiceFactory.getService(MemberService.class);

	public MemberController() {
		service = new MemberService();
	}

	public void saveMember(FormEvent ev, int type) {
		if (type == 0) {
			Address address = new Address(ev.getStreet(), ev.getCity(), ev.getState(), ev.getZip());
			LibraryMember member = new LibraryMember(ev.getNumber(), ev.getFirstName(), ev.getLastName(), ev.getPhone(),
					address);

			service.addMember(member);
		} else if (type == 1) // delete
		{
			service.deleteMember(ev.getNumber());
		} else if (type == 2) // update
		{
			Address address = new Address(ev.getStreet(), ev.getCity(), ev.getState(), ev.getZip());
			LibraryMember member = new LibraryMember(ev.getNumber(), ev.getFirstName(), ev.getLastName(), ev.getPhone(),
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
