package com.project.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * État représentant le menu principal du jeu.
 * Permet de naviguer vers les différents états du jeu : démarrage, paramètres, ou quitter l'application.
 */
public class MenuState extends GameState {

    /**
     * Gestionnaire de rendu graphique pour cet état.
     */
    private SpriteBatch batch;

    /**
     * Zone cliquable pour les boutons.
     */
    private Texture menuTexture, startTexture, settingsTexture, exitTexture;

    /**
     * Zone cliquable pour le bouton "Exit".
     */
    private Rectangle startAreas, optionsAreas,exitAreas;

    /**
     * Constructeur de l'état du menu principal.
     * Initialise les textures, zones cliquables, et le gestionnaire de rendu graphique.
     *
     * @param managerState Gestionnaire des états du jeu.
     */
    public MenuState(ManagerState managerState) {

        super(managerState);

        batch = new SpriteBatch();

        menuTexture = new Texture("menu/menu.png");
        startTexture = new Texture("menu/start.png");
        settingsTexture = new Texture("menu/settings.png");
        exitTexture = new Texture("menu/exit.png");

        startAreas = new Rectangle(280, 320, startTexture.getWidth(), startTexture.getHeight());
        optionsAreas = new Rectangle(280, 80, settingsTexture.getWidth(), settingsTexture.getHeight());
        exitAreas = new Rectangle(660, 80, exitTexture.getWidth(), exitTexture.getHeight());
    }

    /**
     * Met à jour l'état du menu principal.
     * Gère les clics utilisateur pour naviguer vers d'autres états ou quitter le jeu.
     *
     * @param delta Temps écoulé depuis la dernière image (en secondes).
     */
    @Override
    public void update(float delta) {

        if (Gdx.input.justTouched()) {
            int screenX = Gdx.input.getX();
            int screenY = Gdx.input.getY();
            int realY = Gdx.graphics.getHeight() - screenY;

            if (startAreas.contains(screenX, realY)) {
                gameStateManager.setState(new PlayState(gameStateManager));
            } else if (optionsAreas.contains(screenX, realY))
            {
                gameStateManager.setState(new SettingsState(gameStateManager));
            } else if (exitAreas.contains(screenX, realY)) {
                Gdx.app.exit();
            }
        }
    }

    /**
     * Gère l'affichage graphique du menu principal.
     * Affiche les boutons et l'arrière-plan.
     */
    @Override
    public void render() {
        batch.begin();
        batch.draw(menuTexture, 0, 0);
        batch.draw(startTexture, startAreas.x, startAreas.y);
        batch.draw(settingsTexture, optionsAreas.x, optionsAreas.y);
        batch.draw(exitTexture, exitAreas.x, exitAreas.y);
        batch.end();
    }

    /**
     * Libère les ressources utilisées par cet état.
     * Nettoie les textures et le gestionnaire de rendu graphique.
     */
    @Override
    public void dispose() {
        batch.dispose();
        menuTexture.dispose();
        startTexture.dispose();
        settingsTexture.dispose();
        exitTexture.dispose();
    }
}

