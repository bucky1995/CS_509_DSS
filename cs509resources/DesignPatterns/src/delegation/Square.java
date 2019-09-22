package delegation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;

public class Square extends JButton {
	Color colors[] = { Color.white, Color.yellow, Color.pink, Color.orange, Color.cyan, Color.magenta, Color.green };
	
	public Square (int value) {
		this.setFont(new Font("MS Comic Sans", Font.PLAIN, 48));
		setValue(value);
	}
	
	public void setValue(int v) {
		this.setText("" + v);
		this.setBackground(colors[v]);
	}
	
	public int getValue() {
		return Integer.valueOf(getText());
	}
}
