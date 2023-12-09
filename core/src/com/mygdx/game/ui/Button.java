package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Button {

	public interface ClickListener {
        void onClick(Button button);
    }
	
	protected BitmapFont font;
	private Texture btnBackground;
	private Texture btnHovered;
	private Rectangle rectangle;
	private String title;
	private Vector3 mousePos;
	private ClickListener clickListener;
	
	public Button(String title, int x, int y) {
		font = new BitmapFont();
		this.title = title;
		
		this.btnBackground = new Texture("button_background.png");
		this.btnHovered = new Texture("button_hovered.png");
		
		this.rectangle = new Rectangle(x, y, 128, 32);
		
		this.mousePos = new Vector3();
	}
	
	public void render(SpriteBatch batch, Camera camera) {
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);
		
		batch.draw(btnBackground, rectangle.x, rectangle.y);
		
		if (rectangle.contains(mousePos.x, mousePos.y)) {
			batch.draw(btnHovered, rectangle.x, rectangle.y);
		}
		font.draw(batch, title, rectangle.x + 40, rectangle.y + 22);
		
		if (Gdx.input.isButtonJustPressed(0) && rectangle.contains(mousePos.x, mousePos.y) && clickListener != null) {
			clickListener.onClick(this);
        } else {
        }
	}
	
	public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
