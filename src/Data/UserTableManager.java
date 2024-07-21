package Data;

import Logic.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UserTableManager extends SuperDB
{
    private ArrayList<User> userList;
    private boolean change = false; // only flagged when change has happened to local data struct.
    private final String SELECT_ALL_USERS = "SELECT * FROM Users";
    private final String ADD_USER = "INSERT INTO Users(FirstName,LastName,UserType) VALUES (?,?,?)";
    private final String DELETE_USER_BY_ID = "DELETE FROM Users WHERE UserID = ?";
    private final String DELETE_BY_NAME = "DELETE FROM Users WHERE UserName = ?"; // username should be first.last
    // (modify db)

    // default
    public UserTableManager()
    {

    }

    // with existing user list.
    public UserTableManager(ArrayList<User> usersList)
    {
        userList = usersList;
    }

    /**
     * Select all users from the DB and return them as an arraylist.
     * @return ArrayList<User> all users in DB
     */
    public ArrayList<User> getUserList()
    {
        ArrayList<User> users = new ArrayList<>();

        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
                users.add(new User(rs.getInt("ID"), rs.getString("FirstName"),
                                   rs.getString("LastName"), rs.getString("UserType")));

            return users;
        }
        catch(Exception e)
        {
            return users;
        }
    }

    public boolean addUser(User user)
    {
        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(ADD_USER);
            ps.setString(1, user.getfName());
            ps.setString(2, user.getlName());
            ps.setString(3, user.getUserType());

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    /**
     * Delete a user by their first name.
     * @param user the user we wish to delete
     * @return true if deleted false if else.
     * @throws SQLException DB connectivity issues
     */
    public boolean deleteUser(User user) throws SQLException
    {
        try(Connection conn = getConnection())
        {
          PreparedStatement ps = conn.prepareStatement(DELETE_BY_NAME);
          ps.setString(1, user.getfName());
          return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}
