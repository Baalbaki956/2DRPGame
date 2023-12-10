package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGame;
import com.mygdx.game.map.world;
import com.mygdx.game.ui.IconGroup;
import com.mygdx.game.ui.InventoryPanel;
import com.mygdx.game.ui.LogoutPanel;
import com.mygdx.game.ui.SkillPanel;

public class GameScreen implements Screen {

	private MyGame game;
	
	private OrthographicCamera camera;
	private OrthographicCamera uiCamera;
	
	private world world;
	private Vector3 mousePos = new Vector3();
	
	private IconGroup iconGroup;
	
	private InventoryPanel inventoryPanel;
	private SkillPanel skillPanel;
	private LogoutPanel logoutPanel;
	
	public GameScreen(MyGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		world = new world(this);
		logoutPanel = new LogoutPanel(0, 0, "");
		logoutPanel.setPosition(Gdx.graphics.getWidth() / 2 - logoutPanel.getTextureWidth() / 2, Gdx.graphics.getHeight() / 2);
		inventoryPanel = new InventoryPanel(-216, -24);
		skillPanel = new SkillPanel(-216, -24);

		iconGroup = new IconGroup(this);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		// Update world camera
		camera.update();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);
		
		// Update UI camera
        uiCamera.update();
		
        // Render world
		world.getMapRenderer().setView(camera);
		game.getSpriteBatch().setProjectionMatrix(camera.combined);
		game.getSpriteBatch().begin();
		world.render();
		
		// Render UI
        game.getSpriteBatch().setProjectionMatrix(uiCamera.combined);
		iconGroup.render(delta, uiCamera);

		inventoryPanel.render(game.getSpriteBatch(), delta);
		skillPanel.render(game.getSpriteBatch(), delta);
		logoutPanel.renderButton(game.getSpriteBatch(), uiCamera, delta);

		game.getSpriteBatch().end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {		
		iconGroup.dispose();
		inventoryPanel.dispose();
		skillPanel.dispose();
		logoutPanel.dispose();
	}
	
	public void handleWindowClosing() {
		logoutPanel.show();
	}

	public MyGame getGame() {
		return game;
	}

	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}

	public SkillPanel getSkillPanel() {
		return skillPanel;
	}

	public LogoutPanel getLogoutPanel() {
		return logoutPanel;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
}
