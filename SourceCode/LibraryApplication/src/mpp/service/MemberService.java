package mpp.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import mpp.dao.MemberDao;
import mpp.model.LibraryMember;

public class MemberService extends Service{
	
	public static ArrayList<LibraryMember> members;
	
	private MemberDao dao;
	
	public MemberService()
	{
		dao = new MemberDao();
		if (members == null)
			members = dao.getAllMembers();
		//members = new ArrayList<>(); 
	}
	
	public void addMember(LibraryMember member)
	{
		
		members.add(member);
		
	}
	
	public void deleteMember(String number)
	{
		LibraryMember member = getMember(number);
		if (member != null)
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
		/*return members.stream()
                .filter(member -> member.getMemberId().equals(number))
                .findFirst()
                .orElse(null);*/
		for(LibraryMember mem : members)
		{
			if (mem.getMemberId().equals(number) )
				return mem;
		}
		return null;
	}

	public List<LibraryMember> getMembers() {
		return members;
	}

	/*public void setMembers(List<LibraryMember> members) {
		members = members;
	}*/
}
