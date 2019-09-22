package distributed.view;

import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;

import distributed.model.Model;

/**
 * Use multiple text fields to represent the values of a model.
 */
public class TextPanel extends Panel implements IModelUpdated {

	/** GUI elements. */
	TextField textFieldH = null;
	TextField textFieldW = null;
	TextField textFieldC = null;
	
	/** Construct view for the given model. */
	public TextPanel () {
		super ();
		
		initialize();
	}
	
	/**
	 * Initialize the panel.
	 */
	void initialize() {
		Label labelC1 = new Label();
		labelC1.setBounds(new Rectangle(13, 94, 48, 23));
		labelC1.setText("Color:");
		Label labelW1 = new Label();
		labelW1.setBounds(new Rectangle(13, 63, 53, 23));
		labelW1.setText("Width:");
		Label labelH1 = new Label();
		labelH1.setBounds(new Rectangle(13, 26, 45, 23));
		labelH1.setText("Height:");

		setLayout(null);
		setBounds(new Rectangle(300, 290, 289, 145));
		add(getTextFieldH());
		add(getTextFieldW());
		add(getTextFieldC());
		add(labelH1);
		add(labelW1);
		add(labelC1);
	}

	/**
	 * Initialize and/or return height text field. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public TextField getTextFieldH() {
		if (textFieldH == null) {
			textFieldH = new TextField();
			textFieldH.setBounds(new Rectangle(83, 26, 109, 23));
		}
		return textFieldH;
	}

	/**
	 * Initialize and/or return width text field. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public TextField getTextFieldW() {
		if (textFieldW == null) {
			textFieldW = new TextField();
			textFieldW.setBounds(new Rectangle(83, 61, 106, 23));
		}
		return textFieldW;
	}

	/**
	 * Initialize and/or return color text field. This 
	 * method is public to make it possible to be accessible during testing.
	 */
	public TextField getTextFieldC() {
		if (textFieldC == null) {
			textFieldC = new TextField();
			textFieldC.setBounds(new Rectangle(83, 95, 106, 23));
		}
		return textFieldC;
	}

	/** Respond to changes in the underlying model. */
	public void modelChanged() {
		Model model = Model.getInstance();
		textFieldH.setText("" + model.getHeight());
		textFieldW.setText("" + model.getWidth());
		textFieldC.setText("" + model.getColor());
	}
}
