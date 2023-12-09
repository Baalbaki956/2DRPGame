package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class Icon implements Disposable{

	public interface ClickListener {
        void onClick(Icon icon);
    }
	
	private TextureAtlas atlas;
	private Sprite sprite;
	
	private Sprite background;
	private Sprite hovered;
	
	private Rectangle rectangle;
	private Vector3 mousePos;
	private boolean isPressed = false;
	
	private ClickListener clickListener;
	
	public Icon(String fileName, float x, float y, float offset) {
		atlas = new TextureAtlas(Gdx.files.internal("atlases/icons.atlas"));
		sprite = new Sprite(atlas.findRegion(fileName));

		background = new Sprite(atlas.findRegion("rounded-rectangle"));
		background.setPosition(x, y);
		hovered = new Sprite(atlas.findRegion("rounded-rectangle-hovered"));
		hovered.setPosition(x, y);
		
		this.mousePos = new Vector3();
		
		sprite.setPosition(x + offset, y + offset);
		
		this.rectangle = new Rectangle(x, y, background.getWidth(), background.getHeight());
	}
	
	public void render(SpriteBatch batch, Camera camera) {
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);

		background.draw(batch);
		if (rectangle.contains(mousePos.x, mousePos.y)) {
			hovered.draw(batch);
		}
		sprite.draw(batch);
	}
	
	public void update(float delta) {
		if (Gdx.input.isButtonJustPressed(0) && rectangle.contains(mousePos.x, mousePos.y) && clickListener != null) {
			clickListener.onClick(this);
			isPressed = true;
        } else {
        	isPressed = false;
        }
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

	@Override
	public void dispose() {
		atlas.dispose();
		sprite.getTexture().dispose();
		background.getTexture().dispose();
		hovered.getTexture().dispose();
	}

}
