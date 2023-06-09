package mpp.view.librarymember;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import mpp.controller.MemberController;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibraryMemberList extends JPanel {
	private static final long serialVersionUID = -7723999228193594172L;
	private TablePanel tablePanel;
	private JButton okBtn;
	private MemberController controller;
	private Toolbar toolbar;
	private AddMember addMember;
	private JButton btnNewButton;

	public LibraryMemberList(Frame parent) {
		controller = new MemberController();
		toolbar = new Toolbar();
		addMember = new AddMember(this, parent);

		setLayout(new BorderLayout());
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getMembers());
		add(tablePanel, BorderLayout.CENTER);

		okBtn = new JButton("+ Add Member");
		okBtn.setHorizontalAlignment(SwingConstants.LEFT);

		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);

		// add(okBtn, BorderLayout.NORTH);
		add(toolbar, BorderLayout.NORTH);

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember.setSize(600, 500);
				addMember.clearFields();
				addMember.setVisible(true);

			}
		});
		toolbar.add(btnNewButton);

//		setSize(500, 600);
//		setVisible(true);

		tablePanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e, int type) {
				if (type == 1) {
					saveMember(e, type);
					refestList();
				} else if (type == 2) {
					var selectedItem = controller.getMember(e.getNumber());
					addMember.populateData(selectedItem);
					addMember.setVisible(true);
				}

			}
		});
	}

	public void saveMember(FormEvent e, int type) {
		controller.saveMember(e, type);
	}

	public void refestList() {
		tablePanel.refresh();
	}

}
