package mpp.view;

//Java Program to create a popup and display
//it on a parent frame
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class AuthorWindow extends JFrame implements ActionListener {
	
	// popup
	private AuthorWindow p;
	
	private List<String> selectedAuthor;
	private JFrame f;

	// constructor
	public AuthorWindow () {
		selectedAuthor = new ArrayList<>();
		// create a frame
		f = new JFrame("Select Author");

		// create a label
		JLabel l = new JLabel("This is a popup");

		f.setSize(400, 400);

		PopupFactory pf = new PopupFactory();

		// create a panel
		JPanel p2 = new JPanel();

		// set Background of panel
		p2.setBackground(Color.red);

		p2.add(l);

		// create a panel
		JPanel p1 = new JPanel();
		f.getContentPane().add(p1);
		GridBagLayout gbl_p1 = new GridBagLayout();
		gbl_p1.columnWidths = new int[]{0, 0};
		gbl_p1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_p1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_p1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		p1.setLayout(gbl_p1);
		
		String[] authors = new String[]{"But", "Lam", "Huan", "Hien"};
		
		JLabel lblNewLabel = new JLabel("Select author:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		p1.add(lblNewLabel, gbc_lblNewLabel);
		
		JList list = new JList(authors);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		p1.add(list, gbc_list);
		
		list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  String text = list.getSelectedValue().toString();
                  System.out.println("Author = " + text);
                }
            }
        });
		
		JButton btnAdd = new JButton("Add author");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Select author");
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 3;
		p1.add(btnAdd, gbc_btnAdd);
		f.show();
		
		
	}

	// if the button is pressed
	public void actionPerformed(ActionEvent e)
	{
		p.show();
	}
}

