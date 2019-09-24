package PostCard.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import PostCard.model.*;
import org.json.JSONException;
import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class editCardGUI extends JFrame {
    private JPanel Mainview;
    private JTabbedPane pages;
    private JPanel Front;
    private JPanel inner_Left;
    private JPanel inner_Right;
    private JPanel Back;
    private JButton addTextButton;
    private JButton addPicButton;
    private JList frontList;
    private JList leftList;
    private JList rightList;
    private JList BackList;
    private JTextArea inputArea;
    private JComboBox pic_list;
    private JTextField TBound1;
    private JTextField TBound2;
    private JTextField TPos1;
    private JTextField TPos2;
    private JComboBox font_list;
    private JTextField PBound1;
    private JTextField PBound2;
    private JTextField PPos1;
    private JTextField PPos2;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton quitButton;
    private JPanel Left;
    private JButton TupdateButton;
    private JButton PupdateButton;
    private JButton loadButton;
    private JButton deleteButton;
    private JLabel test;

    private card c;

    public editCardGUI(card cin) {
        c = cin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 800,400);
        setResizable(false);
        getContentPane().add(this.Mainview);
        Mainview = new JPanel();
        Mainview.setLayout(new FlowLayout());
        String [] fontList = {"Comic Sans MS","Lucida Calligraphy","Times New Roman"};
        for(int i=0;i<fontList.length;i++){
            font_list.addItem(fontList[i]);
        }
        String[] picList = {"flower","ballon","cake"};
        for(int i=0;i<picList.length;i++){
            pic_list.addItem(picList[i]);
        }
        for(int i=0;i<=3;i++){
            updatePage(i);
        }
        setVisible(true);


        frontList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String select = frontList.getSelectedValue().toString();
                updateCompoenet(select);
            }
        });
        addTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                addText(page);
            }
        });
        addPicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                addPic(page);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                deleteE(page);

            }
        });
        TupdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                try {
                    updateTE(page);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        });
        PupdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                updatePE(page);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                setVisible(false);
                loadCardGUI lc = new loadCardGUI();
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
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                try {
                    copy(page);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int page = pages.getSelectedIndex();
                String filePath = "cache/cache.txt";
                File f = new File(filePath);
                Scanner scanner = null;
                try {
                    scanner = new Scanner( f );
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                String ele= scanner.nextLine();
                if(ele.equals("")){
                    JOptionPane.showMessageDialog(null, "no cached Visual element", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
                try {
                    c.addVE(page,ele);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                updatePage(page);
            }
        });
    }

    private void updateCompoenet(String c){
        clearText();
        ArrayList<String> config = breakE(c);
        if(config.get(0).equals("0")){//update text area
            inputArea.setText(config.get(1));
            font_list.setSelectedItem(config.get(2));
            TBound1.setText(config.get(3));
            TBound2.setText(config.get(4));
            TPos1.setText(config.get(5));
            TPos2.setText(config.get(6));
        }else{//update pic area
            pic_list.setSelectedItem(config.get(1));
            PBound1.setText(config.get(2));
            PBound2.setText(config.get(3));
            PPos1.setText(config.get(4));
            PPos2.setText(config.get(5));
        }
    }

    private void updatePage(int page){
        ArrayList<String> list = new ArrayList<String>();
        DefaultListModel tempList = new DefaultListModel();
        switch (page){
            case 0: {
                list = c.front.getE();
                clearText();
                for(int i=0;i<list.size();i++){
                    tempList.addElement(list.get(i));
                }
                frontList.setModel(tempList);
                break;
            }
            case 1: {
                list = c.i_left.getE();clearText();
                for(int i=0;i<list.size();i++){
                    tempList.addElement(list.get(i));
                }
                leftList.setModel(tempList);
                break;
            }
            case 2:{
                list = c.i_right.getE();clearText();
                for(int i=0;i<list.size();i++){
                    tempList.addElement(list.get(i));
                }
                rightList.setModel(tempList);
                break;
            }
            case 3:{
                list = c.back.getE();clearText();
                for(int i=0;i<list.size();i++){
                    tempList.addElement(list.get(i));
                }
                BackList.setModel(tempList);
                break;
            }
            default:break;
        }
    }

    private ArrayList<String> breakE(String e){
        String[] ebreak = e.split("-");
        ArrayList<String> res=new ArrayList<String>();
        if(ebreak[0].equals("flower")||ebreak[0].equals("ballon")||ebreak[0].equals("cake")){
            res.add("1");//pic element
        }else{
            res.add("0");//text element
        }
        for(int i=0;i<ebreak.length;i++){
            res.add(ebreak[i]);
        }
        return res;
    }

    private void clearText(){
        inputArea.setText("");
        TBound1.setText("");
        TBound2.setText("");
        TPos1.setText("");
        TPos2.setText("");
        PBound1.setText("");
        PBound2.setText("");
        PPos1.setText("");
        PPos2.setText("");
    }

    private void addText(int page){
        if(page == 3){
            JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String vT = inputArea.getText();
        vT+="-";
        inputArea.setText("");
        vT+=font_list.getSelectedItem().toString();
        vT+="-";
        vT+=TBound1.getText();
        vT+="-";
        vT+=TBound2.getText();
        vT+="-";
        vT+=TPos1.getText();
        vT+="-";
        vT+=TPos2.getText();
        try {
            c.addVE(page,vT);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        updatePage(page);
    }

    private void addPic(int page){
        if(page == 3){
            JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String vP = pic_list.getSelectedItem().toString();
        vP+="-";
        vP+=PBound1.getText();
        vP+="-";
        vP+=PBound2.getText();
        vP+="-";
        vP+=PPos1.getText();
        vP+="-";
        vP+=PPos2.getText();

        try {
            c.addVE(page,vP);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        updatePage(page);
    }

    private void updateTE(int page) throws IOException, JSONException {
        if(page == 3){
            JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String element = "";
        switch(page){
            case 0:{
                element = frontList.getSelectedValue().toString();
                break;
            }
            case 1:{
                element = leftList.getSelectedValue().toString();
                break;
            }
            case 2:{
                element = rightList.getSelectedValue().toString();
                break;
            }
            case 3:{
                JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        String vT = inputArea.getText();
        vT+="-";
        inputArea.setText("");
        vT+=font_list.getSelectedItem().toString();
        vT+="-";
        vT+=TBound1.getText();
        vT+="-";
        vT+=TBound2.getText();
        vT+="-";
        vT+=TPos1.getText();
        vT+="-";
        vT+=TPos2.getText();
        try {
            c.addVE(page,vT);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        try {
            c.deleteVE(page,element);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        updatePage(page);
    }

    private void updatePE(int page){
        if(page == 3){
            JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String element = "";
        switch(page){
            case 0:{
                element = frontList.getSelectedValue().toString();
                break;
            }
            case 1:{
                element = leftList.getSelectedValue().toString();
                break;
            }
            case 2:{
                element = rightList.getSelectedValue().toString();
                break;
            }
            case 3:{
                JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        String vP = pic_list.getSelectedItem().toString();
        vP+="-";
        vP+=PBound1.getText();
        vP+="-";
        vP+=PBound2.getText();
        vP+="-";
        vP+=PPos1.getText();
        vP+="-";
        vP+=PPos2.getText();

        try {
            c.addVE(page,vP);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        try {
            c.deleteVE(page,element);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        updatePage(page);
    }

    private void deleteE(int page){
        if(page == 3){
            JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String element = "";
        switch(page){
            case 0:{
                element = frontList.getSelectedValue().toString();
                break;
            }
            case 1:{
                element = leftList.getSelectedValue().toString();
                break;
            }
            case 2:{
                element = rightList.getSelectedValue().toString();
                break;
            }
            case 3:{
                JOptionPane.showMessageDialog(null, "back page cannot be edited", "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        try {
            c.deleteVE(page,element);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        updatePage(page);
    }

    private void copy(int page) throws IOException {
        String element = "";
        switch(page){
            case 0:{
                element = frontList.getSelectedValue().toString();
                break;
            }
            case 1:{
                element = leftList.getSelectedValue().toString();
                break;
            }
            case 2:{
                element = rightList.getSelectedValue().toString();
                break;
            }
            case 3:{
                element = BackList.getSelectedValue().toString();
                break;
            }
        }
        File cacheDir = new File("cache");
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }
        String fileName = "cache.txt";
        File file = new File(cacheDir,fileName);
        file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(element);
        fw.flush();
        fw.close();
    }

}