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
  private PreparedStatement ps;
  private ResultSet rs;
  private static final String UPDATE_BOOK = "UPDATE Book SET Title=?,Author=? WHERE ID=?";
  private static final String SELECT_ALL_BOOKS = "SELECT * FROM Book";
  private static final String INSERT_BOOK = "INSERT INTO Book(Title,Author) VALUES(?,?)";
  private static final String DELETE_BOOK = "DELETE FROM Book WHERE ID=?";



  /* no args constructor */
    public BookTableManager()
    {
        super();
    }


    /**
     * Get all books from the database.
     * @return ArrayList of Books.
     */
    public ArrayList<Book> getAllBooks()
    {

        try(Connection con = super.getConnection())
        {
            ArrayList<Book> books = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
                books.add(new Book(rs.getString("Title"), rs.getString("Author"), rs.getInt("ID")));
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

            return ps.executeUpdate() >= 1;

        }
        catch (SQLException e)
        {
            out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Takes whatever the values are for the Book and updates them in the DB
     *
     * @param B
     * @return true if updated false when errors occur.
     */
    public boolean updateBook(Book B, int bookID) throws SQLException
    {

        try (Connection conn = getConnection())
        {
            if (B == null) return false;

            ps = conn.prepareStatement(UPDATE_BOOK);
            ps.setString(1, B.getTitle());
            ps.setString(2, B.getAuthor());
            ps.setInt(3, bookID);

            return (ps.executeUpdate()  == 1);
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public boolean removeBook(int bookID)
    {
        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(DELETE_BOOK);
            ps.setInt(1, bookID);
            return ps.executeUpdate() == 1;
        }
        catch(SQLException e)
        {
            out.println(e.getMessage());
            return false;
        }
    }


}
