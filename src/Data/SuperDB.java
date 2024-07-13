package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;
import static java.lang.System.out;

public class SuperDB
{
    private final String DB_URL = "jdbc:sqlite:library.db";

    public SuperDB()
    {}

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    public void getAll() throws SQLException
    {
        try(Connection conn = getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Book");

            while(rs.next())
                out.println(rs.getString("Title"));
        }
        catch(SQLException e)
        {
            out.println(e.getMessage());
        }
    }

    public boolean seedDB()
    {
        try (Connection conn = getConnection())
        {
            for(int i = 0; i <= 5; i++)
            {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Book (Title) VALUES ('Book Title')");
            }

        }
        catch (SQLException e)
        {
            out.println(e.getMessage());
        }
        return true;
    }

}
