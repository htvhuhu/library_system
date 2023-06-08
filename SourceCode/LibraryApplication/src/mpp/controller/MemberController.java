package mpp.controller;

import java.util.List;

import mpp.model.Address;
import mpp.model.LibraryMember;
import mpp.service.BookService;
import mpp.service.LibraryMemberService;
import mpp.service.ServiceFactory;
import mpp.view.librarymember.FormEvent;

public class MemberController {
	private LibraryMemberService service ;//= (LibraryMemberService) ServiceFactory.getService(LibraryMemberService.class);
	
	
	public MemberController()
	{
		service = new LibraryMemberService();
	}
	
	public void saveMember(FormEvent ev, int type)
	{
		if (type == 0)
		{
			LibraryMember member = new LibraryMember();
			
			member.setMemberId(ev.getNumber());
			member.setFirstName(ev.getFirstName());
			member.setLastName(ev.getLastName());
			member.setPhoneNumber(ev.getPhone());
			
			Address ad = new Address();
			ad.setStreet(ev.getStreet());
			ad.setCity(ev.getCity());
			ad.setState(ev.getState());
			ad.setZip(ev.getZip());
			member.setAddress(ad);
			
			service.addMember(member);
		}
		else if (type ==1) // delete
		{
			service.deleteMember(ev.getNumber());
		}
		else if (type ==2) // update
		{
			LibraryMember member = new LibraryMember();
			
			member.setMemberId(ev.getNumber());
			member.setFirstName(ev.getFirstName());
			member.setLastName(ev.getLastName());
			member.setPhoneNumber(ev.getPhone());
			
			Address ad = new Address();
			ad.setStreet(ev.getStreet());
			ad.setCity(ev.getCity());
			ad.setState(ev.getState());
			ad.setZip(ev.getZip());
			service.updateMember(member);
		}
		
		service.save();
	}
	
	public List<LibraryMember> getMembers()
	{
		return service.getMembers();
	}
	
	public LibraryMember getMember(String number)
	{
		return service.getMember(number);
	}
}
