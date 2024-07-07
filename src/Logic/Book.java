package Logic;

import static java.lang.System.out;

public class Book
{
    private String title;
    private String author;

    public Book(String title, String author)
    {
        this.title = title;
        this.author = author;
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
}
