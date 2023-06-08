package mpp.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mpp.model.LibraryMember;

public class MemberDao extends DataAccess{
	public ArrayList<LibraryMember> getAllMembers()
	{
		
		//List<LibraryMember> result = new ArrayList<>();
		Object obj = readFromFile(StorageType.MEMBER);
		
		
		if (obj != null)
		{
			LibraryMember[] persons = (LibraryMember[])obj;
			return new ArrayList<LibraryMember>(Arrays.asList(persons));
			//return Arrays.as(persons) ;
		}
		
		return new ArrayList<LibraryMember>();
		
	}
	
	public  void saveAllMembers(List<LibraryMember> list)
	{
		
		saveToFile(StorageType.MEMBER, list.toArray(new LibraryMember[list.size()]));
		
	}
	
}
