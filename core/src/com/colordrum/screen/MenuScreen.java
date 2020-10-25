package com.colordrum.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.colordrum.ColorUtil;
import com.colordrum.Game;
import com.colordrum.controller.LevelController;
import com.colordrum.gameui.TextButton;
import com.colordrum.level.Level;
import com.colordrum.view.LevelRenderer;

public class MenuScreen implements Screen {

    private boolean isStart;

    private Level level;
    private LevelRenderer renderer;
    private LevelController controller;

    private InputMultiplexer gameMultiplexer;
    private InputMultiplexer menuMultiplexer;

    private Game game;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        level = new Level();
        renderer = new LevelRenderer(level);
        controller = new LevelController(level, game);

        renderer.setMenuMode(true);
        controller.setMenuMode(true);

        level.getBalls()[0].setColor(ColorUtil.darkenColor(ColorUtil.Colors.BLUE.getColor(), 0.1f));
        level.getBalls()[1].setColor(ColorUtil.darkenColor(ColorUtil.Colors.RED.getColor(), 0.1f));
        level.getBalls()[2].setColor(ColorUtil.darkenColor(ColorUtil.Colors.GREEN.getColor(), 0.1f));

        menuMultiplexer = new InputMultiplexer(renderer.getStage());
        gameMultiplexer = new InputMultiplexer(controller);

        Gdx.input.setInputProcessor(menuMultiplexer);
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
        controller.update();

        if (renderer.isStartRequired()) {
            GameScreen gameScreen = new GameScreen(game);

            game.setScreen(gameScreen);
            isStart = true;
        }

        if (isStart) {
            Gdx.input.setInputProcessor(gameMultiplexer);
        } else {
            Gdx.input.setInputProcessor(menuMultiplexer);
        }
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
