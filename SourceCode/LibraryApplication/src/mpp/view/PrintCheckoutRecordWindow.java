package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrintCheckoutRecordWindow {
	private JPanel mainPanel;

	public PrintCheckoutRecordWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Print checkout record");
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
