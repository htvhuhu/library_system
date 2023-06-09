package mpp.service;

import java.util.List;
import java.util.ArrayList;

import mpp.dao.DataAccessFactory;
import mpp.dao.MemberDao;
import mpp.model.LibraryMember;

public class MemberService extends Service {
	private MemberDao memberDao = (MemberDao)DataAccessFactory.getDataAccess(MemberDao.class);
	
	public static ArrayList<LibraryMember> members;

	public MemberService() {
		if (members == null)
			members = memberDao.getAllMembers();
	}

	public void addMember(LibraryMember member) {
		members.add(member);
	}

	public void deleteMember(String memberId) {
		LibraryMember member = getMember(memberId);
		if (member != null)
			members.remove(member);
	}

	public void updateMember(LibraryMember member) {
		deleteMember(member.getMemberId());
		addMember(member);
		save();
	}

	public void save() {
		memberDao.saveAllMembers(members);
	}

	public LibraryMember getMember(String memberId) {
		for (LibraryMember mem : members) {
			if (mem.getMemberId().equals(memberId))
				return mem;
		}
		return null;
	}

	public List<LibraryMember> getMembers() {
		return members;
	}

}
