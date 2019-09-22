package shapes.controller;

import shapes.controller.moves.HConnectShape;
import shapes.model.Model;
import shapes.model.Shape;
import shapes.view.Application;
import shapes.view.ShapeDrawer;

public class HorizontalConnectShapeController extends ConnectShapeController {

	public HorizontalConnectShapeController(Model model, Application app) {
		super(model, app);
	}

	@Override
	protected boolean takeAction (Shape selected, int x, int y) {
		if (originalTarget != null) {
			HConnectShape move = new HConnectShape(model, selected, originalx, originaly, originalTarget, x, y);
			if (move.execute()) {
				originalTarget.getDrawer().setState(ShapeDrawer.StateNormal);
				
				model.recordMove(move);
				return true;
			}
		} 
		
		return false; 
	}
	
}
