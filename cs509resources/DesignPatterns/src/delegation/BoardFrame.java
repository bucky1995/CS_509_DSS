package delegation;

import javax.swing.JFrame;

public class BoardFrame extends JFrame {
	public BoardFrame() {
		setSize(600, 600);
		getContentPane().setLayout(null);
		
		BoardPanel panel = new BoardPanel();
		panel.setBounds(10, 10, 400, 400);
		getContentPane().add(panel);
		
	}
	
	public static void main(String[] args) {
		BoardFrame bf = new BoardFrame();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}
}
