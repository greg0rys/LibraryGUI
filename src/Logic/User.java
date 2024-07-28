package Logic;

import java.util.ArrayList;

public class User
{
    private String fName;
    private String lName;
    private int ID;
    private String userType;
    private ArrayList<Book> checkedBooks;

    public User(int id, String name, String lastname, String type, ArrayList<Book> books)
    {
        ID = id;
        lName = name;
        fName = lastname;
        userType = type;
        checkedBooks = (books == null) ? new ArrayList<>() : books; // protect against null.
    }

    public User(String firstName, String lastName, String type)
    {
        fName = firstName;
        lName = lastName;
        userType = type;
    }


    public String getfName() { return fName; }

    public String getlName() { return lName; }

    public String getUserType() { return userType; }

    public int getID() { return ID; }


    public void setfName(String newName) { fName = newName; }

    public void setlName(String newName) { newName = newName; }

    public void setUserType(String newType) { userType = newType; }

    public void setID(int newID) { ID = newID; }

    public boolean addBook(Book newBook)
    {
        return checkedBooks.add(newBook);
    }
}
