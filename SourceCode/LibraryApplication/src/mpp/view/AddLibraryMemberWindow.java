package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mpp.view.librarymember.LibraryMemberList;

public class AddLibraryMemberWindow {
	private JPanel mainPanel;
	private LibraryMemberList listMemberForm;

	public AddLibraryMemberWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Add library member");
		//listMemberForm = new LibraryMemberList();
		//mainPanel.add(listMemberForm);
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
