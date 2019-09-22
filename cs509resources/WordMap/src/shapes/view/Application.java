package shapes.view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import shapes.model.*;
import shapes.controller.*;

/**
 * The containing Frame for the application. This class is nothing more 
 * than a GUI shell, and the real logic happens in the ApplicationPanel.
 */
public class Application extends JFrame  {

	/**
	 * Keep Eclipse Happy.
	 */
	private static final long serialVersionUID = 7951355276092680308L;

	/** Stores reference to model, so can use at anytime during GUI navigation. */
	Model model;
	
	/** Word table. */
	WordTable table;
	
	ApplicationPanel panel;
	
	JButton undoButton;
	JButton redoButton;
	JButton vconnectButton;
	JButton hconnectButton;
	JButton disconnectButton;
	JButton moveButton;
	
	/**
	 * This is the default constructor
	 */
	public Application(Model m) {
		super();
		
		model = m;
		
		setTitle("Sample Application");
		setSize(1200, 700);
		setLayout (new FlowLayout());
		
		// menu at top
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(800, 200);
		
		undoButton = new JButton("Undo");
		menuPanel.add(undoButton);

		redoButton = new JButton("Redo");
		menuPanel.add(redoButton);
		
		vconnectButton = new JButton("Vertical Connect");
		menuPanel.add(vconnectButton);
		
		hconnectButton = new JButton("Horizontal Connect");
		menuPanel.add(hconnectButton);
		
		disconnectButton = new JButton("Disconnect");
		menuPanel.add(disconnectButton);
		
		moveButton = new JButton("Move");
		menuPanel.add(moveButton);
		
		Container pane = this.getContentPane();
		setBackground(Color.white);
		pane.add(menuPanel);  // at top
		
		// Where words appear
		panel = new ApplicationPanel(model);
		panel.setSize(ApplicationPanel.WordPanelSize);
		pane.add(panel);   // in middle
		
		// JTable on the side
		table = new WordTable(model, panel);
		pane.add(table);
		
		// This is a traditional controller acting in response to single button press
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UndoController(model, panel).process();
			}
		});
		
		// This is a traditional controller acting in response to single button press
		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RedoController(model, panel).process();
			}
		});
		
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new MoveShapeController(model, Application.this).register();
			}
		});
		
		vconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new VerticalConnectShapeController(model, Application.this).register();
			}
		});
		
		hconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new HorizontalConnectShapeController(model, Application.this).register();
			}
		});
		
		disconnectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new DisconnectController(model, Application.this).register();
			}
		});

	}

	public ApplicationPanel getWordPanel() {
		return panel;
	}


	/** Return word table object. */
	public WordTable getWordTable() { return table; }
} 
