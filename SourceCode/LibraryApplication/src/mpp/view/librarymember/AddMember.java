package mpp.view.librarymember;

import java.awt.BorderLayout;

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

	public AddMember(LibraryMemberList parent) {
		super(parent, "Preferences", false);
		
		setTitle("Add Library Member");
		formPanel = new FormPanel(this);
		getContentPane().add(formPanel, BorderLayout.CENTER);
		setLocationRelativeTo(parent);
		setSize(600, 500);
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				parent.saveMember(e, type);
				parent.refestList();
			}
		});
	}
	
	public void populateData(LibraryMember data)
	{
		formPanel.populatelate(data);
	}

}
