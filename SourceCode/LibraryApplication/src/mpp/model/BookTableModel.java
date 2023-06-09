package mpp.model;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private List<BookCopy> bookCopies;
    private String[] columnNames = {"Title", "ISBN", "Author", "BookCopyID", "Availability",};

    public BookTableModel(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return bookCopies.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        var bookCopy = bookCopies.get(row);
        var book = bookCopy.getBook();
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
            default:
                return null;
        }
    }
}

