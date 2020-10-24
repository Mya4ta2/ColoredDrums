package com.colordrum.gameui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Drum extends Actor {
    private Color color;
    private Sprite circle;
    private Ball ball;

    private boolean drumColorEqualsBallColor = false;

    public Drum(Texture circle) {
        this.circle = new Sprite(circle);

        setDefaultColor();
        setDefaultPos();
        setDefaultSize();
    }

    public void setDefaultSize() {
        setWidth(100);
        setHeight(100);
    }

    public void setDefaultPos() {
        setX(100);
        setY(100);
    }

    public void setDefaultColor() {
        color = Color.BLACK;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        circle.setColor(color);
        circle.setPosition(getX() - getWidth() / 2, getY() - getHeight() / 2);
        circle.setSize(getWidth(), getHeight());
        circle.draw(batch);

        super.draw(batch, parentAlpha);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public boolean isDrumColorEqualsBallColor() {
        return drumColorEqualsBallColor;
    }

    public void setDrumColorEqualsBallColor(boolean drumColorNotEqualsBallColor) {
        this.drumColorEqualsBallColor = drumColorNotEqualsBallColor;
    }
}
