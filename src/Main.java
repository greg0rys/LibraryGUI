import Data.SuperDB;
import Logic.Book;
import Logic.BookManager;
import Presentation.BookAddEditForm;
import Presentation.BookList;
import Presentation.LandingFrame;
import Data.BookTableManager;
import Presentation.UserList;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            try {
                // Set look and feel to "Nimbus"
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // If Nimbus is not available, fall back to cross-platform look and feel.
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    // not much we can do here
                }
            }

//            new BookList(new BookManager().getAllBooks(), new LandingFrame(false));
//            new BookList(new BookManager().getAllBooks());
            BookManager bm = new BookManager();
            ArrayList<Book> books = bm.getAllBooks();
            new BookAddEditForm(books.get(1), bm, null, books);
        });
    }
}