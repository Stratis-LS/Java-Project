package com.project.game.state;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.project.game.play.Play;

/**
 * Classe représentant l'état principal du jeu.
 * Gère l'affichage, les mises à jour et les interactions utilisateur pendant la partie.
 */
public class PlayState extends GameState implements ApplicationListener {

    /**
     * Carte Tiled utilisée comme arrière-plan.
     */
    private TiledMap tiledMap;

    /**
     * Gestionnaire de rendu pour la carte Tiled.
     */
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * Caméra orthographique utilisée pour afficher la carte et les éléments du jeu.
     */
    private OrthographicCamera camera;

    /**
     * FrameBuffer pour capturer l'image du rendu Tiled.
     */
    private FrameBuffer frameBuffer;

    /**
     * Texture générée à partir du FrameBuffer pour afficher la carte Tiled.
     */
    private Texture tiledTexture;

    /**
     * Gestionnaire de rendu graphique.
     */
    private SpriteBatch spriteBatch;

    /**
     * Indicateur de l'état de la carte Tiled.
     * Si vrai, la carte Tiled est active et affichée.
     */
    private boolean tiledValue = false;

    /**
     * Gestionnaire de rendu graphique principal.
     */
    private SpriteBatch batch;

    /**
     * Texture utilisée pour afficher l'arrière-plan du jeu.
     */
    private Texture gameTexture;

    /**
     * Instance de la classe logique pour gérer les événements et interactions du jeu.
     */
    public Play play;

    /**
     * Constructeur de l'état du jeu principal.
     * Initialise les éléments graphiques, la carte Tiled et la logique de jeu.
     *
     * @param managerState Gestionnaire des états du jeu.
     */
    public PlayState(ManagerState managerState) {
        super(managerState);
        batch = new SpriteBatch();
        spriteBatch = new SpriteBatch();

        // Chargement du fond d'écran
        try {
            gameTexture = new Texture("background.png");
        } catch (Exception e) {
            Gdx.app.error("Main", "Erreur de chargement du fond d'écran : " + e.getMessage());
            gameTexture = null;
        }

        play = new Play();

        // Chargement de la carte Tiled
        try {
            tiledMap = new TmxMapLoader().load("fullBackground.tmx");
            tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

            camera = new OrthographicCamera();
            camera.setToOrtho(false, 1280, 720);  // Vue orthographique sans inversion verticale
            camera.position.set(640, 360, 0);

            frameBuffer = new FrameBuffer(Pixmap.Format.RGB888, 1280, 720, false);
            tiledValue = true;
            System.out.println("Tiled initialisé");
        } catch (Exception e) {
            Gdx.app.error("Main", "Erreur de chargement de la carte Tiled : " + e.getMessage());
            tiledMap = null;
            tiledMapRenderer = null;
        }
    }

    /**
     * Met à jour l'état du jeu.
     * Permet de gérer les interactions utilisateur, comme le retour au menu principal via la touche Échap.
     *
     * @param delta Temps écoulé depuis la dernière image (en secondes).
     */
    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameStateManager.setState(new MenuState(gameStateManager));
        }
    }

    /**
     * Gère le rendu graphique de l'état du jeu.
     * Met à jour la logique du jeu, puis affiche l'arrière-plan et les éléments graphiques de la partie.
     */
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        if (play != null) {
            play.update(delta);
        }

        if (tiledValue) {
            frameBuffer.begin();
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
            frameBuffer.end();

            tiledTexture = frameBuffer.getColorBufferTexture();

            spriteBatch.begin();
            spriteBatch.draw(tiledTexture, 0, 0);
            spriteBatch.end();
        }

        batch.begin();

        if (gameTexture != null && !tiledValue) {
            batch.draw(gameTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (play != null) {
            play.render(batch);
        }

        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     * Libère les ressources utilisées par cet état.
     * Nettoie les textures, les gestionnaires de rendu et la logique de jeu.
     */
    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (gameTexture != null) gameTexture.dispose();
        if (play != null) play.dispose();
        if (tiledMap != null) tiledMap.dispose();
        if (tiledMapRenderer != null) tiledMapRenderer.dispose();
        if (frameBuffer != null) frameBuffer.dispose();
        if (spriteBatch != null) spriteBatch.dispose();
    }
}
