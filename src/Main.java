import Data.SuperDB;
import Presentation.LandingFrame;
import Data.BookTableManager;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;

public class Main
{
    public static void main(String[] args)
    {


        javax.swing.SwingUtilities.invokeLater(()->{
            new LandingFrame();
        });

    }
}