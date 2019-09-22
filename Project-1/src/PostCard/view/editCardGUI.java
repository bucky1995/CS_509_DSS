package PostCard.view;

import javax.swing.*;
import PostCard.model.*;

import java.awt.*;

public class editCardGUI extends JFrame {
    private JPanel Mainview;
    private JLabel test;

    public editCardGUI(card c) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 300,100);
        setResizable(false);
        getContentPane().add(this.Mainview);
        Mainview = new JPanel();
        Mainview.setLayout(new FlowLayout());
        setVisible(true);
        test.setText(c.recipient);
    }
}
