package Presentation;

import Data.BookTableManager;
import Logic.Book;
import Logic.BookManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookList extends JFrame
{
    private JPanel root;
    private JTable bookList;
    private JScrollPane tableScroller;
    private JButton homeButton;
    private JButton lastScrButton;
    private JButton searchButton;
    private JButton addBookButton;
    private JLabel totalBooksL;
    private final ArrayList<Book> books = new ArrayList<>();
    private JFrame lastScr = null;

    /**
     * Default constructor
     * @param B the required ArrayList of books to load into our model.
     */
    public BookList(ArrayList<Book> B)
    {
        super("Book List");

        setup(B);
        buttonEvents();

    }

    public BookList(ArrayList<Book> B, JFrame lastFrame)
    {
        super("Book List");
        lastScr = lastFrame;
        setup(B);
        buttonEvents();
    }



    /*
     * Set the default table model on load.
     */
    public void model()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Title");
        dtm.addColumn("Author");

        for(Book b : books)
            dtm.addRow(new Object[]{b.getTitle(),b.getAuthor()});

        bookList.setModel(dtm);
        totalBooksL.setText("Total Books: " + books.size());
    }

    /**
     * Default setup method - created to keep the constructor clean.
     * @param B the ArrayList of books that will be added to the model.
     */

    private void setup(ArrayList<Book> B)
    {
        books.clear();
        books.addAll(B);
        model(); /* set the table model */
        add(root);
        setPreferredSize(new Dimension(500, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

    private void buttonEvents()
    {
        addBookButton.addActionListener(e -> {
            /* create input form for new books. */
            dispose();
        });

        homeButton.addActionListener( e-> {
            new LandingFrame();
            dispose();
        });

        /* only set the event if the last screen was passed if not hide button*/
        if(lastScr != null)
        {
            lastScrButton.addActionListener( e-> {
                /* have a constructor that takes in the JFrame that existed before this one was created
                 * to simulate a 'back' button effect. */
                lastScr.setVisible(true);
                dispose();
            });
        }
        else
        {
            lastScrButton.setVisible(false);
        }


    }
}
