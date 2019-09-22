package timer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class StartTimerController implements ActionListener {

	TimerFrame app;
	
	public StartTimerController(TimerFrame timerFrame) {
		this.app = timerFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyTimer t = MyTimer.getInstance();
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// HACK. Should be in a model someplace
				int val = Integer.valueOf(app.getTimerLabel().getText());
				val += 1;
				app.getTimerLabel().setText("" + val);
			}
			
		};
		
		t.schedule(task, 1000);
		app.getStartButton().setEnabled(false);
		app.getStopButton().setEnabled(true);
	}

}
