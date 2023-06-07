package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchBookWindow {
	private JPanel mainPanel;
	
	public SearchBookWindow() {
		setupUI();
	}
	
	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Search book");
		mainPanel.add(lblTitle);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
