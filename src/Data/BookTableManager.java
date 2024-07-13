package Data;
import Logic.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class BookTableManager extends SuperDB
{
  private ArrayList<Book> listNode;
  private PreparedStatement ps;
  private ResultSet rs;
  private static final String UPDATE_BOOK = "UPDATE Book SET Title=?,Author=? WHERE Title=?";
  private static final String SELECT_ALL_BOOKS = "SELECT * FROM Book";
  private static final String INSERT_BOOK = "INSERT INTO Book(Title,Author) VALUES(?,?)";



  /* no args constructor */
    public BookTableManager()
    {
        super();
        listNode = getAllBooks();
    }


    /**
     * Get all books from the database.
     * @return ArrayList of Books.
     */
    public ArrayList<Book> getAllBooks()
    {
        ArrayList<Book> books = new ArrayList<>();

        try(Connection con = super.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
                books.add(new Book(rs.getString("Title"), rs.getString("Author")));
            return books;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Insert a new book into the database.
     * @param B the book to be added
     * @return true if added false if else.
     * @throws SQLException Exceptions can occur when interfacing with the DB
     */
    public boolean addBook(Book B) throws SQLException
    {
        if(B == null)
            return false;

        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(INSERT_BOOK);
            ps.setString(1,B.getTitle());
            ps.setString(2,B.getAuthor());

            return true;

        }
        catch (SQLException e)
        {
            out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Takes whatever the values are for the Book and updates them in the DB
     * @param B
     * @return true if updated false when errors occur.
     */
    public boolean updateBook(Book B)
    {
        if(B == null) return false;

        try(Connection conn = getConnection())
        {
            ps = null;
            rs = null;

            ps = conn.prepareStatement(UPDATE_BOOK);
            ps.setString(1,B.getTitle());
            ps.setString(2,B.getAuthor());
            ps.setString(3,B.getTitle());

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    /**
     * Accept user input to add books
     * TODO: input sanitization.
     * @return true if we added any books, false if we did not.
     * @throws SQLException DB issues with the query.
     */
    public boolean addBooks() throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        String title, author, answer;
        ArrayList<Book> books = new ArrayList<>();
        boolean flag = false;

        do
        {
            out.println("Enter Book Title: ");
            title = scanner.nextLine();
            if (title.isEmpty())
            {
                return false;

            }
            out.println("Enter Book Author: ");
            author = scanner.nextLine();
            if (author.isEmpty())
            {
                return false;
            }
            Book book = new Book(title, author);
            flag = addBook(book);

            out.println("Enter more books? Enter (Y/N");
            answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("N"))
            {
               return flag;
            }

        } while(flag);

        return flag;
    }


}
