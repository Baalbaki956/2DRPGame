package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;

public class MyGame extends Game {
	
	private SpriteBatch batch;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		batch = new SpriteBatch();
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public void closeRequest() {
		gameScreen.handleWindowClosing();
	}
}
