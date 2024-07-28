package Presentation;

import Logic.Book;
import Logic.BookManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private JButton previousBookButton;
    private BookManager bookManager;
    private Book currBook;
    private Book lastBook;
    private int currBookIDX;
    private JFrame lastScreen;
    private ArrayList<Book> allBooks;

    /**
     * Default no args constructor
     */
    public BookAddEditForm()
    {
        super("Book Add Form");
        currBook = new Book("temp", "temp");
        setup();
        bookManager = new BookManager();
        allBooks = bookManager.getAllBooks();
        previousBookButton.setVisible(false);
        nextBookButton.setVisible(false);
        bookID.setText("Adding New Book..");

    }

    /**
     * Create a new form with a book and manager
     * @param book the book to display
     * @param bManager the book manager in play.
     */
    public BookAddEditForm(Book book, BookManager bManager)
    {
        super("Editing: " + book.getTitle());
        currBook = book;
        setup();
        bookID.setText("Book ID" + book.getTableID());
        loadBook(book);
        bookManager = bManager;
    }

    /**
     * Create a form with full init.
     * @param B the book to display
     * @param bManager the current manager in play
     * @param lastFrame the previous screen
     * @param books all book stored in the DB
     */
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

    /**
     * Interface abstract method.
     */
    @Override
    public void setup()
    {
        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setVisible(true);
        setLocationRelativeTo(null); // keep frames in the middle of the screen
        setButtonEvents();
        pack();
    }

    /**
     * Set the form fields with Book data.
     * @param B the book we wish to display.
     */
    private void loadBook(Book B)
    {
        bTitle.setText(B.getTitle());
        bAuthor.setText(B.getAuthor());

    }


    /**
     * Wire events to all buttons on Frame.
     */
    private void setButtonEvents()
    {
        /*
         * Create a new Landing Frame & dispose of the current frame.
         */
        homeButton.addActionListener(e->{
           new LandingFrame();
           dispose();
        });

        /*
         * When clicked a check will occur to make sure the fields have changed, if not nothing will happen
         * If they have changed book will be updated, and current data structure (ArrayList<Book>) will be updated to
         *  hold the new book in the current books index.
         */
        saveButton.addActionListener(e->{
            // a check needs to occur that makes sure we actually made a change before running the update.
            if(getTitle().equals("Edit Book Form"))
            {
                try
                {
                    Book temp = new Book(bTitle.getText(), bAuthor.getText()); // create comparison object.

                    // compare for updates, if none return.
                    if(currBook.compare(temp))
                    {
                        MessageDialogs.showWarningMessage(this, "No Change Was Detected.");
                        return;

                    }

                    // perform updates to the book and show the appropriate success / failure message.
                    if(bookManager.updateBook(temp, currBook.getTableID(), allBooks.indexOf(currBook)))
                    {
                        MessageDialogs.showSuccessMessage(this, temp.getTitle() +
                                " updated successfully");
                        allBooks.set(allBooks.indexOf(currBook), temp); // update this book in the local data struct
                    }
                    else
                    {
                        MessageDialogs.showErrorMessage(this, temp.getTitle()
                                + " unable to be edited.. Try again");
                    }

                   // allBooks = bookManager.getAllBooks(); // update the array list with a fresh DB call.
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }

            }
            else
            {

                // check to make sure user has input data in all fields.
                if(validateNewBookFields())
                {
                    MessageDialogs.showErrorMessage(this, "Please ensure all fields are filled out");
                    return;
                }
                try
                {
                    Book temp = new Book(bTitle.getText(), bAuthor.getText()); // store the new book as temp object.

                    if(bookManager.addBook(temp))
                        MessageDialogs.showSuccessMessage(this, temp.getTitle() + " Added to DB");
                    else
                        MessageDialogs.showErrorMessage(this, temp.getTitle()
                                + " Was unable to be added");
                    allBooks.add(temp); // add the new book to the local data struct as well.
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }




        });

        /*
         * When clicked advance the display 1 book
         */
        nextBookButton.addActionListener(e->{

            lastBook = currBook; // store the current book last. Possibly turn this into a doubly linked list instead.
            currBookIDX =  (currBookIDX + 1) % allBooks.size();
            currBook = allBooks.get(currBookIDX);
            loadBook(currBook);
            fillFields(currBook.getTitle(), currBook.getAuthor(), currBook.getTableID());
        });

        /*
         * When clicked go back -1 book
         */
        previousBookButton.addActionListener( e -> {
            if(lastBook == null) return;

            currBook = lastBook;
           currBookIDX = allBooks.indexOf(lastBook);
           loadBook(lastBook);
           fillFields(currBook.getTitle(), currBook.getAuthor(), currBook.getTableID());


        });
    }

    /**
     * Ensure that both Book Title and Book Author fields are set when adding a new book to the system
     * @return true if all required fields are set, false if else.
     */
    private boolean validateNewBookFields()
    {
        boolean emptyTitle = bTitle.getText().equals(" ");
        boolean emtpyAuthor = bAuthor.getText().equals(" ");
        boolean allEmpty = ( bTitle.getText().isEmpty() && bAuthor.getText().isEmpty() );

        return (emptyTitle || emtpyAuthor || allEmpty);
    }

    /**
     * Fill the form fields with data from a book
     * @param T the title of the book
     * @param A the author of the book
     * @param ID the ID of the book
     */
    private void fillFields(String T, String A, int ID)
    {
        bTitle.setText(T);
        bAuthor.setText(A);
        bookID.setText("Book ID: [ " + ID + " ]");
    }
}
