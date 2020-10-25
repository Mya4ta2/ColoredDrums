package com.colordrum.screen;

import com.badlogic.gdx.Screen;
import com.colordrum.Game;
import com.colordrum.controller.LevelController;
import com.colordrum.level.Level;
import com.colordrum.view.LevelRenderer;

public class GameScreen implements Screen {

    private boolean isStart;

    private Game game;

    private Level level;
    private LevelRenderer renderer;
    private LevelController controller;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        level = new Level();
        renderer = new LevelRenderer(level);
        controller = new LevelController(level, game);
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
        controller.update();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        level.resize(width, height);
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

    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
