package mpp.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import mpp.controller.BookController;
import mpp.model.BookCopy;
import mpp.model.BookTableModel;
import mpp.model.CheckoutRecord;
import mpp.model.LibraryMember;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PrintCheckoutRecordWindow {
	BookController bookController = new BookController();
	JPanel mainPanel;
	JTextField txtMemberId;
	private List<BookCopy> bookCopies;
	JTable bookTable;
	private List<LibraryMember> libraryMembers;
	List<CheckoutRecord> checkoutRecords;

	public PrintCheckoutRecordWindow() {
		bookTable = new JTable(new BookTableModel(this.bookCopies, checkoutRecords));
		loadTable(null);
		setupUI();
	}

	private void loadTable(String memberId) {
		this.libraryMembers = memberId == null || memberId.isEmpty() ? bookController.getAllMembers() : bookController.getMember(memberId);
		
		
		this.checkoutRecords =  this.libraryMembers.stream()
        		.filter(lm -> lm.getCheckoutRecords() != null)
        		.flatMap(lm-> lm.getCheckoutRecords().stream()).toList();

		this.bookCopies = bookController.getAllBookCopies().stream()
				.filter(bc-> checkoutRecords.stream().map(cr->cr.getBookCopy().getBook().getIsbn()).toList().contains(bc.getBook().getIsbn())
						&& checkoutRecords.stream().map(cr->cr.getBookCopy().getBookCopyID()).toList().contains(bc.getBookCopyID())).toList();

		BookTableModel model = (BookTableModel) this.bookTable.getModel();
		model.updateBooks(this.bookCopies, this.checkoutRecords);
		model.fireTableDataChanged();
	}
	
	private void setupUI() {
		mainPanel = new JPanel();
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{189, 72, 0};
		gbl_mainPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblTitle = new JLabel("Checkout book");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		mainPanel.add(lblTitle, gbc_lblTitle);
		
		JLabel lblNewLabel = new JLabel("Member ID: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		mainPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtMemberId = new JTextField();
		GridBagConstraints gbc_txtMemberId = new GridBagConstraints();
		gbc_txtMemberId.insets = new Insets(0, 0, 5, 5);
		gbc_txtMemberId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMemberId.gridx = 1;
		gbc_txtMemberId.gridy = 1;
		mainPanel.add(txtMemberId, gbc_txtMemberId);
		txtMemberId.setColumns(10);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberId = txtMemberId.getText();
				
				if (memberId.isBlank()) {
					JOptionPane.showMessageDialog(mainPanel,"Please input Member ID!!!");
					return;
				}
                loadTable(memberId);
                
                BookTableModel model = (BookTableModel) bookTable.getModel();
        		model.updateBooks(bookCopies, checkoutRecords);
			}
		});
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.anchor = GridBagConstraints.WEST;
		gbc_btnPrint.insets = new Insets(0, 0, 5, 0);
		gbc_btnPrint.gridx = 2;
		gbc_btnPrint.gridy = 1;
		mainPanel.add(btnPrint, gbc_btnPrint);
		

		GridBagConstraints gbc_bookTable = new GridBagConstraints();
		gbc_bookTable.gridheight = 3;
		gbc_bookTable.insets = new Insets(0, 0, 5, 0);
		gbc_bookTable.gridwidth = GridBagConstraints.REMAINDER;
		gbc_bookTable.fill = GridBagConstraints.BOTH;
		gbc_bookTable.gridx = 0;
		gbc_bookTable.gridy = 2;
		gbc_bookTable.weightx = 1;
		gbc_bookTable.weighty = 1;
		mainPanel.add(new JScrollPane(bookTable), gbc_bookTable);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
