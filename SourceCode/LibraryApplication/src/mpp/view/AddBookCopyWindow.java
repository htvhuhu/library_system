package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddBookCopyWindow {
	private JPanel mainPanel;

	public AddBookCopyWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Add book copy");
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
}
