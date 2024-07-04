package Presentation;

import Logic.BookManager;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.util.ArrayList;

public class BookMenu extends JFrame
{
    private JPanel root;
    private JButton homeButton;
    private JButton previousMenuButton;
    private JButton findBookButton;
    private JButton addBookButton;
    private JButton seeBookLoanStatusButton;
    private  BookManager bookManager;
    private ArrayList<Book>

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
    private void load()
    {

    }

    /**
     * When new bookMenus get created pass the current manager.
     * @return
     */
    public  BookManager getBookManager()
    {
        return bookManager;
    }
}
