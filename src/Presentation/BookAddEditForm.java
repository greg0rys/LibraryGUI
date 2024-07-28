package Presentation;

import Logic.Book;
import Logic.BookManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

public class BookAddEditForm extends JFrame implements Frame
{
    private JPanel root;
    private JButton homeButton;
    private JButton saveButton;
    private JTextField bTitle;
    private JTextField bAuthor;
    private JComboBox availSatus;
    private JButton backButton;
    private JPanel btnContainer;
    private JPanel formContainer;
    private JButton nextBookButton;
    private JLabel bookID;
    private BookManager bookManager;
    private Book currBook;
    private int currBookIDX;
    private JFrame lastScreen;
    private ArrayList<Book> allBooks;

    public BookAddEditForm()
    {
        super("Book Add Form");
        currBook = new Book("temp", "temp");
        setup();
    }

    public BookAddEditForm(Book book, BookManager bManager)
    {
        super("Editing: " + book.getTitle());
        currBook = book;
        setup();
        bookID.setText("Book ID" + book.getTableID());
        loadBook(book);
        bookManager = bManager;
    }

    public BookAddEditForm(Book B, BookManager bManager, JFrame lastFrame, ArrayList<Book> books)
    {
        super("Edit Book Form");
        bookManager = bManager;
        lastScreen = lastFrame;
        allBooks = books;
        currBook = B;
        setup();
        loadBook(B);
        setButtonEvents();
        bookID.setText("Book ID: " + B.getTableID());

    }

    @Override
    public void setup()
    {
        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setVisible(true);
        setLocationRelativeTo(null); // keep frames in the middle of the screen
        pack();
    }

    private void loadBook(Book B)
    {
        bTitle.setText(B.getTitle());
        bAuthor.setText(B.getAuthor());

    }


    private void setButtonEvents()
    {
        homeButton.addActionListener(e->{
           new LandingFrame();
           dispose();
        });

        saveButton.addActionListener(e->{

            if(getTitle().equals("Edit Book Form"))
            {
                out.println("Editing Book");
                Book temp = new Book("","");

                if(!currBook.getTitle().equals(bTitle.getText()))
                {
                    temp.setTitle(bTitle.getText());
                }

                if(bAuthor.getText().equals(" "))
                {
                    temp.setAuthor("Error? ");
                }


                try
                {
                    bookManager.updateBook(temp, currBook.getTableID());
                    allBooks = bookManager.getAllBooks();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }

                out.println(bTitle.getText());
            }
            else
            {
                out.println("adding new book ");
                Book newBook = new Book(bTitle.getText(), bAuthor.getText());
                try
                {
                    bookManager.addBook(newBook);
                    allBooks = bookManager.getAllBooks();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }




        });

        nextBookButton.addActionListener(e->{

            currBookIDX =  (currBookIDX + 1) % allBooks.size();
            currBook = allBooks.get(currBookIDX);
            loadBook(currBook);
            bookID.setText("Book ID: " + currBook.getTableID());
            bTitle.setText(currBook.getTitle());
            bAuthor.setText(currBook.getAuthor());
        });
    }
}
