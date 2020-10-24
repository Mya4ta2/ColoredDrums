package com.colordrum;

import com.colordrum.screen.GameScreen;
import com.colordrum.screen.MenuScreen;

public class Game extends com.badlogic.gdx.Game {

    private GameScreen gameScreen;
    private MenuScreen menuScreen;

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }
}
