package Presentation;

import Data.UserTableManager;
import Logic.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

@SuppressWarnings("unchecked")
public class UserList extends JFrame
{
    private JPanel root;
    private JList memberList;
    private JList employeeList;
    private JList adminList;
    private JButton homeButton;
    private JButton addUserButton;
    private JButton backButton;
    private JLabel memberLabel;
    private JLabel employeeLabel;
    private JLabel adminLabel;
    private JPanel navButtons;
    private JLabel memberCount;
    private JLabel employeeCount;
    private JLabel adminCount;
    private JButton editUserButton;
    private UserTableManager UTM = new UserTableManager();
    private ArrayList<User> users;
    private JFrame prevFrame;

    /* default construct */
    public UserList() throws SQLException
    {
        super("User List");
        setup();

    }


    public UserList(JFrame previousFrame) throws SQLException
    {
        super("User List");
        prevFrame = previousFrame;
        prevFrame.setVisible(false);

        setup();
    }

    /* default setup method */
    private void setup() throws SQLException
    {
        users = UTM.getUserList();
        populateLists();
        setButtonEvents();
        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);


    }

    /* populate the lists upon frame load */
    private void populateLists()
    {
        DefaultListModel<String> employeeModel = new DefaultListModel<>();
        DefaultListModel<String> memberModel = new DefaultListModel<>();
        DefaultListModel<String> adminModel = new DefaultListModel<>();



        for(User user : users)
        {
            if(user.getUserType().equals("Owner") || user.getUserType().equals("Manager"))
            {
                adminModel.addElement("[ID " + user.getID() + "] " + user.getfName() + ", " + user.getlName());

                continue; // carry on.
            }

            if(user.getUserType().equals("Employee"))
            {
                employeeModel.addElement("[ID " + user.getID() + "] " + user.getfName() + ", " + user.getlName());
                continue; // carry on.
            }

            // if we make it here then we are dealing with a member logically.
            memberModel.addElement("[ID " + user.getID() + "] " + user.getfName() + ", " + user.getlName());
        }

        employeeCount.setText("Total Employees: " + employeeModel.getSize());
        memberCount.setText("Total Members: " + memberModel.getSize());
        adminCount.setText("Total Admins: " + adminModel.getSize());

        employeeList.setModel(employeeModel);
        memberList.setModel(memberModel);
        adminList.setModel(adminModel);


    }

    private void setButtonEvents()
    {
        homeButton.addActionListener( e -> {
           new LandingFrame();
           dispose();
        });

        backButton.addActionListener( e -> {
            if(prevFrame == null) { return; }

            prevFrame.setVisible(true);
            dispose();
        });

        addUserButton.addActionListener( e -> {
           new AddUserMenu(this, UTM);
        });

        editUserButton.addActionListener( e -> {
            int memberListIndex = memberList.getSelectedIndex();
            int employeeListIndex = employeeList.getSelectedIndex();
            int adminListIndex = adminList.getSelectedIndex();
            int selectedIndex = -1;
            if (memberListIndex > -1)
            {
                selectedIndex = memberListIndex;
            }
            else if (employeeListIndex > -1)
            {
                selectedIndex = employeeListIndex;
            }
            else if (adminListIndex > -1)
            {
                selectedIndex = adminListIndex;
            }

                            User selectedUser = users.get(selectedIndex);
                new EditUserMenu(this, UTM, selectedUser);
        });

        adminList.addListSelectionListener(e->{
            if(employeeList.getSelectedIndex() > -1)
                employeeList.clearSelection();

            if(memberList.getSelectedIndex() > -1)
                memberList.clearSelection();
        });

        memberList.addListSelectionListener(e->{
            if(employeeList.getSelectedIndex() > -1)
                employeeList.clearSelection();

            if(adminList.getSelectedIndex() > -1)
                adminList.clearSelection();
        });

        employeeList.addListSelectionListener(e->{

            if(memberList.getSelectedIndex() > -1)
                memberList.clearSelection();

            if(adminList.getSelectedIndex() > -1)
                adminList.clearSelection();

        });

    }

}
