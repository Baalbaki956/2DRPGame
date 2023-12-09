package com.mygdx.game.ui;

import com.mygdx.game.ui.base.Panel;

public class InventoryPanel extends Panel {

	public InventoryPanel(int startX, int startY) {
		super(startX, startY, "Inventory");
	}

	@Override
	public void dispose() {
		this.panel.dispose();
		this.font.dispose();
	}

}
