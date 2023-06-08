package mpp.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mpp.model.LibraryMember;

public class LibraryMemberDao{
	
	public List<LibraryMember> getAllMembers()
	{
		
		Object obj = DataAccess.readFromFile(StorageType.MEMBER);
		
		
		if (obj != null)
		{
			LibraryMember[] persons = (LibraryMember[])obj;
			return Arrays.asList(persons) ;
		}
		
		return new ArrayList<LibraryMember>();
		
	}
	
	public  void saveAllMembers(List<LibraryMember> list)
	{
		
		DataAccess.saveToFile(StorageType.MEMBER, list.toArray(new LibraryMember[list.size()]));
		
	}

}
