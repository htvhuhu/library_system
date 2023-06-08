package mpp.service;

import java.util.ArrayList;
import java.util.List;

import mpp.dao.LibraryMemberDao;
import mpp.model.LibraryMember;

public class LibraryMemberService{
	private List<LibraryMember> members;
	
	private LibraryMemberDao dao;
	
	public LibraryMemberService()
	{
		dao = new LibraryMemberDao();
		//members = dao.getAllMembers();
		members = new ArrayList<>();
	}
	
	public void addMember(LibraryMember member)
	{
		
		members.add(member);
		
	}
	
	public void deleteMember(String number)
	{
		LibraryMember member = getMember(number);
		members.remove(member);
	}
	
	public void updateMember(LibraryMember member)
	{		
		deleteMember(member.getMemberId());
		addMember(member);
	}
	
	public void save()
	{
		dao.saveAllMembers(members);
	}
	
	public LibraryMember getMember( String number )
	{
		return members.stream()
                .filter(member -> member.getMemberId().equals(number))
                .findFirst()
                .orElse(null);
	}

	public List<LibraryMember> getMembers() {
		return members;
	}

	public void setMembers(List<LibraryMember> members) {
		this.members = members;
	}
}
