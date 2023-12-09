package com.mygdx.game.ui;

import com.mygdx.game.ui.base.Panel;

public class SkillPanel extends Panel {

	public SkillPanel(int startX, int startY) {
		super(startX, startY, "Skills");
	}

	@Override
	public void dispose() {
		this.panel.dispose();
		this.font.dispose();
	}

}
