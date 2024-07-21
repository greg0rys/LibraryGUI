package Presentation;

import Data.UserTableManager;
import Logic.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static java.lang.System.out;

public class AddUserMenu extends JFrame
{
    private JButton homeButton;
    private JButton backButton;
    private JButton clearButton;
    private JButton submitButton;
    private JTextField firstName;
    private JTextField lastName;
    private JComboBox userType;
    private JPanel root;
    private JFrame lastScreen;
    private  UserTableManager userTableManager;
    private boolean hasChanged = false; // only create a new UserTableManager if there was an addition

    // default constructor
    public AddUserMenu()
    {
        super("Add User");
        userTableManager = new UserTableManager(); // not passed in so always init because depends
        setup();
    }


    public AddUserMenu(JFrame lastFrame)
    {
        super("Add User");
        lastScreen = lastFrame;
        userTableManager = new UserTableManager();
        lastScreen.setVisible(false);
        setup();
    }

    public AddUserMenu(JFrame lastFrame, UserTableManager UTableManager)
    {
        super("Add User");
        lastScreen = lastFrame;
        userTableManager = UTableManager;
        lastScreen.setVisible(false);
        setup();
    }

    private void setup()
    {
        add(root);
        setPreferredSize(new Dimension(500, 200));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setButtonEvents();
    }


    private void setButtonEvents()
    {
        backButton.addActionListener(e->{
            try
            {
                if(hasChanged)
                {
                    out.println("Change detected running queries..");
                    new UserList(); // have it query the list again since we just added a user

                }
                else
                {
                    out.println("No change");
                    lastScreen.setVisible(true);
                }
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        submitButton.addActionListener(e->{
            if(!validateFields()) return;
            // need to implement user notifications for addition / deletion

            User temp = new User(firstName.getText(), lastName.getText(), userType.getSelectedItem().toString());
            if(userTableManager.addUser(temp)) {

                MessageDialogs.showSuccessMessage(this, "Successfully added " + temp.getfName());
                clearFields();
                hasChanged = true;

            }
            else
            {

                MessageDialogs.showErrorMessage(this, "Error adding " + temp.getfName());
                hasChanged = false; // make sure previous truths don't accidently make a positive.
                clearFields();
            }

        });
    }

    private boolean validateFields()
    {
        return !firstName.getText().isEmpty() && !lastName.getText().isEmpty();
    }

    private void clearFields()
    {
        firstName.setText("");
        lastName.setText("");
        userType.setSelectedIndex(0);
    }
}
