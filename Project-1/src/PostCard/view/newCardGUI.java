package PostCard.view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import PostCard.model.*;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class newCardGUI extends JFrame{
    private JPanel newCard;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextPane Recipent;
    private JComboBox Orientation;
    private JComboBox EType;

    public newCardGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(this.newCard);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2, 600, 150);
        setResizable(false);
        String[] typeList = {
                "Anniversary", "Back to School", "Baptism and Christening", "Baby", "Bar/Bat Mitzvah",
                "Birthday", "Confirmation", "Congratulations", "Encouragement", "First Communion",
                "Get Well", "Graduation", "Retirement", "Sympathy", "Teacher Appreciation", "Thank You", "Wedding "
        };
        String[] orientationList = {
                "landscape : 900 x 600",
                "portrait : 600 x 900"
        };
        for(int i=0;i<typeList.length;i++){
            this.EType.addItem(typeList[i]);
        }
        for(int i=0;i<orientationList.length;i++){
            this.Orientation.addItem(orientationList[i]);
        }
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                initialScreenGUI init = new initialScreenGUI();
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String re = Recipent.getText();
                if(re.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Recipent cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    String type = EType.getSelectedItem().toString();
                    int or = Orientation.getSelectedIndex();
                    card c = new card(or,type,re);
                    try {
                        c.save();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }   catch (JSONException jx){
                        jx.printStackTrace();
                    }
                    try {
                        c.addVE(3,"Xinbo's Project1-Comic Sans MS-1-1-1-1");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    //todo create edit screen
                    editCardGUI ec = new editCardGUI(c);
                    setVisible(false);
                    dispose();
                }
            }
        });
    }


}
