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

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
}
