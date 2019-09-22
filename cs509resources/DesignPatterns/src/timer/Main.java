package timer;

import javax.swing.JFrame;

import timer.gui.TimerFrame;

public class Main {
	public static void main(String[] args) {
		TimerFrame tf = new TimerFrame();
		tf.getStopButton().setEnabled(false);
		
		tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tf.setVisible(true);
		
		
	}
}
