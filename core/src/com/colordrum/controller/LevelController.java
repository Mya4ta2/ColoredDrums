package com.colordrum.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.colordrum.ColorUtil;
import com.colordrum.gameui.Ball;
import com.colordrum.level.Level;

import java.util.Arrays;

public class LevelController implements InputProcessor {

    private Level level;

    //time
    private float currentTimer;
    private float colorChangeTimer;

    public LevelController(Level level) {
        this.level = level;
    }

    public void update() {
        updateLevel();
        processInput();
    }

    public void updateLevel() {
        for (int i = 0; i < level.getBalls().length; i++) {
            level.getBalls()[i].rotate();
        }

        for (int i = 0; i < level.getDrums().length; i++) {
            if (ColorUtil.colorEquals(ColorUtil.darkenColor(level.getDrums()[i].getColor(), 0.1f), level.getDrums()[i].getBall().getColor())) {
                level.getDrums()[i].setDrumColorEqualsBallColor(true);
            } else {
                level.getDrums()[i].setDrumColorEqualsBallColor(false);
            }
        }


        colorChangeTimer += Gdx.graphics.getDeltaTime();
        currentTimer += Gdx.graphics.getDeltaTime();

        if (colorChangeTimer >= level.colorChangeSpeed) {

            for (int i = 0; i < level.getDrums().length; i++) {
                changeBallsColor(level.getBalls()[i]);
            }

            colorChangeTimer = 0;
        }

        level.setPlayTime(currentTimer);
    }

    public void processInput() {

    }

    public void changeBallsColor(Ball ball) {
        ColorUtil.Colors[] colors = ColorUtil.Colors.values();

        ball.setColor(ColorUtil.darkenColor(colors[getRandomNum(0, colors.length)].getColor(), 0.1f));
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public int getRandomNum(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
