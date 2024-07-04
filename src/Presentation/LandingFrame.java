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
    private JRadioButton radioButton3;
    private JLabel name;
    private JPanel root;

    public LandingFrame()
    {
        super("Home Page");
        add(root);
        setPreferredSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        loginButton.addActionListener(e ->{
           out.println("Clicked " + loginButton.getText());
        });

        radioButtonEvents();
    }

    private void radioButtonEvents()
    {
        searchRadioButton.addActionListener(e ->
                                            {
                                                memberRadioButton.setSelected(false);
                                                radioButton3.setSelected(false);
                                                dispose();
                                            });

        memberRadioButton.addActionListener(e ->
                                       {
                                           searchRadioButton.setSelected(false);
                                           radioButton3.setSelected(false);
                                           new MemberMenu();
                                           dispose();

                                       });

        radioButton3.addActionListener(e ->
                                       {
                                           searchRadioButton.setSelected(false);
                                           memberRadioButton.setSelected(false);
                                           dispose();
                                       });
    }
}
