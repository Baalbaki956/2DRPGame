package com.mygdx.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.entities.Lever;
import com.mygdx.game.entities.Oven;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Usable;
import com.mygdx.game.screens.GameScreen;

public class world implements Disposable {

	private GameScreen screen;
	private static TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private float scale = 2f;
	
	private Usable oven;
	private Usable lever;

	// Entiites
	private static Player player;

	public world(GameScreen screen) {
		this.screen = screen;

		map = new TmxMapLoader().load("xml/map.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map /* , scale */);

		oven = new Oven();
		lever = new Lever();
		player = new Player(map, 0, 0);
	}

	public void render() {
		mapRenderer.render();

		player.render(screen.getGame().getSpriteBatch());
		screen.getCamera().position.set(player.getX() * 32, player.getY() * 32, 0);

		if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			oven.use(this);
			lever.use(this);
		}
	}

	public OrthogonalTiledMapRenderer getMapRenderer() {
		return mapRenderer;
	}
	
	public TiledMap getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}
	
	public GameScreen getScreen() {
		return screen;
	}
	
	public boolean isInRange(int tileX, int tileY, int rangeInTiles) {
		int distanceX = Math.abs(tileX - player.getX());
		int distanceY = Math.abs(tileY - player.getY());

		return distanceX <= rangeInTiles && distanceY <= rangeInTiles;
	}
	
	public TiledMapTile getTilesetTile(int index) {
		return getMap().getTileSets().getTileSet("tileset").getTile(index);
	}
	
	public void spawnTile(int x, int y, TiledMapTile newTile, String layer) {
		Cell newCell = new Cell();
		newCell.setTile(newTile);
		((TiledMapTileLayer) getMap().getLayers().get(layer)).setCell(x, y, newCell);
	}
	
	@Override
	public void dispose() {
		map.dispose();
		mapRenderer.dispose();
	}
}
