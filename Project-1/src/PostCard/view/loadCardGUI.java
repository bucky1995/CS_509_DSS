package PostCard.view;
import PostCard.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class loadCardGUI extends JFrame{
    private JList savedCard;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton duplicateButton;
    private JButton cancelButton;
    private JPanel loadCard;

    public loadCardGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(this.loadCard);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 600, 200);
        setResizable(false);
        updateList();
        setVisible(true);
        ///return back to main screen
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                initialScreenGUI init = new initialScreenGUI();
                dispose();
            }
        });
        //load from existing card
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                String fileName;
                if(savedCard.getSelectedIndex()!=-1){
                    fileName = savedCard.getSelectedValue().toString();
                    String filePath = "archive/"+fileName;
                    File f = new File(filePath);
                    Scanner input = null;
                    try {
                        input = new Scanner(f);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    List<String> lines = new ArrayList<String>();

                    while (input.hasNextLine()) {
                        lines.add(input.nextLine());
                    }
                    card c = new card(Integer.parseInt(lines.get(2)), lines.get(0), lines.get(1));
                    //todo create edit screen
                    editCardGUI ec = new editCardGUI(c);
                    setVisible(false);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Please choose one card to load", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //delete one existing card
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName;
                if(savedCard.getSelectedIndex()!=-1) {
                    fileName = savedCard.getSelectedValue().toString();
                    String filePath = "archive/"+fileName;
                    File f = new File(filePath);
                    f.delete();
                    int dialogButton=JOptionPane.YES_NO_OPTION;;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Delete confirmation","Delete confirmation",JOptionPane.WARNING_MESSAGE);
                    if(dialogResult == 0){
                        f.delete();
                        JOptionPane.showMessageDialog(null, "Delete Success", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        updateList();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please choose one card to delete", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //duplicate from one existing card and change recipent name
        duplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(savedCard.getSelectedIndex()!=-1) {
                    String fileName = savedCard.getSelectedValue().toString();
                    String newRecipent = "";
                    String filePath = "archive/"+fileName;
                    File f = new File(filePath);
                    Scanner input = null;
                    try {
                        input = new Scanner(f);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    List<String> lines = new ArrayList<String>();
                    while (input.hasNextLine()) {
                        lines.add(input.nextLine());
                    }


                    while (newRecipent.isEmpty()) {
                        newRecipent = JOptionPane.showInputDialog("Please input an new recipent: ");
                        if (newRecipent.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Recipent cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                    lines.set(0,newRecipent);
                    card c = new card(Integer.parseInt(lines.get(2)), lines.get(0), lines.get(1));
                    try {
                        c.save();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    updateList();
                    JOptionPane.showMessageDialog(null, "Duplicate Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Please choose one card to duplicate", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }

    private void updateList(){
        File folder = new File("archive");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> lis = null;
        DefaultListModel tempList = new DefaultListModel();

        for(int i=0;i<listOfFiles.length;i++){
            String filename = listOfFiles[i].getName();
            if(filename.charAt(0) == '.'){
                continue;
            }
            tempList.addElement(filename);
        }
        savedCard.setModel(tempList);
    }
}
