package mpp.model;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private List<BookCopy> bookCopies;
    private List<LibraryMember> libraryMembers;
    private String[] columnNames = {"Title", "ISBN", "Author", "BookCopyID", "Availability","Borrower ID", "Checkout Date", "Due Date" };

    public BookTableModel(List<BookCopy> bookCopies, List<LibraryMember> libraryMembers) {
        this.bookCopies = bookCopies;
        this.libraryMembers = libraryMembers;
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
        CheckoutRecord checkoutRecord = this.libraryMembers.stream()
        		.filter(lm -> lm.getCheckoutRecords() != null)
        		.flatMap(lm-> lm.getCheckoutRecords().stream())
        		.filter(cr -> cr.getBookCopy().getBookCopyID() ==  (bookCopy.getBookCopyID()))
        		.sorted((cr1, cr2)->cr2.getCheckoutDate().compareTo(cr1.getCheckoutDate()))
        		.findFirst().orElse(null);
        switch (col) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getIsbn();
            case 2:
                return book.getAuthors();
            case 3:
                return bookCopy.getBookCopyID();
            case 4:
                return bookCopy.isAvailable() ? "Available" : "Not Available";
            case 5:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" : checkoutRecord.getBorrowerId();
            case 6:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" :checkoutRecord.getCheckoutDate();
            case 7:
                return bookCopy.isAvailable() ? "": checkoutRecord == null ?"" :checkoutRecord.getDueDate();
            default:
                return null;
        }
    }
    public void updateBooks(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
        fireTableDataChanged();
    }
}

