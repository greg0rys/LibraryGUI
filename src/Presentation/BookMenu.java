package Presentation;

import Logic.BookManager;
import Logic.Book;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookMenu extends JFrame
{
    private JPanel root;

    @SuppressWarnings("unused")
    private JButton homeButton, previousMenuButton, findBookButton, addBookButton, seeBookLoanStatusButton;
    private BookManager bookManager;
    private ArrayList<Book> bookList;

    public BookMenu()
    {
        super("Book Menu");
        load();

    }

    /**
     * The program driver should be the holder of the managers, and they should be passed in when the guis are created
     * TODO: figure out if there is a more efficient and direct way to complete this.
     * @param B the book manager we wish to use throughout the program lifecycle.
     */
    public BookMenu(BookManager B)
    {
        super("Book Menu");
        bookManager = B;
        load();
    }

    /**
     * Load the book manager.
     */
    private void load()
    {
        bookManager = new BookManager();
        add(root);
        setPreferredSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        bookList = bookManager.getAllBooks();

    }

    /**
     * When new bookMenus get created pass the current manager.
     * @return the BookManager object.
     */
    public  BookManager getBookManager()
    {
        return bookManager;
    }

    public ArrayList<Book> getBookList()
    {
        return bookList;
    }

    /**
     * Set the events on the actionable JObjects
     */
    private void setEvents()
    {
        homeButton.addActionListener(e->{
            new LandingFrame();
            dispose();
        });
    }

}
