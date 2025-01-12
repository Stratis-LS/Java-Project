package com.project.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.project.game.Main;
import com.project.game.key.KeyManager;

/**
 * État des paramètres du jeu.
 * Permet de gérer l'interface des paramètres, notamment le réglage du volume de la musique
 * et la navigation de retour au menu principal.
 */
public class SettingsState extends GameState {

    /**
     * Gestionnaire de rendu graphique pour cet état.
     */
    private SpriteBatch batch;

    /**
     * Textures utilisées pour afficher l'écran des paramètres.
     */
    private Texture upTexture, downTexture, leftTexture, rightTexture, settingTexture, returnTexture, increaseTexture, decreaseTexture;

    /**
     * Zones interactives définissant les clics sur les boutons.
     */
    private Rectangle upAreas, downAreas, leftAreas, rightAreas, returnAreas, increaseAreas, decreaseAreas;

    /**
     * Police utilisée pour afficher le volume de la musique.
     */
    private BitmapFont fontSound, fontSize;

    /**
     * Constructeur de l'état des paramètres.
     * Initialise les textures, zones interactives, et la police.
     *
     * @param managerState Gestionnaire des états du jeu.
     */
    public SettingsState(ManagerState managerState) {

        super(managerState);

        batch = new SpriteBatch();
        fontSize = new BitmapFont();
        fontSize.setColor(Color.BLACK);
        fontSound = new BitmapFont();
        fontSound.setColor(Color.BLACK);
        fontSound.getData().setScale(3);
        fontSize.getData().setScale(2);

        upTexture = new Texture("menu/settings/up.png");
        downTexture = new Texture("menu/settings/down.png");
        leftTexture = new Texture("menu/settings/left.png");
        rightTexture = new Texture("menu/settings/right.png");
        settingTexture = new Texture("menu/settings/setting.png");
        returnTexture = new Texture("menu/settings/return.png");
        increaseTexture = new Texture("menu/settings/increase.png");
        decreaseTexture = new Texture("menu/settings/decrease.png");
        upAreas = new Rectangle(520, 460, upTexture.getWidth(), upTexture.getHeight());
        downAreas = new Rectangle(830, 460, downTexture.getWidth(), downTexture.getHeight());
        leftAreas = new Rectangle(830, 330, leftTexture.getWidth(), leftTexture.getHeight());
        rightAreas = new Rectangle(520, 330, rightTexture.getWidth(), rightTexture.getHeight());
        returnAreas = new Rectangle(130, 60, returnTexture.getWidth(), returnTexture.getHeight());
        increaseAreas = new Rectangle(880, 200, increaseTexture.getWidth(), increaseTexture.getHeight());
        decreaseAreas = new Rectangle(520, 200, decreaseTexture.getWidth(), decreaseTexture.getHeight());
    }

    /**
     * Met à jour l'état des paramètres.
     * Gère les interactions utilisateur, comme les clics sur les boutons
     * et la navigation avec la touche Échap.
     *
     * @param delta Temps écoulé depuis la dernière image (en secondes).
     */
    @Override
    public void update(float delta) {

        if (Gdx.input.justTouched()) {
            int screenX = Gdx.input.getX();
            int screenY = Gdx.input.getY();
            int realY = Gdx.graphics.getHeight() - screenY;

            if (returnAreas.contains(screenX, realY)) {
                gameStateManager.setState(new MenuState(gameStateManager));
            }

            if (increaseAreas.contains(screenX, realY)) {
                Main.getMusicManager().volumeModification(0.1f);
            }

            if (decreaseAreas.contains(screenX, realY)) {
                Main.getMusicManager().volumeModification(-0.1f);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            gameStateManager.setState(new MenuState(gameStateManager));
        }
    }

    /**
     * Gère l'affichage graphique de l'état des paramètres.
     * Affiche les boutons, le fond, et le volume actuel.
     */
    @Override
    public void render() {
        batch.begin();
        batch.draw(settingTexture, 0, 0);
        batch.draw(upTexture, upAreas.x, upAreas.y);
        batch.draw(downTexture, downAreas.x, downAreas.y);
        batch.draw(leftTexture, leftAreas.x, leftAreas.y);
        batch.draw(rightTexture, rightAreas.x, rightAreas.y);
        batch.draw(returnTexture, returnAreas.x, returnAreas.y);
        batch.draw(increaseTexture, increaseAreas.x, increaseAreas.y);
        fontSize.draw(batch, String.valueOf(KeyManager.getUpKeyName()), 730, 520);
        fontSize.draw(batch, String.valueOf(KeyManager.getDownKeyName()), 1040, 520);
        fontSize.draw(batch, String.valueOf(KeyManager.getRightKeyName()), 730, 390);
        fontSize.draw(batch, String.valueOf(KeyManager.getLeftKeyName()), 1040, 390);
        fontSound.draw(batch, String.valueOf(Main.getMusicManager().getVolume()), 770, 250);
        batch.draw(decreaseTexture, decreaseAreas.x, decreaseAreas.y);
        batch.end();
    }

    /**
     * Libère les ressources utilisées par cet état.
     * Nettoie les textures, polices, et autres éléments graphiques.
     */
    @Override
    public void dispose() {
        batch.dispose();
        fontSound.dispose();
        fontSize.dispose();
        upTexture.dispose();
        downTexture.dispose();
        leftTexture.dispose();
        rightTexture.dispose();
        settingTexture.dispose();
        returnTexture.dispose();
        increaseTexture.dispose();
        decreaseTexture.dispose();
    }
}
