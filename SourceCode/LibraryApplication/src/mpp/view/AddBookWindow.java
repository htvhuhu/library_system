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

	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
