package Presentation;

import javax.swing.*;
import java.util.ArrayList;

public class MessageDialogs extends JFrame
{
    public MessageDialogs() {}

    /**
     * Show message dialogs for errors
     * @param frame the parent component
     * @param message the message we wish to display
     */
    public static void showErrorMessage(JFrame frame, String message)
    {

        JOptionPane.showMessageDialog(frame,
                                      message,
                                      "ERROR " + frame.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showSuccessMessage(JFrame frame, String message)
    {
        JOptionPane.showMessageDialog(frame,
                                      message,
                                      frame.getTitle() + " Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showDeleteionMessage(JFrame frame, String message)
    {
        JOptionPane.showMessageDialog(frame,
                                      message + " has been deleted",
                                      frame.getTitle() + " Deletion", JOptionPane.INFORMATION_MESSAGE);
    }
}
