package com.colordrum.level;

import com.badlogic.gdx.graphics.Texture;
import com.colordrum.ColorUtil;
import com.colordrum.gameui.Ball;
import com.colordrum.gameui.Drum;

public class Level {

    public float colorChangeSpeed = 3f;
    private float playTime = 0;

    private int drumsAmount = 3;

    private Ball[] balls = new Ball[drumsAmount];
    private Drum[] drums = new Drum[drumsAmount];
    private int[] radius = new int[drumsAmount];

    private Texture texture;
    private Texture star;

    public Level() {
        texture = new Texture("circle.png");
        star = new Texture("star.png");

        createLevel();
    }

    public void createLevel() {
        for (int i = 0; i < drumsAmount; i++) {
            balls[i] = new Ball(texture, star);
            balls[i].setRotationAngle(i*90);
            balls[i].setRotationSpeed(1);

            drums[i] = new Drum(texture);
            drums[i].setSize(125 + i * 110, 125 + i * 110);

            drums[i].setBall(balls[i]);
        }

        drums[2].setColor(ColorUtil.Colors.GREEN.getColor());
        drums[1].setColor(ColorUtil.Colors.RED.getColor());
        drums[0].setColor(ColorUtil.Colors.BLUE.getColor());

        radius[0] = 35;
        radius[1] = 90;
        radius[2] = 145;

        balls[0].setRotationRadius(radius[0]);
        balls[1].setRotationRadius(radius[1]);
        balls[2].setRotationRadius(radius[2]);

        balls[0].setColor(ColorUtil.darkenColor(ColorUtil.Colors.GREEN.getColor(), 0.1f));
        balls[1].setColor(ColorUtil.darkenColor(ColorUtil.Colors.RED.getColor(), 0.1f));
        balls[2].setColor(ColorUtil.darkenColor(ColorUtil.Colors.BLUE.getColor(), 0.1f));

        drums[0].setRotationRadius(radius[0]);
        drums[1].setRotationRadius(radius[1]);
        drums[2].setRotationRadius(radius[2]);
    }

    public void resize(int width, int height) {
        for (int i = 0; i < drumsAmount; i++) {
            balls[i].setPosition(width / 2 / width % 2 - 22.5f, height / 2 / height % 2 - 22.5f);
            drums[i].setPosition(width / 2 / width % 2, height / 2 / height % 2);
        }
    }

    public Ball[] getBalls() {
        return balls;
    }

    public Drum[] getDrums() {
        return drums;
    }

    public int[] getRadius() {
        return radius;
    }

    public int getRandomNum(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public float getPlayTime() {
        return playTime;
    }

    public void setPlayTime(float playTime) {
        this.playTime = playTime;
    }
}
