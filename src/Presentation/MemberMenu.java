package Presentation;

import javax.swing.*;
import java.awt.*;

public class MemberMenu extends JFrame
{
    private JPanel root;
    private JButton homeButton;
    private JButton backButton;
    private JRadioButton addNewMemberRadioButton;
    private JRadioButton findMemberRadioButton;
    private JRadioButton deactivateMemberRadioButton;


    public MemberMenu()
    {
        super("Member Menu");
        add(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setVisible(true);
        pack();
    }

}
