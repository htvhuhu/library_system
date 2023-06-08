package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import mpp.controller.BookController;
import mpp.model.Author;
import mpp.model.Book;

import com.jgoodies.forms.layout.FormSpecs;
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
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("194px"),
				ColumnSpec.decode("61px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("16px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
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
