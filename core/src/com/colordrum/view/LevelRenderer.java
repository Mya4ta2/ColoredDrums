package com.colordrum.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colordrum.ColorUtil;
import com.colordrum.Vars;
import com.colordrum.gameui.TextButton;
import com.colordrum.level.Level;

public class LevelRenderer {

    private boolean menuMode = false;
    private boolean startRequired = false;

    private Level level;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private SpriteBatch batch;

    private Texture button1;
    private Texture button2;
    private Sound buttonSound;
    private BitmapFont font;

    private TextButton startButton;
    private TextButton exitButton;


    public LevelRenderer(Level level) {
        this.level = level;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();

        button1 = new Texture("button1.png");
        button2 = new Texture("button2.png");
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("minecraft_click.wav"));
        font = new BitmapFont(Gdx.files.internal("font/RussoOne.fnt"));

        stage = new Stage();
        stage.setViewport(viewport);

        startButton = new TextButton(button1, button2, buttonSound, font);
        startButton.setText("start");
        startButton.setSize(300, 100);
        startButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setStartRequired(true);
                return true;
            }
        });
        exitButton = new TextButton(button1, button2, buttonSound, font);
        exitButton.setText("exit");
        exitButton.setSize(300, 100);
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        for (int i = level.getDrums().length-1; i >= 0; i--) {
            stage.addActor(level.getDrums()[i]);
        }

        for (int i = 0; i < level.getBalls().length; i++) {
            stage.addActor(level.getBalls()[i]);
        }

        if (!menuMode) {
            stage.addActor(startButton);
            stage.addActor(exitButton);
        }
    }

    public void render(float delta) {

        float[] rgb = ColorUtil.ColorToRGB(ColorUtil.darkenColor(level.getDrums()[2].getColor(), 0.1f));

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(rgb[0], rgb[1], rgb[2],1);

        stage.act();
        stage.draw();

        if (!menuMode) {
            batch.begin();
            font.draw(batch, String.valueOf(level.getPlayTime()), 100, 100);
            batch.end();
        }

        if (!menuMode) { // remove button if menu disabled
            for (int i = 0; i < stage.getActors().size; i++) {
                if (stage.getActors().items[i] == startButton || stage.getActors().items[i] == exitButton) {
                    stage.getActors().items[i].remove();
                }
            }
        }

        camera.update();
        viewport.apply();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);

        exitButton.setPosition(width / 2 / width % 2 - exitButton.getWidth() / 2, height / 2 / height % 2 - exitButton.getHeight() / 2 - 45);
        startButton.setPosition(width / 2 / width % 2 - exitButton.getWidth() / 2, height / 2 / height % 2 + exitButton.getHeight() / 2);
    }

    public boolean isMenuMode() {
        return menuMode;
    }

    public void setMenuMode(boolean menuMode) {
        this.menuMode = menuMode;
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isStartRequired() {
        return startRequired;
    }

    public void setStartRequired(boolean startRequired) {
        this.startRequired = startRequired;
    }
}
