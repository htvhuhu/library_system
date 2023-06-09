package mpp.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;

import mpp.controller.BookController;
import mpp.controller.MemberController;
import mpp.model.Book;
import mpp.model.BookTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchBookWindow {
	private JPanel mainPanel;
	private JTextField txtSearchQuery;
	BookController bookController = new BookController();
	private List<Book> books;
	
	public SearchBookWindow() {
		setupUI();
	}
	
	private void setupUI() {
		mainPanel = new JPanel();
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{195, 60, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{14, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblTitle = new JLabel("Search book");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		mainPanel.add(lblTitle, gbc_lblTitle);
		
		JLabel lblNewLabel = new JLabel("ISBN:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		mainPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtSearchQuery = new JTextField();
		GridBagConstraints gbc_txtSearchQuery = new GridBagConstraints();
		gbc_txtSearchQuery.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchQuery.gridx = 1;
		gbc_txtSearchQuery.gridy = 1;
		mainPanel.add(txtSearchQuery, gbc_txtSearchQuery);
		txtSearchQuery.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var bookCopyList = bookController.searchBookCopies(txtSearchQuery.getText());
				
				if(bookCopyList == null || bookCopyList.size() == 0)
				{
	                JOptionPane.showMessageDialog(mainPanel,"No copy found!!!");
				}
				JTable bookTable = new JTable(new BookTableModel(bookCopyList));
				bookTable.setEnabled(false);  // makes the cells read-only

				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.fill = GridBagConstraints.BOTH;
				gbc.weightx = 1;
				gbc.weighty = 1;

				mainPanel.add(new JScrollPane(bookTable), gbc);
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 1;
		mainPanel.add(btnSearch, gbc_btnSearch);
		
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
