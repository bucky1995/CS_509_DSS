package bowling;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import bowling.view.GameView;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		IBowling model = new bowlingam.Game();
		final GameView frame = new GameView(model);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}      
		});
	}

}
