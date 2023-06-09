package mpp.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mpp.constant.Functions;
import mpp.constant.Message;
import mpp.controller.LoginController;
import mpp.exception.LoginException;
import mpp.model.ListItem;
import mpp.model.Role;

import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.DefaultListCellRenderer;
import java.awt.FlowLayout;
import mpp.view.librarymember.*;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -8315084945374605128L;
	private LoginController loginController = new LoginController();
	
	JList<ListItem> leftMenu;
	//context for CardLayout
	JPanel cards;
	private Role role = Role.BOTH;
	private String loginUser;
	
	private JPanel mainPanel;
	JSplitPane splitPane;
	private LoginWindow loginWindow;
	private JLabel lblLoginUser;
	
	// Librarian
	ListItem checkoutItem = new ListItem(Functions.CHECK_OUT, true);
	ListItem searchBookItem = new ListItem(Functions.SEARCH_BOOK, false);
	ListItem printCheckoutRecordItem = new ListItem(Functions.PRINT_CHECKOUT_RECORD, false);
	
	// Administrator
	ListItem addManageLibraryMemberItem = new ListItem(Functions.MANAGE_LIBRARY_MEMBER, false);
	ListItem addBookItem = new ListItem(Functions.ADD_BOOK, false);
    ListItem addBookCopyItem = new ListItem(Functions.ADD_BOOK_COPY, false);
    
    ListItem logoutItem = new ListItem(Functions.LOG_OUT, true);

    
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Library Management");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		setupLoginWindow();
//		setupMainUI();
	}
	
	private void setupLoginWindow() {
		setSize(Functions.LOGIN_WINDOW_WIDTH, Functions.LOGIN_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        cards = new JPanel();
        cards.setLayout(new BorderLayout());
        loginWindow = new LoginWindow(this);
        cards.add(loginWindow.getMainPanel(), BorderLayout.CENTER);
        getContentPane().add(cards, BorderLayout.CENTER);
        cards.repaint();
	}
	
	private void setupMainUI() {
		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		lblLoginUser = new JLabel("Login user: ");
		lblLoginUser.setHorizontalAlignment(SwingConstants.RIGHT);
		headerPanel.add(lblLoginUser);
		lblLoginUser.setText("Login user: " + loginUser);
		
		setSize(Functions.MAIN_WINDOW_WIDTH, Functions.MAIN_WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(true);
		
		setupContentPanel();
		setupLeftMenu();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftMenu, cards);
		splitPane.setDividerLocation(200);
		splitPane.setLeftComponent(leftMenu);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
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
        LibraryMemberListWindow addLibraryMemberWindow = new LibraryMemberListWindow(this);
        JPanel addLibraryMemberPanel = addLibraryMemberWindow.getMainPanel();

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
        cards.add(addLibraryMemberPanel, addManageLibraryMemberItem.getItemName());
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
            model.addElement(addManageLibraryMemberItem);
            model.addElement(addBookItem);
            model.addElement(addBookCopyItem);
        } else {
            model.addElement(checkoutItem);
            model.addElement(searchBookItem);
            model.addElement(printCheckoutRecordItem);
            model.addElement(addManageLibraryMemberItem);
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
                logout();
            } else {
                CardLayout cardLayout = (CardLayout) (cards.getLayout());
                cardLayout.show(cards, value);
            }
        });
	}
	
	private void logout() {
		int opt = JOptionPane.showConfirmDialog(this.mainPanel, 
								Message.MSG_CONFIRM_LOGOUT, 
								Message.MSG_CONFIRM_TITLE,
								JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			removeComponent();
			setupLoginWindow();			
		}
	}
	
	/**
	 * Login function
	 */
	public void login(String userName, char[] password) {
		if (userName.length() > 0 && password.length > 0) {
			try {
				this.role = loginController.login(userName, password);
				loginUser = userName;
				removeComponent();
				setupMainUI();
				
			} catch (LoginException e) {
				getLoginWindow().getLblErrorMessage().setText(e.getMessage());
			}
		}
	}
	
	public LoginWindow getLoginWindow() {
		return loginWindow;
	}

	public void removeComponent() {
		mainPanel.removeAll();
    }
}
