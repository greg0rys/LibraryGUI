package Presentation;

import Data.UserTableManager;
import Helpers.userNode;
import Logic.User;

import javax.swing.*;

public class EditUserMenu extends JFrame
{
    private JButton homeButton;
    private JButton backButton;
    private JTextField fName;
    private JTextField lName;
    private JComboBox userType;
    private JPanel root;
    private JButton deleteUserButton;
    private JButton saveButton;
    private JLabel fNameL;
    private JLabel lastNameL;
    private JLabel userTypeL;
    private JPanel navContainer;
    private JPanel formContainer;
    private JFrame lastScreen; // all must pass these so we can implement a "back" button
    private UserTableManager userTableManager;
    private User U;
    private userNode userNode;

    public EditUserMenu(JFrame lastFrame){}

    public EditUserMenu(JFrame lastFrame, UserTableManager utm, User selectedUser)
    {
        super("Edit User Form");
        userNode = new userNode(utm, selectedUser, lastFrame);

    }
}

