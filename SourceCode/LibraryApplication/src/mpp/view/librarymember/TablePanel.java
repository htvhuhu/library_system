package mpp.view.librarymember;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import mpp.model.LibraryMember;

public class TablePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private LibraryMemberTableModel tableModel;
	private JPopupMenu popup;
	private FormListener formListener;
	public TablePanel() {
		
		tableModel = new LibraryMemberTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		setLayout(new BorderLayout());
		JMenuItem removeItem = new JMenuItem("Delete Member");
		JMenuItem updateItem = new JMenuItem("Update Member");
		popup.add(removeItem);
		popup.add(updateItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);

				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				tableModel.fireTableRowsDeleted(row, row);
				/*if(personTableListener != null) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}*/
				String number = table.getModel().getValueAt(row, 0).toString();
				FormEvent ev = new FormEvent(number, this);

				if (formListener != null) {
					formListener.formEventOccurred(ev, 1);
				}
			}
		});
		
		updateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				tableModel.fireTableRowsDeleted(row, row);
				/*if(personTableListener != null) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}*/
				String number = table.getModel().getValueAt(row, 0).toString();
				FormEvent ev = new FormEvent(number, this);

				if (formListener != null) {
					formListener.formEventOccurred(ev, 2);
				}
			}
		});
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<LibraryMember> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}
