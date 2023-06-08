package mpp.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mpp.constant.Functions;
import mpp.model.ListItem;
import mpp.model.Role;
import mpp.view.librarymember.LibraryMemberList;

import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.DefaultListCellRenderer;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -8315084945374605128L;
	
	JList<ListItem> leftMenu;
	//context for CardLayout
	JPanel cards;
	private Role role = Role.BOTH;
	
	private JPanel mainPanel;

	// Librarian
	ListItem checkoutItem = new ListItem(Functions.CHECK_OUT, true);
	ListItem searchBookItem = new ListItem(Functions.SEARCH_BOOK, false);
	ListItem printCheckoutRecordItem = new ListItem(Functions.PRINT_CHECKOUT_RECORD, false);
	
	// Administrator
	ListItem addMemberItem = new ListItem(Functions.ADD_MEMBER, false);
	ListItem addBookItem = new ListItem(Functions.ADD_BOOK, false);
    ListItem addBookCopyItem = new ListItem(Functions.ADD_BOOK_COPY, false);
    
    ListItem logoutItem = new ListItem(Functions.LOG_OUT, true);

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Library Management");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		
		setupUI();
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftMenu, cards);
		splitPane.setDividerLocation(200);
		mainPanel.add(splitPane);
	}
	
	private void setupUI() {
		setupContentPanel();
		setupLeftMenu();
	}
	
	private void setupContentPanel() {
		// Librarian
		// Checkout Book Panel
        CheckoutWindow checkoutWindow = new CheckoutWindow();
        JPanel checkoutPanel = checkoutWindow.getMainPanel();
        
        // Search Book Window
        SearchBookWindow searchBookWindow = new SearchBookWindow();
        JPanel searchBookPanel = searchBookWindow.getMainPanel();
        
        // Print checkout record Window
        PrintCheckoutRecordWindow printCheckoutRecordWindow = new PrintCheckoutRecordWindow();
        JPanel printCheckoutRecordPanel = printCheckoutRecordWindow.getMainPanel();

        // Administrator
        // Add Library Member Window
        AddLibraryMemberWindow addLibraryMemberWindow = new AddLibraryMemberWindow();
        JPanel addLibraryMemberPanel = addLibraryMemberWindow.getMainPanel();
        
        LibraryMemberList member = new LibraryMemberList(this);
        // Add Book Window
        AddBookWindow addBookWindow = new AddBookWindow();
        JPanel addBookPanel = addBookWindow.getMainPanel();
        
        // Add Book Copy Window
        AddBookCopyWindow addBookCopyWindow = new AddBookCopyWindow();
        JPanel addBookCopyPanel = addBookCopyWindow.getMainPanel();
        
        cards = new JPanel(new CardLayout());
		// Librarian
        cards.add(checkoutPanel, checkoutItem.getItemName());
        cards.add(searchBookPanel, searchBookItem.getItemName());
        cards.add(printCheckoutRecordPanel, printCheckoutRecordItem.getItemName());
        // Administrator
        cards.add(member, addMemberItem.getItemName());
        cards.add(addBookPanel, addBookItem.getItemName());
        cards.add(addBookCopyPanel, addBookCopyItem.getItemName());
        
	}
	
	@SuppressWarnings("serial")
	private void setupLeftMenu() {
		
		DefaultListModel<ListItem> model = new DefaultListModel<>();
        if (role == Role.LIBRARIAN) {
            model.addElement(checkoutItem);
            model.addElement(searchBookItem);
            model.addElement(printCheckoutRecordItem);
        } else if (role == Role.ADMIN) {
            model.addElement(addMemberItem);
            model.addElement(addBookItem);
            model.addElement(addBookCopyItem);
        } else {
            model.addElement(checkoutItem);
            model.addElement(searchBookItem);
            model.addElement(addMemberItem);
            model.addElement(addBookItem);
            model.addElement(addBookCopyItem);
        }
        model.addElement(logoutItem);
        leftMenu = new JList<>(model);
        // selected first item in list
        int begin = 0;
        int end = 0;
        leftMenu.setSelectionInterval(begin, end);
        leftMenu.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
                if (value instanceof ListItem) {
                    ListItem nextItem = (ListItem) value;
                    setText(nextItem.getItemName());
                    if (isSelected) {
                        setForeground(Color.WHITE);
                        setBackground(Color.BLUE);
                    }
                }
                return c;
            }
        });
        
        leftMenu.addListSelectionListener(event -> {
            String value = leftMenu.getSelectedValue().getItemName();
            if (value == Functions.LOG_OUT.toString()) {
                //handleLogout();
            } else {
                CardLayout cardLayout = (CardLayout) (cards.getLayout());
                cardLayout.show(cards, value);
            }
        });
	}

}
