package mpp.view.librarymember;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import mpp.model.LibraryMember;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AddMember extends JDialog{
	private FormPanel formPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddMember(LibraryMemberList parentPanel, Frame parent) {
		super(parent, "Preferences", false);
		
		setTitle("Add Library Member");
		formPanel = new FormPanel(this);
		getContentPane().add(formPanel, BorderLayout.CENTER);
		setLocationRelativeTo(parent);
		setSize(600, 500);
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				parentPanel.saveMember(e, type);
				parentPanel.refestList();
			}
		});
	}
	
	public void populateData(LibraryMember data)
	{
		formPanel.populatelate(data);
	}
	
	public void clearFields()
	{
		formPanel.clearField();
	}

}
