package mpp.view.librarymember;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import mpp.model.LibraryMember;

public class LibraryMemberTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<LibraryMember> db;
	
	private String[] colNames = {"ID", "Name", "First Name", "Last Name", "Phone Number", "Address"};
	
	public LibraryMemberTableModel() {
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}



	public void setData(List<LibraryMember> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		LibraryMember member = db.get(row);
		
		switch(col) {
		case 0:
			return member.getMemberId();
		case 1:
			return member.getLastName();
		case 2:
			return member.getFirstName();
		case 3:
			return member.getPhoneNumber();
		case 4:
			return member.getAddress();
		
		}
		
		return null;
	}

}
