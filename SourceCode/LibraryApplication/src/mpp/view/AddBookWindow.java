package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mpp.controller.BookController;
import mpp.model.Address;
import mpp.model.Author;
import mpp.model.Book;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookWindow {
	
	private BookController bookController = new BookController();
	private JPanel mainPanel;
	private JTextField txtTitle;
	private JTextField txtISBN;

	public AddBookWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{121, 40, 125, 150, 0};
		gbl_mainPanel.rowHeights = new int[]{26, 26, 16, 26, 26, 26, 26, 29, 29, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblBookTitle = new JLabel("Title:");
		GridBagConstraints gbc_lblBookTitle = new GridBagConstraints();
		gbc_lblBookTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookTitle.gridx = 0;
		gbc_lblBookTitle.gridy = 1;
		mainPanel.add(lblBookTitle, gbc_lblBookTitle);
		

	
		ArrayList<String> authors = new ArrayList<>();
//		allAuthors.stream().forEach(author -> {
//			String name = author.getFirstName() + " " + author.getLastName();
//			authors.add(name);
//		});
//		
		txtTitle = new JTextField();
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.gridx = 2;
		gbc_txtTitle.gridy = 1;
		mainPanel.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN Number:");
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.insets = new Insets(0, 0, 5, 5);
		gbc_lblISBN.gridx = 0;
		gbc_lblISBN.gridy = 2;
		mainPanel.add(lblISBN, gbc_lblISBN);
		
		txtISBN = new JTextField();
		GridBagConstraints gbc_txtISBN = new GridBagConstraints();
		gbc_txtISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtISBN.insets = new Insets(0, 0, 5, 5);
		gbc_txtISBN.gridx = 2;
		gbc_txtISBN.gridy = 2;
		mainPanel.add(txtISBN, gbc_txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author Information:");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.NORTH;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 3;
		mainPanel.add(lblAuthor, gbc_lblAuthor);
		
		
		JList listAuthor = new JList(authors.toArray());
		GridBagConstraints gbc_listAuthor = new GridBagConstraints();
		gbc_listAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_listAuthor.fill = GridBagConstraints.BOTH;
		gbc_listAuthor.gridx = 2;
		gbc_listAuthor.gridy = 3;
		mainPanel.add(listAuthor, gbc_listAuthor);
		
		JButton btnAddAuthor = new JButton("Add author");

		btnAddAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AuthorWindow frame = new AuthorWindow();
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnAddAuthor = new GridBagConstraints();
		gbc_btnAddAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddAuthor.gridx = 2;
		gbc_btnAddAuthor.gridy = 4;
		mainPanel.add(btnAddAuthor, gbc_btnAddAuthor);
		
		JButton btnAddBook = new JButton("Save");
		GridBagConstraints gbc_btnAddBook = new GridBagConstraints();
		gbc_btnAddBook.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddBook.gridx = 2;
		gbc_btnAddBook.gridy = 5;
		mainPanel.add(btnAddBook, gbc_btnAddBook);

	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
