package shapes.controller;

import shapes.controller.moves.VConnectShape;
import shapes.model.Model;
import shapes.model.Shape;
import shapes.view.Application;
import shapes.view.ShapeDrawer;

public class VerticalConnectShapeController extends ConnectShapeController {

	public VerticalConnectShapeController(Model model, Application app) {
		super(model, app);
	}

	@Override
	protected boolean takeAction (Shape selected, int x, int y) {
		if (originalTarget != null) {
			VConnectShape move = new VConnectShape(model, selected, originalx, originaly, originalTarget, x, y);
			if (move.execute()) {
				originalTarget.getDrawer().setState(ShapeDrawer.StateNormal);
				
				model.recordMove(move);
				return true;
			}
		} 

		return false;
	}
}
