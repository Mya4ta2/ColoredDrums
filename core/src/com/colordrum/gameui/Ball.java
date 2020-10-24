package com.colordrum.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;

public class Ball extends Actor {

    private Color color;
    private Sprite circle;
    private Vector2 position = new Vector2();

    private float rotationRadius;
    private float rotationSpeed;
    private float rotationAngle;

    public Ball(Texture circle) {
        this.circle = new Sprite(circle);

        setDefaultColor();
        setDefaultPos();
        setDefaultSize();

        position.x = getX();
        position.y = getY();

        addListener(new BallInputListener(this));
    }

    public void setDefaultSize() {
        setWidth(45);
        setHeight(45);
    }

    public void setDefaultPos() {
        setX(100);
        setY(100);
    }

    public void setDefaultColor() {
        color = Color.BLACK;
    }

    public void rotate() {
        setX((float) (position.x - (Math.cos(rotationAngle * Math.PI / 180) * rotationRadius)));
        setY((float) (position.y - (Math.sin(rotationAngle * Math.PI / 180) * rotationRadius)));

        rotationAngle += rotationSpeed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        circle.setColor(color);
        circle.setPosition(getX(), getY());
        circle.setSize(getWidth(), getHeight());
        circle.draw(batch);

        super.draw(batch, parentAlpha);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotationRadius() {
        return rotationRadius;
    }

    public void setRotationRadius(float rotationRadius) {
        this.rotationRadius = rotationRadius;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
