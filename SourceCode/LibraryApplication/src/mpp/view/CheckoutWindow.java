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
import mpp.model.SearchBookTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CheckoutWindow {
	BookController bookController = new BookController();
	JPanel mainPanel;
	JTextField txtMemberId;
	JTextField txtISBN;
	private List<BookCopy> bookCopies;
	JTable bookTable;
	private List<LibraryMember> libraryMembers;
	List<CheckoutRecord> checkoutRecords;
	
	public CheckoutWindow() {
		bookTable = new JTable(new BookTableModel(this.bookCopies, checkoutRecords));
		loadTable(null);
		setupUI();
	}

	private void loadTable(String memberId) {
		this.libraryMembers = memberId == null || memberId.isEmpty() ? bookController.getAllMembers() : bookController.getMember(memberId);
		checkoutRecords = this.libraryMembers.stream()
        		.filter(lm -> lm.getCheckoutRecords() != null)
        		.flatMap(lm-> lm.getCheckoutRecords().stream()).toList();

		bookCopies = bookController.getAllBookCopies().stream()
				.filter(bc-> checkoutRecords.stream().map(cr->cr.getBookCopy().getBook().getIsbn()).toList().contains(bc.getBook().getIsbn())
						&& checkoutRecords.stream().map(cr->cr.getBookCopy().getBookCopyID()).toList().contains(bc.getBookCopyID())).toList();
		BookTableModel model = (BookTableModel) bookTable.getModel();
		model.updateBooks(this.bookCopies, checkoutRecords);
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
		
		JLabel lblNewLabel_1 = new JLabel("ISBN:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		mainPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtISBN = new JTextField();
		GridBagConstraints gbc_txtISBN = new GridBagConstraints();
		gbc_txtISBN.insets = new Insets(0, 0, 5, 5);
		gbc_txtISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtISBN.gridx = 1;
		gbc_txtISBN.gridy = 2;
		mainPanel.add(txtISBN, gbc_txtISBN);
		txtISBN.setColumns(10);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberId = txtMemberId.getText();
                String isbn = txtISBN.getText();
                if (memberId.isBlank()) {
					JOptionPane.showMessageDialog(mainPanel,"Please input Member ID!!!");
					return;
				}
                
                if (isbn.isBlank()) {
					JOptionPane.showMessageDialog(mainPanel,"Please input ISBN!!!");
					return;
				}
                var result = bookController.checkoutBook(memberId, isbn);
                JOptionPane.showMessageDialog(mainPanel,result);
                loadTable(null);
                
                BookTableModel model = (BookTableModel) bookTable.getModel();
        		model.updateBooks(bookCopies, checkoutRecords);
			}
		});
		
		GridBagConstraints gbc_btnCheckout = new GridBagConstraints();
		gbc_btnCheckout.insets = new Insets(0, 0, 5, 5);
		gbc_btnCheckout.gridx = 1;
		gbc_btnCheckout.gridy = 3;
		mainPanel.add(btnCheckout, gbc_btnCheckout);
		

		GridBagConstraints gbc_bookTable = new GridBagConstraints();
		gbc_bookTable.gridwidth = GridBagConstraints.REMAINDER;
		gbc_bookTable.fill = GridBagConstraints.BOTH;
		gbc_bookTable.gridx = 0;
		gbc_bookTable.gridy = 4;
		gbc_bookTable.weightx = 1;
		gbc_bookTable.weighty = 1;
		mainPanel.add(new JScrollPane(bookTable), gbc_bookTable);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
