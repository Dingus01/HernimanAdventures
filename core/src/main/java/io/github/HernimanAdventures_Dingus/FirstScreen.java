package io.github.HernimanAdventures_Dingus;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FirstScreen implements Screen {
    Texture sheet;
    Animation<TextureRegion> idleAnim;

    float stateTime;

    FitViewport viewport;
    SpriteBatch spriteBatch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private OrthographicCamera camera;

    @Override
    public void show() {
        map = new TmxMapLoader().load("diddybludden.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        sheet = new Texture("Tech Dungeon Roguelite - Asset Pack (DEMO)/Players/players blue x1.png");

        TextureRegion[][] tmp = TextureRegion.split(sheet, 32, 32);
        TextureRegion[] idleFrames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            idleFrames[i] = tmp[0][i];
        }

        idleAnim = new Animation<>(0.2f, idleFrames);

        stateTime = 0;

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);
    }

    @Override
    public void render(float delta) {
        camera.update();

        stateTime += delta;

        TextureRegion frame = idleAnim.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(frame, 100, 100);
        spriteBatch.end();

        mapRenderer.setView(camera);
        mapRenderer.render();

        input();
        logic();
        draw();
    }

    private void input() {

    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height, true);
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
