package com.mygdx.game.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.screens.GameScreen;

public class IconGroup implements Disposable {

	private Map<String, Icon> icons;
	private GameScreen screen;

	public IconGroup(final GameScreen screen) {
		this.screen = screen;

		icons = new HashMap<String, Icon>();
		addIcon("backpack", 0, Gdx.graphics.getHeight() - 24, 4);
		addIcon("writing", 24, Gdx.graphics.getHeight() - 24, 4);
		addIcon("gear", 24 * 2, Gdx.graphics.getHeight() - 24, 4);
		addIcon("logout", 24 * 3, Gdx.graphics.getHeight() - 24, 4);
		addIcon("menu", 24 * 4, Gdx.graphics.getHeight() - 24, 0);

		// Set up click listeners for each icon
		for (Icon icon : icons.values()) {
			setClickListener(icon);
		}
	}

	private void setClickListener(Icon icon) {
		if (icon == icons.get("backpack")) {
			icon.setClickListener(new Icon.ClickListener() {

				@Override
				public void onClick(Icon icon) {
					screen.getInventoryPanel().toggle();

					if (screen.getSkillPanel().getPressed() == true) {
						screen.getSkillPanel().hide();
					}
				}
			});
		}

		if (icon == icons.get("writing")) {
			icon.setClickListener(new Icon.ClickListener() {

				@Override
				public void onClick(Icon icon) {
					screen.getSkillPanel().toggle();

					if (screen.getInventoryPanel().getPressed() == true) {
						screen.getInventoryPanel().hide();
					}
				}
			});
		}

		if (icon == icons.get("logout")) {
			icon.setClickListener(new Icon.ClickListener() {

				@Override
				public void onClick(Icon icon) {
					screen.getLogoutPanel().show();
				}
			});
		}
	}

	public void addIcon(String iconName, int x, int y, int offset) {
		this.icons.put(iconName, new Icon(iconName, x, y, offset));
	}

	public void render(float delta, Camera camera) {
		for (Iterator<Icon> iter = icons.values().iterator(); iter.hasNext();) {
			iter.next().render(screen.getGame().getSpriteBatch(), camera);
		}

		for (Iterator<Icon> iter = icons.values().iterator(); iter.hasNext();) {
			iter.next().update(delta);
		}
	}

	@Override
	public void dispose() {
		for (Icon icon : icons.values()) {
			icon.dispose();
		}
	}
}
