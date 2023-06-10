package mpp.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mpp.controller.BookController;
import mpp.model.Book;
import mpp.model.BookCopy;

public class AddBookCopyWindow {
	
	private static BookController bookController = new BookController();
	//private AuthorController authorController = new AuthorController();
	private JPanel mainPanel;
	//private List<Author> selectedAuthors = new ArrayList<>();
	private JTable tblBook = new JTable();
	private DefaultTableModel model = (DefaultTableModel) tblBook.getModel();
	public DefaultTableModel getModel() {
		return model;
	}

	private JTable tbCopy = new JTable();
	private DefaultTableModel copyModel = (DefaultTableModel) tbCopy.getModel(); 
	private String selectedValue;

	public AddBookCopyWindow() {
		setupUI();
	}

	private void setupUI() {
		mainPanel = new JPanel();
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{121, 40, 125, 42, 58, 150, 0};
		gbl_mainPanel.rowHeights = new int[]{26, 26, 0, 26, 26, 29, 29, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);

        // Add columns to the model
        model.addColumn("ISBN");
        model.addColumn("Title");
        //model.addColumn("Authors");
        model.addColumn("Max Checkout");
        model.addColumn("Number of Copies");
        
        fillBookTableData();

		JLabel lblBookList = new JLabel("Book List");
		GridBagConstraints gbc_lblBookList = new GridBagConstraints();
		gbc_lblBookList.anchor = GridBagConstraints.WEST;
		gbc_lblBookList.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookList.gridx = 0;
		gbc_lblBookList.gridy = 0;
		mainPanel.add(lblBookList, gbc_lblBookList);
				
		// Create a scroll pane and add the table to it
        //JScrollPane scrollPane = new JScrollPane(tblBook);
		GridBagConstraints gbc_tblBook = new GridBagConstraints();
		gbc_tblBook.gridwidth = 6;
		gbc_tblBook.insets = new Insets(0, 0, 5, 0);
		gbc_tblBook.fill = GridBagConstraints.BOTH;
		gbc_tblBook.gridx = 0;
		gbc_tblBook.gridy = 1;
		mainPanel.add(tblBook, gbc_tblBook);
		
				tblBook.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
		            @Override
		            public void valueChanged(ListSelectionEvent e) {
		                if (!e.getValueIsAdjusting()) {  // Ensure the event is not still in the process of adjusting
		                    int selectedRow = tblBook.getSelectedRow();
		
		                    // Do something with the selected data
		                    selectedValue = tblBook.getValueAt(selectedRow, 0).toString();
		                    //System.out.println("Selected value: " + selectedValue);
		                    fillCopyBookTable();
		                }
		            }
		        });
		
		GridBagConstraints gbc_tbCopy = new GridBagConstraints();
		gbc_tbCopy.gridwidth = 5;
		gbc_tbCopy.insets = new Insets(0, 0, 5, 5);
		gbc_tbCopy.fill = GridBagConstraints.BOTH;
		gbc_tbCopy.gridx = 0;
		gbc_tbCopy.gridy = 4;
		mainPanel.add(tbCopy, gbc_tbCopy);
		
		JButton btnCopy = new JButton("Add copy");
		btnCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bookController.addCopy(selectedValue);
				copyModel.fireTableDataChanged();
				
				fillCopyBookTable();
			}
		});
		GridBagConstraints gbc_btnCopy = new GridBagConstraints();
		gbc_btnCopy.anchor = GridBagConstraints.WEST;
		gbc_btnCopy.insets = new Insets(0, 0, 5, 0);
		gbc_btnCopy.gridx = 5;
		gbc_btnCopy.gridy = 4;
		mainPanel.add(btnCopy, gbc_btnCopy);
		
        // Add columns to the model
        copyModel.addColumn("Copy Number");
        copyModel.addColumn("Is Availabe");
        
		// Disable edit on tables
		tblBook.setDefaultEditor(Object.class, null);
        // Show border
		tblBook.setShowGrid(true);
		tblBook.setGridColor(Color.GRAY);
		
		// Disable edit on tables
		tbCopy.setDefaultEditor(Object.class, null);
        // Show border
		tbCopy.setShowGrid(true);
		tbCopy.setGridColor(Color.GRAY);
	}
	
	private void fillCopyBookTable() {
		copyModel.setRowCount(0);
		Map<String, Book> books = bookController.getAllBooks();
		Book book = books.get(selectedValue);
		
		if (book != null) {
			List<BookCopy> copyList = book.getCopies();
			if (!copyList.isEmpty()) {				
				copyModel.addRow(new Object[] {"Copy Number", "Available"});
		        for (BookCopy copy : copyList) {
		            Object[] row = {copy.getBookCopyID(), copy.isAvailable()};
		            copyModel.addRow(row);
		        }
			}
		}
	}
	
	public void fillBookTableData() {
        model.setRowCount(0);
		//System.out.println("[fillBookTableData] Get books recods :");
        Map<String, Book> books = bookController.getAllBooks();
        //System.out.println("Get all books " + books.size());
        model.addRow(new Object[] {"ISBN", "Title", "Max Checkout", "Number of Copies"});
        for (Entry<String, Book> entry: books.entrySet()) {
        	System.out.print("Book: " + entry.getKey() + " " + entry.getValue().getTitle());
        	Book book = entry.getValue();
       
            model.addRow(new Object[]{book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopies().size()});
        }
    }
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
