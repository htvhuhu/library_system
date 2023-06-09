package mpp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mpp.controller.BookController;
import mpp.model.Book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AddBookCopyWindow {
	private static final String[] DEFAULT_BOOK_HEADERS = {"ISBN", "Title", "Number of Copies"};
	private final String[] DEFAULT_COPY_HEADERS = {"Copy Number", "Is Available"};
	
	private BookController bookController = new BookController();
	private JPanel mainPanel;
	private JTable bookTable;
	private Map<String, Book> bookMap;

	public AddBookCopyWindow() {
		setupUI();
		
		// Set table header
        setHeader(bookTable, DEFAULT_BOOK_HEADERS);
        
        bookMap = bookController.getAllBooks();
        // fill default data
        fillBookTableData(bookMap);
	}
	
	private void fillBookTableData(Map<String, Book> tableData) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        for (Book book : tableData.values()) {
            Object[] row = {book.getIsbn(), book.getTitle(), book.getCopies().size()};
            model.addRow(row);
        }
    }
	
	private void setHeader(JTable table, String[] headers) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (String header : headers) {
            model.addColumn(header);
        }
    }

	private void setupUI() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneBook = new JScrollPane();
		headerPanel.add(scrollPaneBook, BorderLayout.NORTH);
		
		bookTable = new JTable();
		headerPanel.add(bookTable, BorderLayout.SOUTH);
		scrollPaneBook.setViewportView(bookTable);
		
		// Disable edit on tables
        bookTable.setDefaultEditor(Object.class, null);
        // Show border
        bookTable.setShowGrid(true);
        bookTable.setGridColor(Color.GRAY);
		
		JPanel contentPanel = new JPanel();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
}
