package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowListener;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		MyGame game = new MyGame();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("2DRPGGame");
		config.setMaximized(true);
		config.setWindowListener(new Lwjgl3WindowListener() {
			
			@Override
			public boolean closeRequested() {
				game.closeRequest();
				return false;
			}

			@Override
			public void created(Lwjgl3Window window) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void iconified(boolean isIconified) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void maximized(boolean isMaximized) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusGained() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void filesDropped(String[] files) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void refreshRequested() {
				// TODO Auto-generated method stub
				
			}
		});
		new Lwjgl3Application(game, config);
	}
}
