package shapes.controller;

import shapes.model.Model;
import shapes.view.ApplicationPanel;

public class RedoController {
	Model model;
	ApplicationPanel canvas;
	
	public RedoController(Model m, ApplicationPanel canvas) {
		this.model = m;
		this.canvas = canvas;
	}
	
	public boolean process() {
		Move m = model.removeRedoMove();
		if (m == null) { return false; }
		
		if (m.execute()) {
			model.recordRedoneMove(m);
		}
		
		// force board to redraw
		canvas.redraw();
		canvas.repaint();
		return true;
	}
}

