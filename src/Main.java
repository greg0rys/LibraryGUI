import Data.SuperDB;
import Logic.BookManager;
import Presentation.BookList;
import Presentation.LandingFrame;
import Data.BookTableManager;
import Presentation.UserList;

import javax.swing.*;
import java.sql.*;
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
            try {
                new UserList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}