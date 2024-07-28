package Logic;

import static java.lang.System.out;

public class Book
{
    private String title;
    private String author;
    private int tableID;

    public Book(String title, String author)
    {
        this.title = title;
        this.author = author;
    }

    public Book(String bTitle, String bAuthor, int tID)
    {
        title = bTitle;
        author = bAuthor;
        tableID = tID;
    }

    public void display()
    {

        out.println("Title: " + title + "\nAuthor: " + author);
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setTitle(String T)

    {
        title = T;
    }

    public void setAuthor(String A)
    {
        author = A;
    }

    public int getTableID()
    {
        return tableID;
    }

    public void setTableID(int tID)
    {
        tableID = tID;
    }
}
