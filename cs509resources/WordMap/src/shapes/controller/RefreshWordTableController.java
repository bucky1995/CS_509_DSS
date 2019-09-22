package shapes.controller;

import shapes.view.WordTable;

public class RefreshWordTableController implements Listener {

	/** Widget to be refreshed. */
	WordTable table;
	
	public RefreshWordTableController(WordTable table) {
		this.table = table;
	}
	
	@Override
	public void update() {
		table.refreshTable();
	}
}
