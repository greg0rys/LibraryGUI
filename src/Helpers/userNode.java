package Helpers;

import Data.UserTableManager;
import Logic.User;

import javax.swing.*;
import java.sql.SQLException;

public class userNode
{
    private UserTableManager userTableManager;
    private User user;
    private JFrame lastFrame;

    public userNode(UserTableManager userTManager, User u, JFrame lastScreen)
    {
        userTableManager = userTManager;
        user = u;
        lastFrame = lastScreen;
    }

    public boolean deleteUser(User U) throws SQLException
    {
        return userTableManager.deleteUser(U);
    }
}
