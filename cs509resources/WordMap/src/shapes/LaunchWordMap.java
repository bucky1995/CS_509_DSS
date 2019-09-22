package shapes;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import shapes.model.*;
import shapes.view.*;

/**
 * Class to launch the WordMap Application.
 */
public class LaunchWordMap {
	
	/** Where board's persistent data is stored across executions. */
	static final String defaultStorage = "Wordmap.storage";
	
	/** Where initial words are drawn from . */
	static final String defaultWordList = "WordList.txt";
	
	/**
	 * Loads words from {@link #defaultWordList} file and randomly places wordshapes
	 * within the lower half of the board.
	 *
	 * Returns the Board properly intialized with the original words.
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
			
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			while (sc.hasNext()) {
				String word = sc.nextLine();
				
				int x = baseX + r.nextInt(width);
				int y = baseY + r.nextInt(height);
				WordShape ws = new WordShape(x, y, new WordDrawer(), word);
				shapes.add(ws);
			}
			
			BoardMemento memento = new BoardMemento(shapes);
			board = new Board();
			board.restore(memento);
		} catch (FileNotFoundException e) {
			System.err.println("Unable to initialize from: " + defaultWordList);
		}

		if (sc != null) { sc.close(); }
		return board;
	}
	
	/**
	 * Store board to file identified by string.
	 * 
	 * @param b            Board to be stored
	 * @param location     Location on disk where to store board.
	 */
	static void storeState(Board b, String location) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(location));
			oos.writeObject(b.getState());
		} catch (Exception e) {
			System.err.println("Unable to store state:" + e.getMessage());
		}
		
		if (oos != null) {
			try { oos.close(); } catch (IOException ioe) { } 
		}
		
	}
	
	/**
	 * Load up storage, if exists; otherwise initialize from word list.
	 * 
	 * If unable to load up state, then returns null.
	 * 
	 * @param location    Location on disk where board was stored
	 * @return            Board object fully configured with prior state
	 */
	static Board loadState(String location) {
		 ObjectInputStream ois = null;
		 Board b = new Board();
		 try {
			 ois = new ObjectInputStream (new FileInputStream(location));
			 BoardMemento m = (BoardMemento) ois.readObject();
			 ois.close();
			 b.restore(m);
		 } catch (Exception e) {
			 // unable to perform restore. 
			 System.err.println("Unable to load state from:" + location);
			 b = null;
		 }
		 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }
		 
		 return b;
	}
	
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		System.out.println ("Welcome to MY WordMap!");
		
		// Create the entity objects that form the basis of our model
		Board b = loadState(defaultStorage);
		if (b == null) {
			b = initializeWords(ApplicationPanel.WordPanelSize);
		}
		if (b == null) {
			System.err.println("Unable to launch WordMap");
			System.exit(-1);
		}
		
		final Model model = new Model(b);
		
		System.out.println ("Model constructed");
		
		// In some cases we can only construct the view once model 
		// is available. In other cases, we can construct the View 
		// object and then configure it later with the model object. 
		// In this example, we show the former. 
		final Application pr = new Application(model);
		
		// This controller is an anonymous class who responds to closing 
		// events by exiting the application.
		pr.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				storeState(model.getBoard(), defaultStorage);
				System.exit(0);
			}
		});
			
		// launch everything and go!
		pr.setVisible (true);
	}
}
