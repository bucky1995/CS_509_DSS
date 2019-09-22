package timer.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TimerFrame extends JFrame {
	JLabel timerCount;
	JButton btnStart, btnStop;
	
	public JLabel getTimerLabel() {
		return timerCount;
	}
	
	public JButton getStartButton() {
		return btnStart;
	}
	
	public JButton getStopButton() {
		return btnStop;
	}
	
	public TimerFrame() {
		super();
		setSize (500, 300);
		setTitle("Sample Timer");
		getContentPane().setLayout(null);
		
		JLabel lblTimer = new JLabel("Timer:");
		lblTimer.setBounds(36, 51, 46, 14);
		getContentPane().add(lblTimer);
		
		timerCount = new JLabel("0");
		timerCount.setBounds(75, 51, 46, 14);
		getContentPane().add(timerCount);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 209, 89, 23);
		getContentPane().add(btnStart);
		btnStart.addActionListener(new StartTimerController(this));
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(163, 209, 89, 23);
		getContentPane().add(btnStop);
		btnStop.addActionListener(new EndTimerController(this));

	}
}
