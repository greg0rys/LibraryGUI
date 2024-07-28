package Presentation;

import Logic.Book;
import Logic.BookManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditBookSelectionForm extends JFrame implements Frame
{
    private JButton homeButton;
    private JButton backButton;
    private JList bookList;
    private JPanel root;
    private JButton submitButton;
    private BookManager bookManager;
    private ArrayList<Book> bookArrayList;

    public EditBookSelectionForm(ArrayList<Book> books) throws SQLException
    {
        super("Edit Book Selection");
        bookManager = new BookManager();
        bookArrayList = books;
        setup();
    }

    @Override
    public void setup() throws SQLException
    {
        add(root);
        setPreferredSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        fillList(); // fill list with data.
    }

    private void fillList()
    {
        // ensure that the array list has been filled
        if(bookArrayList.isEmpty())
            bookArrayList.addAll(bookManager.getAllBooks());


        DefaultListModel listModel = new DefaultListModel();

        for(Book b : bookArrayList)
        {
            listModel.addElement(b.getTitle() + " - " + b.getAuthor());
        }

        bookList.setModel(listModel);
    }

    private void setEvents()
    {
        submitButton.addActionListener(e->{
            // use the arraylist index of to take the selected index and find the book in the list to pass in edit
        });

    }
}
