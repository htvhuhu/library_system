package mpp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mpp.controller.LoginController;
import mpp.exception.LoginException;
import mpp.model.Role;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1468040792651195783L;
	private JPanel contentPane;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private LoginController loginController = new LoginController();
	
	/*
	 * Constructor
	 */
	public LoginWindow() {
		setupUI();
		
		btnLogin.addActionListener(e -> login(tfUserName.getText().trim(), passwordField.getPassword()));
	}
	/**
	 * Login function
	 */
	private void login(String userName, char[] password) {
		if (userName.length() > 0 && password.length > 0) {
			try {
				Role role = loginController.login(userName, password);
			} catch (LoginException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
	}

	/**
	 * Create the frame.
	 */
	private void setupUI() {
		setSize(400, 250);
		setResizable(false);
		setTitle("Library Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		contentPane.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 10));
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		headerPanel.add(separator);
		
		JPanel bodyPanel = new JPanel();
		contentPane.add(bodyPanel, BorderLayout.CENTER);
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
		
		JPanel footerPanel = new JPanel();
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		
		btnLogin = new JButton("Login");
		
		footerPanel.add(btnLogin);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
