package com.project.game.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Représente un ennemi dans le jeu.
 * L'ennemi se déplace vers le bas de l'écran et peut être détruit, affichant une explosion à la place de son image.
 * La classe hérite de la classe {@link Entity}.
 */
public class Enemy extends Entity {

    /**
     * Vitesse de déplacement de l'ennemi (en pixels par seconde).
     */
    private float vitesse = 100;

    /**
     * Temps écoulé depuis l'explosion (en secondes).
     */
    private float explosionTime = 0;

    /**
     * Texture de l'explosion lorsque l'ennemi est détruit.
     */
    private Texture explosionTexture;

    /**
     * Indique si l'ennemi a été détruit.
     */
    private boolean estDetruit = false;

    /**
     * Constructeur de la classe Enemy.
     * Initialise l'ennemi à la position donnée et charge la texture de l'ennemi et de l'explosion.
     *
     * @param x Position en x de l'ennemi.
     * @param y Position en y de l'ennemi.
     */
    public Enemy(float x, float y) {
        super(x, y, "game/ennemi.png");
        explosionTexture = new Texture("game/explosion.png");
    }

    /**
     * Met à jour la position de l'ennemi et gère l'état de l'explosion.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour (en secondes).
     */
    @Override
    public void update(float delta) {
        if (!estDetruit) {
            y -= vitesse * delta;
        } else {
            explosionTime += delta;
        }
    }

    /**
     * Affiche l'ennemi ou l'explosion à l'écran.
     *
     * @param batch Instance de {@link SpriteBatch} utilisée pour dessiner la texture.
     */
    @Override
    public void render(SpriteBatch batch)
    {
        if (estDetruit) {
            batch.draw(explosionTexture, x, y);
        } else {
            batch.draw(texture, x, y);
        }
    }

    /**
     * Vérifie si l'ennemi est en dehors de l'écran (en bas).
     *
     * @return true si l'ennemi est hors de l'écran, false sinon.
     */
    public boolean isOffScreen() {
        return y + texture.getHeight() < 0;
    }

    /**
     * Retourne la vitesse de l'ennemi.
     *
     * @return Vitesse de l'ennemi (en pixels par seconde).
     */
    public float getVitesse() {
        return vitesse;
    }

    /**
     * Modifie la vitesse de l'ennemi.
     *
     * @param vitesse Nouvelle vitesse de l'ennemi.
     */
    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Détruit l'ennemi et déclenche l'animation de l'explosion.
     */
    public void destroy() {
        estDetruit = true;
        explosionTime = 0;
    }

    /**
     * Retourne la largeur de la texture de l'ennemi.
     *
     * @return Largeur de la texture en pixels.
     */
    public float getWidth() { return texture.getWidth(); }

    /**
     * Retourne la hauteur de la texture de l'ennemi.
     *
     * @return Hauteur de la texture en pixels.
     */
    public float getHeight() { return texture.getHeight(); }

    /**
     * Vérifie si l'ennemi a été détruit et si le temps d'explosion a suffisamment écoulé.
     *
     * @return true si l'ennemi est détruit et que l'explosion est terminée, false sinon.
     */
    public boolean estDetruit() {
        return estDetruit && explosionTime > 1;
    }

    /**
     * Vérifie si l'ennemi est prêt à être retiré (si l'explosion est terminée).
     *
     * @return true si l'ennemi est prêt à être retiré, false sinon.
     */
    public boolean isReadyToRemove() {
        return estDetruit && explosionTime > 1;
    }

    /**
     * Libère les ressources associées aux textures de l'ennemi et de l'explosion.
     */
    public void dispose() {
        texture.dispose();
        explosionTexture.dispose();
    }
}

