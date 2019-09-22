package hangman.view;
import hangman.controller.GuessLetterController;
import hangman.model.Model;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class HangmanGUI extends JFrame {

	JPanel contentPane;
	JTextField guessTextField;
	HangmanPanel hangmanPanel;
	JPanel solutionPanel;
	
	JTextField[] letters;
	
	/** Keep track of Model. */
	Model model;
	
	/**
	 * Create the frame.
	 */
	public HangmanGUI(Model m) {
		this.model = m;
		
		setTitle("Hangman Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//JPanel hangmanPanel = new JPanel();
		hangmanPanel = new HangmanPanel (m);
		
		// Make sure this panel has as many textfields as there are letters in the model.
		solutionPanel = new JPanel();
		letters = new JTextField[m.length()];
		for (int i = 0; i < m.length(); i++) {
			letters[i] = new JTextField ();
			letters[i].setColumns(1);
			letters[i].setEditable(false);
			letters[i].setFont(new Font ("Tahoma", Font.PLAIN, 18));
			solutionPanel.add(letters[i]);
		}
		
		JLabel lblGuessLetter = new JLabel("Guess Letter: ");
		
		guessTextField = new JTextField();
		guessTextField.setColumns(1);
		
//		JTextField another = new JTextField();
//		JTextField[] set = new JTextField[] { guessTextField, another } ;
//		
//		for (int i = 0; i < set.length; i++) {
//			final int j =i;
//			set[i].addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				new HelperClass(j).process();				
//			}
//			
//		});
//		}
		
		guessTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				new GuessLetterController(HangmanGUI.this, model).process();
				
			}
			
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(92)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblGuessLetter)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(guessTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(solutionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(hangmanPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(hangmanPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(solutionPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuessLetter)
						.addComponent(guessTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public HangmanPanel getPanel() {
		return hangmanPanel;
	}
	
	public JTextField getGuessTextField () {
		return guessTextField;
	}

	public JTextField getLettersField (int n) {
		return letters[n];
	}
	
	public void redrawYourself() {
		// update stick figure....
		hangmanPanel.repaint();
		
		// update letters
		for (int i = 0; i < model.length(); i++) {
			if (model.isDiscovered(i)) {
				letters[i].setText("" + model.getLetter(i));
			} else {
				letters[i].setText("");
			}
		}
		
	}

}
