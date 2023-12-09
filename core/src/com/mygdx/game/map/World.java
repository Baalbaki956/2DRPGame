package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.entities.Player;
import com.mygdx.game.screens.GameScreen;

public class World implements Disposable{

	private GameScreen screen;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private float scale = 2f;
	
	// Entiites
	private Player player;
	
	public World(GameScreen screen) {
		this.screen = screen;
		
		map = new TmxMapLoader().load("xml/map.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map /*, scale*/);
		
		player = new Player(map, 5, 5);
	}
	
	public void render() {
		mapRenderer.render();
		
		player.render(screen.getGame().getSpriteBatch());
		screen.getCamera().position.set(player.getX() * 32, player.getY() * 32, 0);
	}

	public OrthogonalTiledMapRenderer getMapRenderer() {
		return mapRenderer;
	}

	@Override
	public void dispose() {
		map.dispose();
		mapRenderer.dispose();
	}
}
