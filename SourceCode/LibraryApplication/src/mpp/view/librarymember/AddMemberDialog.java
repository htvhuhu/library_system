package mpp.view.librarymember;

import java.awt.Frame;
import javax.swing.JDialog;

import mpp.constant.Message;
import mpp.model.LibraryMember;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AddMemberDialog extends JDialog {
	private FormListener formListener;

	private static final long serialVersionUID = 1L;
	private JTextField tfMemberId;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfPhoneNumber;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private boolean isUpdate = false;

	public AddMemberDialog(LibraryMemberListWindow parentPanel, Frame parent) {
		super(parent, "Preferences", false);

		setTitle("Add Library Member");
		setLocationRelativeTo(parent);
		setSize(400, 350);

		setupUI();

		this.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				parentPanel.saveMember(e, type);
				parentPanel.refreshList();
			}
		});
	}
	
	

	private void addMember() {
		String memberId = tfMemberId.getText();
		String firstName = tfFirstName.getText();
		String lastName = tfLastName.getText();
		String phone = tfPhoneNumber.getText();

		String street = tfStreet.getText();
		String city = tfCity.getText();
		String state = tfState.getText();
		String zipCode = tfZipCode.getText();
		
		if (memberId.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
				|| phone.isEmpty() || street.isEmpty() || city.isEmpty()
				|| state.isEmpty() || zipCode.isEmpty())
		{
			JOptionPane.showMessageDialog(null,
					Message.MSG_CONFIRM_REQUIRED_FIELDS,
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}

		FormEvent ev = new FormEvent(memberId, firstName, lastName, phone, street, city, state, zipCode, this);

		if (formListener != null) {
			if (isUpdate == false) {
				formListener.formEventOccurred(ev, 0);
			} else {
				isUpdate = false;
				formListener.formEventOccurred(ev, 2);
			}
		}
		closeDialog();
	}

	private void closeDialog() {
		this.setVisible(false);
	}

	private void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public void populateData(LibraryMember data) {
		tfMemberId.setText(data.getMemberId());
		tfFirstName.setText(data.getFirstName());
		tfLastName.setText(data.getLastName());
		tfPhoneNumber.setText(data.getPhoneNumber());
		
		if (data.getAddress() != null) {
			tfStreet.setText(data.getAddress().getStreet());
			tfCity.setText(data.getAddress().getCity());
			tfState.setText(data.getAddress().getState());
			tfZipCode.setText(data.getAddress().getZip());
			
		}
		isUpdate = true;
		tfMemberId.enable(false);
	}

	public void clearFields() {
		tfMemberId.setText("");
		tfFirstName.setText("");
		tfLastName.setText("");
		tfPhoneNumber.setText("");

		tfStreet.setText("");
		tfCity.setText("");
		tfState.setText("");
		tfZipCode.setText("");
		isUpdate = false;
		tfMemberId.enable(true);
	}
	
	private void setupUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Member Id");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipadx = 20;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		tfMemberId = new JTextField();
		GridBagConstraints gbc_tfMemberId = new GridBagConstraints();
		gbc_tfMemberId.anchor = GridBagConstraints.WEST;
		gbc_tfMemberId.insets = new Insets(20, 20, 5, 0);
		gbc_tfMemberId.gridx = 5;
		gbc_tfMemberId.gridy = 1;
		getContentPane().add(tfMemberId, gbc_tfMemberId);
		tfMemberId.setColumns(20);

		JLabel lblNewLabel_1 = new JLabel("First name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		tfFirstName = new JTextField();
		tfFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tfFirstName = new GridBagConstraints();
		gbc_tfFirstName.anchor = GridBagConstraints.WEST;
		gbc_tfFirstName.insets = new Insets(0, 20, 5, 0);
		gbc_tfFirstName.gridx = 5;
		gbc_tfFirstName.gridy = 2;
		getContentPane().add(tfFirstName, gbc_tfFirstName);
		tfFirstName.setColumns(20);

		JLabel lblNewLabel_2 = new JLabel("Last name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		tfLastName = new JTextField();
		GridBagConstraints gbc_tfLastName = new GridBagConstraints();
		gbc_tfLastName.anchor = GridBagConstraints.WEST;
		gbc_tfLastName.insets = new Insets(0, 20, 5, 0);
		gbc_tfLastName.gridx = 5;
		gbc_tfLastName.gridy = 3;
		getContentPane().add(tfLastName, gbc_tfLastName);
		tfLastName.setColumns(20);

		JLabel lblNewLabel_3 = new JLabel("Phone number");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		tfPhoneNumber = new JTextField();
		GridBagConstraints gbc_tfPhoneNumber = new GridBagConstraints();
		gbc_tfPhoneNumber.anchor = GridBagConstraints.WEST;
		gbc_tfPhoneNumber.insets = new Insets(0, 20, 5, 0);
		gbc_tfPhoneNumber.gridx = 5;
		gbc_tfPhoneNumber.gridy = 4;
		getContentPane().add(tfPhoneNumber, gbc_tfPhoneNumber);
		tfPhoneNumber.setColumns(20);
		
		tfPhoneNumber.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = tfPhoneNumber.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	tfPhoneNumber.setEditable(true);
	               //label.setText("");
	            } else {
	            	tfPhoneNumber.setEditable(false);
	               //label.setText("* Enter only numeric digits(0-9)");
	            }
	         }
	      });

		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 5;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		tfStreet = new JTextField();
		GridBagConstraints gbc_tfStreet = new GridBagConstraints();
		gbc_tfStreet.anchor = GridBagConstraints.WEST;
		gbc_tfStreet.insets = new Insets(0, 20, 5, 0);
		gbc_tfStreet.gridx = 5;
		gbc_tfStreet.gridy = 5;
		getContentPane().add(tfStreet, gbc_tfStreet);
		tfStreet.setColumns(20);

		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 6;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		tfCity = new JTextField();
		GridBagConstraints gbc_tfCity = new GridBagConstraints();
		gbc_tfCity.anchor = GridBagConstraints.WEST;
		gbc_tfCity.insets = new Insets(0, 20, 5, 0);
		gbc_tfCity.gridx = 5;
		gbc_tfCity.gridy = 6;
		getContentPane().add(tfCity, gbc_tfCity);
		tfCity.setColumns(20);

		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 7;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);

		tfState = new JTextField();
		GridBagConstraints gbc_tfState = new GridBagConstraints();
		gbc_tfState.anchor = GridBagConstraints.WEST;
		gbc_tfState.insets = new Insets(0, 20, 5, 0);
		gbc_tfState.gridx = 5;
		gbc_tfState.gridy = 7;
		getContentPane().add(tfState, gbc_tfState);
		tfState.setColumns(20);

		JLabel lblNewLabel_7 = new JLabel("Zip code");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_7.gridx = 3;
		gbc_lblNewLabel_7.gridy = 8;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);

		tfZipCode = new JTextField();
		GridBagConstraints gbc_tfZipCode = new GridBagConstraints();
		gbc_tfZipCode.anchor = GridBagConstraints.WEST;
		gbc_tfZipCode.insets = new Insets(0, 20, 5, 0);
		gbc_tfZipCode.gridx = 5;
		gbc_tfZipCode.gridy = 8;
		getContentPane().add(tfZipCode, gbc_tfZipCode);
		tfZipCode.setColumns(20);
		
		
		tfZipCode.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = tfZipCode.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	tfZipCode.setEditable(true);
	               
	            } else {
	            	tfZipCode.setEditable(false);
	               
	            }
	         }
	      });

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 20, 5, 0);
		gbc_panel.gridx = 5;
		gbc_panel.gridy = 10;
		getContentPane().add(panel, gbc_panel);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
		
				addMember();
			}
		});
		panel.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		panel.add(btnCancel);
	}

}
