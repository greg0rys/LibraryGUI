package Presentation;

import Logic.BookManager;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.util.ArrayList;

import static java.lang.System.out;

public class BookMenu extends JFrame
{
    private JPanel root;

    @SuppressWarnings("unused")
    private JButton homeButton, previousMenuButton, findBookButton, addBookButton, seeBookLoanStatusButton;
    private  BookManager bookManager;
    private ArrayList<Book> t;

    public BookMenu()
    {
        super("Book Menu");
        load();
        bookManager = new BookManager();
        add(root);
        setPreferredSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    /**
     * Load the book manager.
     */
    private boolean load()
    {
        // make sure we aren't null.
        if(t == null) t = new ArrayList<>();
        if(bookManager == null) bookManager = new BookManager();


        return bookManager.firstLoad(t); // have the manager fill our list.
    }

    /**
     * When new bookMenus get created pass the current manager.
     * @return the BookManager object.
     */
    public  BookManager getBookManager()
    {
        return bookManager;
    }
}
