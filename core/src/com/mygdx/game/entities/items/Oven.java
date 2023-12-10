package com.mygdx.game.entities.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.Usable;
import com.mygdx.game.map.World;

public class Oven implements Usable {
	
	boolean isOvenClicked = false;
	
	@Override
	public void use(World world) {
		TiledMapTileLayer collisionLayer = (TiledMapTileLayer) world.getMap().getLayers().get("Wall");
		Vector3 clickCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		world.getScreen().getCamera().unproject(clickCoords);
		
		int tileX = (int) (clickCoords.x / collisionLayer.getTileWidth());
		int tileY = (int) (clickCoords.y / collisionLayer.getTileHeight());
		
		if (world.isInRange(tileX, tileY, 1)) {
			Cell clickedCell = collisionLayer.getCell(tileX, tileY);
			
			if (clickedCell != null) {
				TiledMapTile clickedTile = clickedCell.getTile();
				if (clickedTile != null && clickedTile.getProperties().containsKey("isOven")) {
					isOvenClicked = !isOvenClicked;

					if (isOvenClicked) {
						TiledMapTile ovenOn = world.getTilesetTile("tileset", 6);
						world.spawnTile(tileX, tileY, ovenOn, "Wall");
					} else {
						TiledMapTile ovenOff = world.getTilesetTile("tileset", 5);
						world.spawnTile(tileX, tileY, ovenOff, "Wall");
					}
				}
			}
		}
	}
	
	
}
