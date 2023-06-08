package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mpp.controller.BookController;
import mpp.model.Author;
import mpp.model.Book;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow {
	
	private BookController bookController = new BookController();
	private JPanel mainPanel;

	public AddBookWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		
		JLabel lblTitle = new JLabel("Add book");
		mainPanel.add(lblTitle, "2, 2, left, top");
		
		JButton btnAddBookButton = new JButton("New button");
		btnAddBookButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Author author = new Author();
				author.setFirstName("Van");
				author.setLastName("Cao");
				
				List<Author> authors = new ArrayList<>();
				authors.add(author);

				Book book = new Book("isbn01", "Gone With The Wind", authors, 10, new ArrayList<>());
				bookController.addBook(null, null, authors, 0, 0);
			}
		});
		mainPanel.add(btnAddBookButton, "2, 16");
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
