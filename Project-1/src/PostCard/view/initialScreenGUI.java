package PostCard.view;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class initialScreenGUI extends JFrame{
    private JButton newButton;
    private JButton loadButton;
    private JPanel Mainview;
    private JButton quitButton;

    public initialScreenGUI() {
        //super("123");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 300,100);
        setResizable(false);
        getContentPane().add(this.Mainview);
        Mainview = new JPanel();
        Mainview.setLayout(new FlowLayout());
        setVisible(true);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                newCardGUI n = new newCardGUI();
                dispose();
            }
        });


        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loadCardGUI l = new loadCardGUI();
                dispose();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
    }
}
