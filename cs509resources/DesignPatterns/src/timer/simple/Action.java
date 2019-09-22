package timer.simple;

import java.util.TimerTask;

public class Action extends TimerTask {

	String param;
	
	// note that you can pass in what you need
	public Action(String params) {
		this.param = params;
	}
	

	@Override
	public void run() {
		System.out.println("[" + param + "] Timer does something");
		
	}

}
