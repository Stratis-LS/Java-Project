package com.project.game.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Représente une entité de jeu générique avec une texture et une position.
 * Cette classe est abstraite et doit être étendue par des entités spécifiques comme {@link Player} ou {@link Enemy}.
 */
public abstract class Entity {

    /**
     * Texture de l'entité, utilisée pour l'affichage à l'écran.
     */
    protected Texture texture;

    /**
     * Position en x et y de l'entité.
     */
    protected float x, y;

    /**
     * Constructeur pour initialiser une entité avec une position et une texture.
     *
     * @param x Position en x de l'entité.
     * @param y Position en y de l'entité.
     * @param texturePath Chemin de la texture utilisée pour l'entité.
     */
    public Entity(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(texturePath);
    }

    /**
     * Méthode abstraite pour mettre à jour l'état de l'entité à chaque frame.
     * Cette méthode doit être implémentée dans les sous-classes.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour (en secondes).
     */
    public abstract void update(float delta);

    /**
     * Méthode abstraite pour rendre l'entité à l'écran.
     * Cette méthode doit être implémentée dans les sous-classes.
     *
     * @param batch Instance de {@link SpriteBatch} utilisée pour dessiner la texture.
     */
    public abstract void render(SpriteBatch batch) ;

    /**
     * Libère les ressources associées à la texture de l'entité.
     */
    public void dispose() {
        texture.dispose();
    }

    /**
     * Retourne la position en x de l'entité.
     *
     * @return La position en x de l'entité.
     */
    public float getX() { return x; }

    /**
     * Retourne la position en y de l'entité.
     *
     * @return La position en y de l'entité.
     */
    public float getY() { return y; }
}

