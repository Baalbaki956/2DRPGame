package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Player {

	private Texture texture;
	private int x, y;
	private TiledMap map;

	public Player(TiledMap map, int x, int y) {
		this.map = map;
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
			spawnTo(0, 0);
		}
	}

	private boolean isCellBlocked(int x, int y) {
		Cell cellGround = ((TiledMapTileLayer) (map.getLayers().get(0))).getCell(x, y);
		Cell cellWall = ((TiledMapTileLayer) (map.getLayers().get("Wall"))).getCell(x, y);

		if ((cellGround != null && cellGround.getTile() != null
				&& cellGround.getTile().getProperties().containsKey("isBlocked"))
				|| (cellWall != null && cellWall.getTile() != null
						&& cellWall.getTile().getProperties().containsKey("isBlocked"))) {
			return true;
		}

		return false;
	}

	public void move(int dx, int dy) {
		int newX = this.x + dx;
		int newY = this.y + dy;

		// Check for collisions before updating the position
		if (!isCellBlocked(newX, newY)) {
			x = newX;
			y = newY;
		}
	}

	public void spawnTo(int x, int y) {
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
