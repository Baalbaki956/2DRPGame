package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Player {

	private Texture texture;
	private TiledMap map;
	private int x, y, width, height;

	public Player(TiledMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
		this.texture = new Texture("entities/player.png");
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, x * 32, y * 32);

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (canMove(0, 1)) {
				move(0, 1);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (canMove(0, -1)) {
				move(0, -1);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (canMove(-1, 0)) {
				move(-1, 0);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (canMove(1, 0)) {
				move(1, 0);
			}
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
	
	private boolean canMove(int dx, int dy) {
	    int newX = x + dx;
	    int newY = y + dy;

	    // Get the collision layer
	    TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);

	    
	    // Check each corner of the player's bounding box
	    for (int i = 0; i < 2; i++) {
	        for (int j = 0; j < 2; j++) {
	            Cell cell = collisionLayer.getCell((int) ((newX + i * width) / collisionLayer.getTileWidth()), (int) ((newY + j * height) / collisionLayer.getTileHeight()));
	    	    boolean canPass = Boolean.valueOf((TiledMapTileLayer) collisionLayer.getCell(
	    	    		(int) ((newX + i * width) / collisionLayer.getTileWidth()), 
	    	    		(int) ((newY + j * height) / collisionLayer.getTileHeight())).getTile().getProperties().get("isBlocked"));

	    	    if (canPass == true) {
	    	    	return true;
	    	    }
	    	    return false;
//	            if (cell != null) {
//	    	        System.out.println("Checking cell at (" + (newX + i) + "," + (newY + j) + ") - isBlocked: " + cell.getTile().getProperties().get("isBlocked"));
//	    	    }
//	            
//	            if (cell != null && (boolean)cell.getTile().getProperties().containsKey("isBlocked")) {
//	            	System.out.println("Checking cell at (" + i + "," + j + ") - isBlocked: " + (cell != null && (boolean) cell.getTile().getProperties().get("isBlocked")));
//	                return false;
//	            }
	        }
	    }

	    return true;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
