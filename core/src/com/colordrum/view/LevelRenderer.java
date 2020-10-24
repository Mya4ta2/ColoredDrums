package com.colordrum.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colordrum.ColorUtil;
import com.colordrum.Vars;
import com.colordrum.level.Level;

public class LevelRenderer {

    private Level level;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;

    public LevelRenderer(Level level) {
        this.level = level;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/RussoOne.fnt"));

        stage = new Stage();
        stage.setViewport(viewport);

        for (int i = level.getDrums().length-1; i >= 0; i--) {
            stage.addActor(level.getDrums()[i]);
        }

        for (int i = 0; i < level.getBalls().length; i++) {
            stage.addActor(level.getBalls()[i]);
        }
    }

    public void render(float delta) {

        float[] rgb = ColorUtil.ColorToRGB(ColorUtil.darkenColor(level.getDrums()[2].getColor(), 0.1f));

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(rgb[0], rgb[1], rgb[2],1);

        stage.act();
        stage.draw();

        batch.begin();
        font.draw(batch, String.valueOf(level.getPlayTime()), 100, 100);
        batch.end();

        camera.update();
        viewport.apply();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
