package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

	private Texture texture;
	private int x, y, width, height;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.texture = new Texture("entities/player.png");
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, x * 32, y * 32);

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			move(0, 1);
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			move(0, -1);
		}

		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			move(-1, 0);
		}

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			move(1, 0);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.T)) {
			teleport(0, 0);
		}
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
