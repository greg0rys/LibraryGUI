package Helpers;

import Logic.User;

import java.util.ArrayList;

public class UserCollection
{
    private ArrayList<User> emp;
    private ArrayList<User> staffList;

    public UserCollection(ArrayList<User> employees, ArrayList<User> staff)
    {
        emp = employees;
        staffList = staff;
    }

    public ArrayList<User> getEmployeeList() { return emp; }

    public ArrayList<User> getStaffList() { return staffList; }
}
