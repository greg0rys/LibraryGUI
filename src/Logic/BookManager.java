package Logic;

import Data.BookTableManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The connector class between the presentation and the database.
 */
public class BookManager
{
    private final ArrayList<Book> ALL_BOOKS = new ArrayList<>();
    private final static BookTableManager BOOK_TABLE_MANAGER = new BookTableManager();
    private int lastCount;

    public BookManager()
    {
        ALL_BOOKS.addAll(BOOK_TABLE_MANAGER.getAllBooks());
        lastCount = ALL_BOOKS.size();
    }

    /**
     * need to flush this idea out...
     * @return true if refreshed false if else.
     */
    public boolean refresh()
    {
        if(ALL_BOOKS.size() > lastCount)
            ALL_BOOKS.addAll(BOOK_TABLE_MANAGER.getAllBooks());

        return false;
    }

    public boolean addBook(Book book) throws SQLException
    {
        ALL_BOOKS.add(book);
        BOOK_TABLE_MANAGER.addBook(book);
        lastCount++;

        return true;
    }

    public boolean updateBook(Book B, int bookID, int currBookIDX) throws SQLException
    {
        ALL_BOOKS.remove(currBookIDX);
        return BOOK_TABLE_MANAGER.updateBook(B, bookID);

    }

    public boolean removeBook(int bookID)
    {
        ALL_BOOKS.removeIf(b -> b.getTableID() == bookID); // update local data struct
        return BOOK_TABLE_MANAGER.removeBook(bookID);
    }

    public ArrayList<Book> getAllBooks()
    {
        return BOOK_TABLE_MANAGER.getAllBooks();
    }


}
