package com.project.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.key.KeyManager;

/**
 * Représente le joueur dans le jeu, contrôlé par les entrées clavier.
 * La classe gère les mouvements du joueur et les limites de l'écran.
 * Elle hérite de la classe {@link Entity}.
 */
public class Player extends Entity {

    /**
     * Vitesse de déplacement du joueur (en pixels par seconde).
     */
    public float vitesse = 300;

    /**
     * Largeur de l'écran de jeu.
     */
    private float largeurEcran, hauteurEcran;

    /**
     * Hauteur de l'écran de jeu.
     */
    KeyManager keys = new KeyManager("assets/key/Keys.json");

    /**
     * Constructeur de la classe Player.
     * Initialise la position initiale, charge la texture et configure les dimensions de l'écran.
     */
    public Player() {
        super(550, 50, "game/vaisseau.png");
        System.out.println("KeyManager initialisé");
        largeurEcran = Gdx.graphics.getWidth();
        hauteurEcran = Gdx.graphics.getHeight();
    }

    /**
     * Met à jour la position du joueur en fonction des entrées clavier.
     * Assure que le joueur reste à l'intérieur des limites de l'écran.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour (en secondes).
     */
    @Override
    public void update(float delta)
    {
        x = keys.mouv_abs(x, vitesse, delta);
        y = keys.mouv_ord(y, vitesse, delta);

        // Maintenir le joueur dans les limites de l'écran
        if (x < 0) x = 0;
        if (x + texture.getWidth() > largeurEcran) x = largeurEcran - texture.getWidth();
        if (y < 0) y = 0;
        if (y + texture.getHeight() > hauteurEcran) y = hauteurEcran - texture.getHeight();

    }

    /**
     * Affiche le joueur à l'écran.
     *
     * @param batch Instance de {@link SpriteBatch} utilisée pour dessiner la texture.
     */
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    /**
     * Retourne la largeur de la texture du joueur.
     *
     * @return Largeur de la texture en pixels.
     */
    public float getWidth() { return texture.getWidth(); }

    /**
     * Retourne la hauteur de la texture du joueur.
     *
     * @return Hauteur de la texture en pixels.
     */
    public float getHeight() { return texture.getHeight(); }
}
