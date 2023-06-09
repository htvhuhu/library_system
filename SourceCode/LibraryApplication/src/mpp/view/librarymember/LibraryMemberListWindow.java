package mpp.view.librarymember;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPanel;
import mpp.controller.MemberController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

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
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember.clearFields();
				addMember.setVisible(true);

			}
		});
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerPanel.add(btnAddMember);

		tablePanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				if (type == 1) { // add new
					saveMember(e, type);
					refreshList();
				} else if (type == 2) { // update
					var selectedItem = controller.getMember(e.getMemberId());
					addMember.populateData(selectedItem);
					addMember.setVisible(true);
				}

			}
		});
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
