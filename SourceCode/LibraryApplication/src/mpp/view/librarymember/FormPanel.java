package mpp.view.librarymember;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;

import mpp.model.LibraryMember;


public class FormPanel extends JPanel{

	private boolean isUpdate = false;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel numberLabel;
	private JLabel phoneLabel;
	private JLabel occupationLabel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneField;
	private FormListener formListener;
	
	
	private JLabel streetLabel;
	private JLabel cityLabel;
	private JLabel stateLabel;
	private JLabel zipLabel;
	
	private JTextField streetField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zipField;
	
	private JTextField numberField;
	private JTextField occupationField;
	private JButton okBtn;
	private JButton cancelBtn;
	
	private JDialog parent;
	//private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel(JDialog _parent) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		numberLabel = new JLabel("Number: ");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		phoneLabel = new JLabel("Phone: ");
		
		parent = _parent;
		streetLabel = new JLabel("Street: ");
		cityLabel = new JLabel("Street: ");
		stateLabel = new JLabel("State: ");
		zipLabel = new JLabel("Zip Code: ");
		
		
		//occupationLabel = new JLabel("Occupation: ");
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		numberField = new JTextField(10);
		phoneField = new JTextField(10);
		
		streetField = new JTextField(50);
		cityField = new JTextField(50);
		stateField = new JTextField(50);
		zipField = new JTextField(10);
		
		/*occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");*/
		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		
		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		//nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		//nameLabel.setLabelFor(nameField);
		
		/*maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		genderGroup = new ButtonGroup();
		
		maleRadio.setSelected(true);
		
		// Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		// Set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);*/

		

		// Set up list box
		
		//ageList.setModel(ageModel);

	/*	ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		// Set up combo box.
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);*/
		

		

		Border innerBorder = BorderFactory.createTitledBorder("Add Member");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				parent.setVisible(false);
			}
		});
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String number = numberField.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String phone = phoneField.getText();
				
				String street = streetField.getText();
				String state = stateField.getText();
				String zip = zipField.getText();
				String city = cityField.getText();			
				
			

				FormEvent ev = new FormEvent(number, firstName,lastName,
						phone, street, city, state, zip, this);

				if (formListener != null) {
					if (isUpdate == false)
					{
						formListener.formEventOccurred(ev, 0);
					}
					else
					{
						isUpdate = false;
						formListener.formEventOccurred(ev, 2);
					}
				}
				parent.setVisible(false);
			}
		});
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	public void populatelate(LibraryMember data) {
		numberField.setText(data.getMemberId());
		firstNameField.setText(data.getFirstName());
		lastNameField.setText(data.getLastName());
		phoneField.setText(data.getPhoneNumber());
		
		streetField.setText(data.getAddress().getStreet());
		stateField.setText(data.getAddress().getState());
		zipField.setText(data.getAddress().getZip());
		cityField.setText(data.getAddress().getCity());
		isUpdate = true;
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(numberLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(numberField, gc);
		
		// ////////// First row ///////////////////////////////////
				gc.gridy++;
			

				gc.weightx = 1;
				gc.weighty = 0.1;

				gc.gridx = 0;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.LINE_END;
				gc.insets = new Insets(0, 0, 0, 5);
				add(lastNameLabel, gc);

				gc.gridx = 1;
				//gc.gridy = 0;
				gc.insets = new Insets(0, 0, 0, 0);
				gc.anchor = GridBagConstraints.LINE_START;
				add(lastNameField, gc);
				
				// ////////// First row ///////////////////////////////////
				gc.gridy++;
			

				gc.weightx = 1;
				gc.weighty = 0.1;

				gc.gridx = 0;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.LINE_END;
				gc.insets = new Insets(0, 0, 0, 5);
				add(firstNameLabel, gc);

				gc.gridx = 1;
				//gc.gridy = 0;
				gc.insets = new Insets(0, 0, 0, 0);
				gc.anchor = GridBagConstraints.LINE_START;
				add(firstNameField, gc);

		// //////////Second row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(phoneLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(phoneField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(streetLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(streetField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(stateLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(stateField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(cityLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cityField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(zipLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(zipField, gc);
		
		// //////////Next row ///////////////////////////////////

		/*gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);*/
		
		// //////////Next row ///////////////////////////////////

		/*gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);*/

		// //////////Next row ///////////////////////////////////

		
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(okBtn, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cancelBtn, gc);
	}

	
	
}



