package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddLibraryMemberWindow {
	private JPanel mainPanel;

	public AddLibraryMemberWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Add library member");
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
