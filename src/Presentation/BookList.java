package Presentation;

import Logic.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookList extends JFrame
{
    private JPanel root;
    private JTable table1;
    private ArrayList<Book> books;

    public BookList(ArrayList<Book> B)
    {
        super("Book List");

        if(B == null) B = new ArrayList<>();
        books = B;
        model();

        add(root);
        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

    }

    @SuppressWarnings("DataFlowIssue")
    public void model()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Title");
        dtm.addColumn("Author");

        for(Book b : books)
            dtm.addRow(new Object[]{b.getTitle(),b.getAuthor()});

        table1.setModel(dtm);
    }
}
