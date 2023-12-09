package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ui.base.Panel;

public class LogoutPanel extends Panel {

	private Button btnSubmit;
	private Button btnCancel;
	
	public LogoutPanel(int startX, int startY, String title) {
		super(startX, startY, title);
		
		this.panel = new Texture("logout_panel.png");
		btnSubmit = new Button("Submit", Gdx.graphics.getWidth() / 2 - this.getTextureWidth() / 2 + 100, Gdx.graphics.getHeight() / 2 + 6);
		btnCancel = new Button("Cancel", Gdx.graphics.getWidth() / 2 - this.getTextureWidth() / 2 + (panel.getWidth() - 200), Gdx.graphics.getHeight() / 2 + 6);
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		if (this.isPanelPressed) {
			batch.draw(panel, x, y);
			font.draw(batch, this.title, 0, 0);
		}
	}
	
	public void renderButton(SpriteBatch batch, Camera camera, float delta) {
		render(batch, delta);
		
		if (this.isPanelPressed) {
			font.draw(batch, "Do you wish to logout?", Gdx.graphics.getWidth() / 2 - getTextureWidth() / 2 + 140, Gdx.graphics.getHeight() / 2 + 134 / 2 + 24);
			btnSubmit.render(batch, camera);
			btnCancel.render(batch, camera);
			
			btnSubmit.setClickListener(new Button.ClickListener() {

				@Override
				public void onClick(Button button) {
					Gdx.app.exit();
				}
			});
			
			btnCancel.setClickListener(new Button.ClickListener() {

				@Override
				public void onClick(Button button) {
					hide();
				}
			});
		}
	}
	
	@Override
	public void dispose() {
		this.panel.dispose();
		this.font.dispose();
	}
	
	public int getTextureWidth() {
		return panel.getWidth();
	}
}
