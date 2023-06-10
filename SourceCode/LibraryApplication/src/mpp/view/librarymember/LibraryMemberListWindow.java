package mpp.view.librarymember;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mpp.constant.Message;
import mpp.controller.MemberController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class LibraryMemberListWindow {
	private JPanel mainPanel;
	private TablePanel tablePanel;
	private MemberController controller;
	private AddMemberDialog addMember;
	private JButton btnAddMember;
	private JPanel headerPanel;

	public LibraryMemberListWindow(Frame parent) {
		controller = new MemberController();
		addMember = new AddMemberDialog(this, parent);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getMembers());
		mainPanel.add(tablePanel, BorderLayout.CENTER);

		headerPanel = new JPanel();
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		
		btnAddMember = new JButton("Add new member");
		btnAddMember.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember.clearFields();
				addMember.setVisible(true);

			}
		});
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		headerPanel.add(btnAddMember);

		tablePanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				if (type == 1) { // delete
					if (confirmDelete() == JOptionPane.YES_OPTION) {
						saveMember(e, type);
						refreshList();
					}
				} else if (type == 2) { // update
					var selectedItem = controller.getMember(e.getMemberId());
					addMember.populateData(selectedItem);
					addMember.setVisible(true);
				}

			}
		});
	}
	
	private int confirmDelete() {
		return JOptionPane.showConfirmDialog(this.mainPanel, 
				Message.MSG_CONFIRM_DELETE, 
				Message.MSG_CONFIRM_TITLE,
				JOptionPane.YES_NO_OPTION);
		
	}

	public void saveMember(FormEvent e, int type) {
		controller.saveMember(e, type);
	}

	public void refreshList() {
		tablePanel.refresh();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
