package delegation;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardPanel extends JPanel {
	public BoardPanel() {
		setLayout(new GridLayout(4, 4, 0, 0));
		
		JButton btnNewButton_2 = new Square(1);
		add(btnNewButton_2);
		
		JButton btnNewButton_9 = new Square(5);
		add(btnNewButton_9);
		
		JButton btnNewButton_8 = new Square(2);
		add(btnNewButton_8);
		
		JButton btnNewButton_4 = new Square(3);
		add(btnNewButton_4);
		
		JButton btnNewButton_11 = new Square(4);
		add(btnNewButton_11);
		
		JButton btnNewButton_1 = new Square(6);
		add(btnNewButton_1);
		
		JButton btnNewButton_5 = new Square(1);
		add(btnNewButton_5);
		
		JButton btnNewButton_12 = new Square(4);
		add(btnNewButton_12);
		
		JButton btnNewButton_3 = new Square(3);
		add(btnNewButton_3);
		
		JButton btnNewButton_6 = new Square(3);
		add(btnNewButton_6);
		
		JButton btnNewButton= new Square(4);
		add(btnNewButton);
		
		JButton btnNewButton_7 = new Square(2);
		add(btnNewButton_7);
		
		JButton btnNewButton_10 = new Square(4);
		add(btnNewButton_10);
		
		JButton btnNewButton_13 = new Square(2);
		add(btnNewButton_13);
		
		JButton btnNewButton_14 = new Square(2);
		add(btnNewButton_14);
		
		JButton btnNewButton_15 = new Square(1);
		add(btnNewButton_15);
	}

}
