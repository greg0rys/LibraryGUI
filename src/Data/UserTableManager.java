package Data;

import Logic.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTableManager extends SuperDB
{
    private ArrayList<User> userList;
    private boolean change = false;
    private final String SELECT_ALL_USERS = "SELECT * FROM Users";

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
     * @throws SQLException connection or processing errors @ DB
     */
    public ArrayList<User> getUserList() throws SQLException
    {
        ArrayList<User> users = new ArrayList<>();

        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
                users.add(new User(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("UserType")));
            return users;
        }
        catch(Exception e)
        {
            return users;
        }
    }
}
