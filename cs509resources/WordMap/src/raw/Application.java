package raw;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


/**
 * The containing Frame for the application. This class is nothing more 
 * than a GUI shell, and the real logic happens in the ApplicationPanel.
 */
public class Application extends JFrame  {

	/**
	 * Keep Eclipse Happy.
	 */
	private static final long serialVersionUID = 7951355276092680308L;

	/** Where initial words are drawn from . */
	static final String defaultWordList = "WordList.txt";

	/**
	 * This is the default constructor
	 */
	public Application(Board board) {
		super();
		
		setTitle("Sample Application");
		setSize(1200, 700);
		setLayout (new FlowLayout());
		
		init(board, this.getContentPane());
	}
	
	// can be used in Applet also.
	public static void init(Board board, Container pane) {
		
		// menu at top
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(800, 200);
		
		JButton undoButton = new JButton("Undo");
		menuPanel.add(undoButton);

		JButton redoButton = new JButton("Redo");
		menuPanel.add(redoButton);
		
		JButton vconnectButton = new JButton("Vertical Connect");
		menuPanel.add(vconnectButton);
		
		JButton hconnectButton = new JButton("Horizontal Connect");
		menuPanel.add(hconnectButton);
		
		JButton disconnectButton = new JButton("Disconnect");
		menuPanel.add(disconnectButton);
		
		JButton moveButton = new JButton("Move");
		menuPanel.add(moveButton);
		
		pane.add(menuPanel);  // at top
		
		// Where words appear
		BoardPanel panel = new BoardPanel(board);
		panel.setSize(BoardPanel.WordPanelSize);
		pane.add(panel);   // in middle
		
		// JTable on the side
		WordTable table = new WordTable(board, panel);
		pane.add(table);
		
		final WordMapAdapter adapt = new WordMapAdapter(board, panel, table);
		panel.addMouseListener(adapt);
		panel.addMouseMotionListener(adapt);
		
		
		// This is a traditional controller acting in response to single button press
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
			}
		});
		
		// This is a traditional controller acting in response to single button press
		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
			}
		});
		
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adapt.setMode(WordMapAdapter.MOVE);
			}
		});
		
		vconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adapt.setMode(WordMapAdapter.VCONNECT);
			}
		});
		
		hconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adapt.setMode(WordMapAdapter.HCONNECT);
			}
		});
		
		disconnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adapt.setMode(WordMapAdapter.DISCONNECT);
			}
		});

	}

	
	/**
	 * Loads words from {@link #defaultWordList} file and randomly places wordshapes
	 * within the lower half of the board.
	 *
	 * Returns the Board properly initialized with the original words.
	 * 
	 * @param d   size of the board to use
	 */
	static Board initializeWords(Dimension d) {
		Scanner sc = null;
		Board board = null;
		int baseX = (int) (.05 * d.width);
		int baseY = (int) (.5 * d.height); // half way down
		int width = (int) (.9 * d.width);
		int height = (int) (.45 * d.height);
		
		System.out.println(baseX + "," + baseY);
		Random r = new Random();
		try {
			// try to get from pre-packaged JAR file first
			InputStream input = d.getClass().getResourceAsStream("/" + defaultWordList);
			if (input != null) {
				sc = new Scanner (input);
			} else {
				sc = new Scanner(new File (defaultWordList));
			}
			
			board = new Board();
			while (sc.hasNext()) {
				String word = sc.nextLine();
				
				int x = baseX + r.nextInt(width);
				int y = baseY + r.nextInt(height);
				PoemShape ws = new PoemShape(x, y, new String[][] { {word}});
				board.add(ws);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Unable to initialize from: " + defaultWordList);
		}

		if (sc != null) { sc.close(); }
		return board;
	}

	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		Board b = initializeWords(BoardPanel.WordPanelSize);
		
		if (b == null) {
			System.err.println("Unable to launch WordMap");
			System.exit(-1);
		}
		
		// In some cases we can only construct the view once model 
		// is available. In other cases, we can construct the View 
		// object and then configure it later with the model object. 
		// In this example, we show the former. 
		final Application pr = new Application(b);
		
		// This controller is an anonymous class who responds to closing 
		// events by exiting the application.
		pr.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
			
		// launch everything and go!
		pr.setVisible (true);
	}
}

