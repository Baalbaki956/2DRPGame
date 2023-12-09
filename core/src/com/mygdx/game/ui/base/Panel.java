package com.mygdx.game.ui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class Panel implements Disposable {

	protected BitmapFont font;
	protected String title;
	protected Texture panel;
	protected boolean isPanelPressed = false;
	protected int x, y; // Initial X position of the panel
	protected float panelSpeed = 600; // Adjust the speed of the animation
	
    private int textX, textY;
	
    public Panel(int startX, int startY, String title) {
    	font = new BitmapFont();
    	this.panel = new Texture("panel.png");
    	this.title = title;
    	this.x = startX;
    	this.y = startY;
    	
    	this.textX = panel.getWidth() / 2 - 24;
    	this.textY = Gdx.graphics.getHeight() - 24 - 12;
    }
    
    public void render(SpriteBatch batch, float delta) {
    	
    	if (isPanelPressed) {
			x += panelSpeed * delta;
			
			if (x >= 0) {
				x = 0;
				
				batch.draw(panel, x, y);
				font.draw(batch, title, textX, textY);
			} else {
				batch.draw(panel, x, y);
			}
			
		} else {
			
			x = -216;
		}
    }
    
    public void toggle() {
    	isPanelPressed = !isPanelPressed;
    }
    
    public boolean getPressed() {
    	return isPanelPressed;
    }

	public Texture getPanel() {
		return panel;
	}

	public void show() {
		isPanelPressed = true;
	}
	
	public void hide() {
		isPanelPressed = false;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
