package com.colordrum.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.colordrum.ColorUtil;
import com.colordrum.Game;
import com.colordrum.gameui.Ball;
import com.colordrum.level.Level;

import java.util.Arrays;

public class LevelController implements InputProcessor {

    public enum ControllerMode {
        SENSOR, KEYBOARD
    }

    private boolean menuMode = false;

    private Level level;
    private Game game;

    //ball control
    private ControllerMode controllerMode = ControllerMode.KEYBOARD;
    private int ballNum;
    private int ballRadius;

    //time
    private float currentTimer;
    private float colorChangeTimer;

    public LevelController(Level level, Game game) {
        this.level = level;
        this.game = game;
    }

    public void update() {
        updateLevel();
        processInput();
    }

    public void updateLevel() {
        for (int i = 0; i < level.getBalls().length; i++) {
            level.getBalls()[i].rotate();
        }

        for(int j = 0; j < level.getBalls().length; j++) {
            for (int j2 = 0; j2 < level.getDrums().length; j2++) {
                if (j2 == j) continue;
                if (level.getDrums()[j2].getRotationRadius() == level.getBalls()[j].getRotationRadius()) {
                    level.getDrums()[j2].setBall(level.getBalls()[j]);
                    break;
                }
            }
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

                for (int i = 0; i < level.getDrums().length; i++) {
                    if (!level.getDrums()[i].isDrumColorEqualsBallColor()) {
                        gameOver();
                        System.out.println(i);
                    }
                }

                colorChangeTimer = 0;
            }


        level.setPlayTime(currentTimer);
        }
    }

    public void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (ballRadius < level.getRadius().length-1) ballRadius++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (ballNum < level.getBalls().length-1) {
                ballNum++;
                ballRadius = getBallRadiusNum(level.getBalls()[ballNum]);
                level.getBalls()[ballNum].setPriority(true);
            } else if (ballNum >= level.getBalls().length-1) {
                ballNum = 0;
                ballRadius = getBallRadiusNum(level.getBalls()[ballNum]);
                level.getBalls()[ballNum].setPriority(true);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (ballNum > 0) {
                ballNum--;
                ballRadius = getBallRadiusNum(level.getBalls()[ballNum]);
            } else if (ballNum <= 0) {
                ballNum = level.getBalls().length-1;
                ballRadius = getBallRadiusNum(level.getBalls()[ballNum]);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (ballRadius > 0) ballRadius--;
        }

        for (int i = 0; i < level.getBalls().length; i++) {
            if (level.getBalls()[i] != level.getBalls()[ballNum]) {
                level.getBalls()[i].setPriority(false);
            } else {
                level.getBalls()[i].setPriority(true);
            }

            level.getBalls()[ballNum].setRotationRadius(level.getRadius()[ballRadius]);
        }
    }

    public int getBallRadiusNum(Ball ball) {
        try {
            if (ball.getRotationRadius() == level.getRadius()[0]) {
                return 0;
            }

            if (ball.getRotationRadius() == level.getRadius()[1]) {
                return 1;
            }

            if (ball.getRotationRadius() == level.getRadius()[2]) {
                return 2;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0;
        }

        return 0;
    }

    public void gameOver() {
        game.setScreen(game.getMenuScreen());
        game.getMenuScreen().setStart(false);
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