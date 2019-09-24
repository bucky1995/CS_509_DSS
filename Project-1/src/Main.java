import PostCard.model.*;
import PostCard.view.*;


import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;



public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "cache/cache.txt";
        File f = new File(filePath);
        f.delete();
        f.createNewFile();
        initialScreenGUI init = new initialScreenGUI();

    }



}
