package timer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndTimerController implements ActionListener {

	TimerFrame app;
	
	public EndTimerController(TimerFrame timerFrame) {
		this.app = timerFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyTimer t = MyTimer.getInstance();
		t.cancel();
		
		app.getStopButton().setEnabled(false);
		app.getStartButton().setEnabled(true);
		
	}

}
