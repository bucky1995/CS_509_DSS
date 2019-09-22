package timer.simple;

import java.util.Timer;

public class Launch {
	public static void main(String[] args) {
		Action a = new Action("tryThis");
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(a, 1000, 1000);
	}
}
