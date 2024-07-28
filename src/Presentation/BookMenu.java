package Presentation;

import Logic.BookManager;
import Logic.Book;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMenu extends JFrame
{
    private JPanel root;
    private JButton backButton;
    private JButton submitButton;
    private JRadioButton addBookRadioButton;
    private JRadioButton editBookRadioButton;
    private JRadioButton removeBookRadioButton;
    private JRadioButton findBookRadioButton;

    @SuppressWarnings("unused")
    private JButton homeButton;
    private JPanel btnContainer;
    private JPanel optionContainer;
    private BookManager bookManager;
    private ArrayList<Book> bookList;

    public BookMenu()
    {
        super("Book Menu");
        bookManager = new BookManager();
        bookList = bookManager.getAllBooks();
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
        setPreferredSize(new Dimension(500, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
        setEvents();

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

        submitButton.addActionListener(e->{
            if(addBookRadioButton.isSelected())
            {
                new BookAddEditForm();
                dispose();
            }
            else if(editBookRadioButton.isSelected())
            {
                try
                {
                    new EditBookSelectionForm(bookList);
                    dispose();

                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
            else if(removeBookRadioButton.isSelected())
            {
                new BookAddEditForm();
                dispose();
            }
            else
            {

                new BookAddEditForm();
                dispose();
            }
        });

        addBookRadioButton.addActionListener(e->{
            clearButtons(editBookRadioButton, removeBookRadioButton, findBookRadioButton);
        });

        editBookRadioButton.addActionListener(e->{
            clearButtons(addBookRadioButton, removeBookRadioButton, findBookRadioButton);

        });

        removeBookRadioButton.addActionListener(e->{
            clearButtons(editBookRadioButton, addBookRadioButton, findBookRadioButton);
        });

        findBookRadioButton.addActionListener(e->{
            clearButtons(addBookRadioButton, editBookRadioButton, removeBookRadioButton);
        });
    }

    // take in variable amount of buttons, loop through total amount and clear selection
    private void clearButtons(JRadioButton ...buttons)
    {
        for (JRadioButton button : buttons)
        {
            button.setSelected(false);
        }
    }

}
