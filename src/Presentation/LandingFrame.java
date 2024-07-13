package Presentation;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.out;

public class LandingFrame extends JFrame
{
    private JButton loginButton;
    private JButton searchButton;
    private JRadioButton searchRadioButton;
    private JRadioButton memberRadioButton;
    private JRadioButton bookMenuRadioButton;
    private JLabel name;
    private JPanel root;
    private JFrame lastScreen = null;

    public LandingFrame()
    {
        super("Home Page");
        setup(true);

    }

    /**
     * Create a new Landing Frame with the ability to control visibility.
     * @param display true if we wish to set visible false if else.
     */
    public LandingFrame(boolean display)
    {
        super("Home Page");
        setup(display);
    }

    /**
     * Create new landing frame with the previous frame stored
     * @param previousFrame the frame that was displayed before this one.
     */
    public LandingFrame(JFrame previousFrame)
    {
        super("Home Page");
        lastScreen = previousFrame;
        setup(true);
    }

    private void radioButtonEvents()
    {
        searchRadioButton.addActionListener(e ->
                                            {
                                                memberRadioButton.setSelected(false);
                                                bookMenuRadioButton.setSelected(false);
                                                dispose();
                                            });

        memberRadioButton.addActionListener(e ->
                                       {
                                           searchRadioButton.setSelected(false);
                                           bookMenuRadioButton.setSelected(false);
                                           new MemberMenu();
                                           dispose();

                                       });

        bookMenuRadioButton.addActionListener(e ->
                                       {
                                           searchRadioButton.setSelected(false);
                                           memberRadioButton.setSelected(false);
                                           dispose();
                                       });
    }

    private void setup(boolean display)
    {
        add(root);
        setPreferredSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(display);
        pack();
        radioButtonEvents();
    }

    private JFrame getLastScreen()
    {
        return lastScreen;
    }
}
