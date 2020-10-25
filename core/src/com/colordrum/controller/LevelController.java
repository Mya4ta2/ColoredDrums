package com.colordrum.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.colordrum.ColorUtil;
import com.colordrum.gameui.Ball;
import com.colordrum.level.Level;

import java.util.Arrays;

public class LevelController implements InputProcessor {

    public enum ControllerMode {
        SENSOR, KEYBOARD
    }

    private boolean menuMode = false;

    private Level level;

    //ball control
    private ControllerMode controllerMode = ControllerMode.KEYBOARD;
    private int ballNum;

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

        if (!menuMode) {
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
    }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            level.getBalls()[ballNum].setRotationRadius(100);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (ballNum < level.getBalls().length-1) {
                ballNum++;
                level.getBalls()[ballNum].setPriority(true);
            } else if (ballNum >= level.getBalls().length-1) {
                ballNum = 0;
                level.getBalls()[ballNum].setPriority(true);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (ballNum > 0) {
                ballNum--;
                level.getBalls()[ballNum].setPriority(true);
            } else if (ballNum <= 0) {
                ballNum = level.getBalls().length-1;
                level.getBalls()[ballNum].setPriority(true);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            level.getBalls()[ballNum].setRotationRadius(30);
        }

        for (int i = 0; i < level.getBalls().length; i++) {
            if (level.getBalls()[i] != level.getBalls()[ballNum]) {
                level.getBalls()[i].setPriority(false);
            }
        }
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

    public boolean isMenuMode() {
        return menuMode;
    }

    public void setMenuMode(boolean menuMode) {
        this.menuMode = menuMode;
    }

    public ControllerMode getControllerMode() {
        return controllerMode;
    }

    public void setControllerMode(ControllerMode controllerMode) {
        this.controllerMode = controllerMode;
    }
}
