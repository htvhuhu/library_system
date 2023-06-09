package mpp.model;
import javax.swing.table.AbstractTableModel;

import mpp.service.MemberService;
import mpp.service.ServiceFactory;

import java.util.List;

public class BookTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6119611072961292821L;
	private List<BookCopy> bookCopies;
    private List<CheckoutRecord> checkoutRecords;
    private String[] columnNames = {"Title", "ISBN", "BookCopyID", "Member Name", "Checkout Date", "Due Date" };
    private MemberService memberService = (MemberService) ServiceFactory.getService(MemberService.class);

    public BookTableModel(List<BookCopy> bookCopies, List<CheckoutRecord> checkoutRecords) {
        this.bookCopies = bookCopies;
        this.checkoutRecords = checkoutRecords;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return bookCopies == null ? 0 : bookCopies.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        var bookCopy = bookCopies.get(row);
        var book = bookCopy.getBook();
        CheckoutRecord checkoutRecord = checkoutRecords.stream()
        		.filter(cr -> cr.getBookCopy().getBook().getIsbn().compareToIgnoreCase(bookCopy.getBook().getIsbn())==0
        		&& cr.getBookCopy().getBookCopyID() ==  (bookCopy.getBookCopyID()))
        		.sorted((cr1, cr2)->cr2.getCheckoutDate().compareTo(cr1.getCheckoutDate()))
        		.findFirst().orElse(null);
        switch (col) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getIsbn();
            case 2:
                return bookCopy.getBookCopyID();
            case 3:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" : memberService.getMember(checkoutRecord.getBorrowerId()).toString();
            case 4:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" :checkoutRecord.getCheckoutDate();
            case 5:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" :checkoutRecord.getDueDate();
            default:
                return null;
        }
    }
    public void updateBooks(List<BookCopy> bookCopies, List<CheckoutRecord> checkoutRecords) {
        this.bookCopies = bookCopies;
        this.checkoutRecords = checkoutRecords;
        fireTableDataChanged();
    }
}

