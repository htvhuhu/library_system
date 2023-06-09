package mpp.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;

public class LoginWindow {

	private JPanel mainPanel;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblErrorMessage;
	
	private MainWindow mainWindow;
	/*
	 * Constructor
	 */
	public LoginWindow(MainWindow mainWindow) {
		setupUI();
		this.mainWindow = mainWindow;
		btnLogin.addActionListener(e -> mainWindow.login(tfUserName.getText().trim(), passwordField.getPassword()));
	}
	

	/**
	 * Create the frame.
	 */
	private void setupUI() {
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 10));
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		headerPanel.add(separator);
		
		JPanel bodyPanel = new JPanel();
		mainPanel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(20, 33, 100, 13);
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		bodyPanel.add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(150, 30, 200, 19);
		bodyPanel.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 57, 100, 13);
		bodyPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 54, 200, 19);
		bodyPanel.add(passwordField);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(new Color(255, 0, 0));
		lblErrorMessage.setBounds(150, 93, 273, 19);
		bodyPanel.add(lblErrorMessage);
		
		JPanel footerPanel = new JPanel();
		mainPanel.add(footerPanel, BorderLayout.SOUTH);
		
		btnLogin = new JButton("Login");
		
		footerPanel.add(btnLogin);
	}

	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
}
