package mpp.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mpp.model.LibraryMember;

public class MemberDao extends DataAccess {
	private static MemberDao instance;

	public static MemberDao getInstance() {
		if (instance == null) {
			return new MemberDao();
		}
		return instance;
	}
	
	public ArrayList<LibraryMember> getAllMembers() {
		Object obj = readFromFile(StorageType.MEMBER);

		if (obj != null) {
			LibraryMember[] persons = (LibraryMember[]) obj;
			return new ArrayList<LibraryMember>(Arrays.asList(persons));
		}

		return new ArrayList<LibraryMember>();

	}

	public void saveAllMembers(List<LibraryMember> list) {

		saveToFile(StorageType.MEMBER, list.toArray(new LibraryMember[list.size()]));

	}

}
