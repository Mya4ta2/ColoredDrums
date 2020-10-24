package com.colordrum;

import com.colordrum.screen.GameScreen;

public class Game extends com.badlogic.gdx.Game {

    private GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
