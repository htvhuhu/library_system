package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddBookWindow {
	private JPanel mainPanel;

	public AddBookWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Add book");
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
