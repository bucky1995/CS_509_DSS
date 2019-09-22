package distributed.view;

import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Label;

import distributed.controller.*;
import distributed.controller.client.BoxController;
import distributed.controller.client.TextController;
import distributed.controller.client.ValueController;
import distributed.model.*;

/**
 * The containing Frame in which the views are found.
 * 
 * Note how all methods are 'package private' to allow a class within the same package
 * to be able to access the GUI elements to install controllers (as needed) to respond
 * to events. 
 * 
 * The view is typically unable to know the full set of controllers which may be desired
 * and so we defer that logic to an external class.
 * 
 * I've added exclusive button to request access.
 */
public class ApplicationPanel extends Panel implements IModelUpdated {
	
	// GUI widgets for displaying information.
	ControlPanel controlPanel = null;
	DrawingCanvas boxView = null;
	TextPanel textView = null;
	ServerPanel serverPanel = null;

	/**
	 * Construct ApplicationPanel with a Model instance used for information.
	 */
	public ApplicationPanel() {
		super();
		
		initialize();
	}

	/**
	 * Initialize frame by adding all views
	 */
	void initialize() {
		Label label1 = new Label();
		label1.setBounds(new Rectangle(388, 260, 102, 23));
		label1.setText("Text Panel");
		Label label2 = new Label();
		label2.setBounds(new Rectangle(340, 41, 150, 23));
		label2.setText("Drawing Canvas");
		Label label3 = new Label();
		label3.setBounds(new Rectangle(30, 41, 150, 23));
		label3.setText("Controller View");
		
		setLayout(null);
		setSize(633, 490);
		
		// Add view and register controllers.
		add(getControlPanel());
		Model model = Model.getInstance();
		getControlPanel().getScrollbarH().addAdjustmentListener(new ValueController(model.getHeight(), this));
		getControlPanel().getScrollbarW().addAdjustmentListener(new ValueController(model.getWidth(), this));
		getControlPanel().getScrollbarC().addAdjustmentListener(new ValueController(model.getColor(), this));
		
		// add view and register controllers
		add(getBoxView());
		BoxController bc = new BoxController(model, this);
		getBoxView().addMouseListener(bc);
		getBoxView().addMouseMotionListener(bc);	
		
		// add view and register controllers
		add(getTextView());
		getTextView().getTextFieldH().addActionListener(new TextController (model.getHeight(), this));
		getTextView().getTextFieldW().addActionListener(new TextController (model.getWidth(), this));
		getTextView().getTextFieldC().addActionListener(new TextController (model.getColor(), this));
		
		// Add server controller
		add(getServerPanel());
		
		add(label1);
		add(label2);
		add(label3);
	}
	
	/** 
	 * Instantiate and/or return the scrollbar control view. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public ControlPanel getControlPanel() {
		if (controlPanel == null) {
			controlPanel = new ControlPanel();
			controlPanel.setBounds(new Rectangle(15, 70, 256, 148));
		}
		return controlPanel;
	}
	
	/** 
	 * Instantiate and/or return the scrollbar control view. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public ServerPanel getServerPanel() {
		if (serverPanel == null) {
			serverPanel = new ServerPanel();
			serverPanel.setBounds(new Rectangle(15, 290, 256, 148));
		}
		return serverPanel;
	}
	
	/**
	 * Instantiate and/or return the box control view. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public DrawingCanvas getBoxView() {
		if (boxView == null) {
			boxView = new DrawingCanvas();
			boxView.setBounds(new Rectangle(300, 70, 140, 140));
		}
		return boxView;
	}

	/**
	 * Instantiate and/or return the text view. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public TextPanel getTextView() {
		if (textView == null) {
			textView = new TextPanel ();
			textView.setBounds(new Rectangle(300, 290, 289, 145));
		}
		
		return textView;
	}

	/** Whenever model changes, all views need to be updated. */
	public void modelChanged() {
		getTextView().modelChanged();
		getControlPanel().modelChanged();
		getBoxView().modelChanged();
	}
	
} 
