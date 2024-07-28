package Logic;

import Data.BookTableManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookManager
{
    private ArrayList<Book> books;
    private final static BookTableManager bookManager = new BookTableManager();
    private int lastCount;

    public BookManager()
    {
        books = bookManager.getAllBooks();
        lastCount = books.size();
    }

    /**
     * need to flush this idea out...
     * @return
     */
    public boolean refresh()
    {
        if(books.size() > lastCount)
            books = bookManager.getAllBooks();

        return false;
    }

    public boolean addBook(Book book) throws SQLException
    {
        books.add(book);
        bookManager.addBook(book);

        return true;
    }

    public boolean updateBook(Book B, int bookID) throws SQLException
    {
        return bookManager.updateBook(B, bookID);

    }

    public ArrayList<Book> getAllBooks()
    {
        return bookManager.getAllBooks();
    }


}
