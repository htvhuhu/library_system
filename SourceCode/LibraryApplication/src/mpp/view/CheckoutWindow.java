package mpp.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class CheckoutWindow {
	private JPanel mainPanel;

	public CheckoutWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Checkout book");
		mainPanel.add(lblTitle);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
