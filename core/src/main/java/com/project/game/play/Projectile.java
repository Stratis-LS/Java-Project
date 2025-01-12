package com.project.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Représente un projectile dans le jeu.
 * Le projectile se déplace verticalement à une vitesse constante et devient inactif lorsqu'il sort de l'écran.
 * Cette classe hérite de {@link Entity} et doit être mise à jour et rendue à chaque image.
 */
public class Projectile extends Entity {

    /**
     * Vitesse verticale constante du projectile (en pixels par seconde).
     */
    private final float vitesse = 500;

    /**
     * Indique si le projectile est actif.
     * Un projectile inactif n'est pas mis à jour ni rendu.
     */
    private boolean actif = true;

    /**
     * Crée un projectile à une position initiale donnée.
     *
     * @param x Coordonnée X initiale du projectile.
     * @param y Coordonnée Y initiale du projectile.
     */
    public Projectile(float x, float y) {
        super(x, y, "game/projectile.png");
    }

    /**
     * Met à jour la position du projectile en fonction du temps écoulé.
     * Le projectile se déplace vers le haut à la vitesse définie.
     * Si le projectile dépasse les limites de l'écran, il devient inactif.
     *
     * @param delta Temps écoulé depuis la dernière mise à jour (en secondes).
     */
    @Override
    public void update(float delta) {
        y += vitesse * delta; // Déplace vers le haut
        if (y > Gdx.graphics.getHeight()) {
            setInactive(); // Désactiver si hors de l'écran
        }
    }

    /**
     * Affiche le projectile à l'écran.
     * Le rendu est effectué uniquement si le projectile est actif.
     *
     * @param batch Instance de {@link SpriteBatch} utilisée pour dessiner les textures.
     */
    @Override
    public void render(SpriteBatch batch) {
        if (actif) {
            batch.draw(texture, x + 20, y);
        }
    }

    /**
     * Vérifie si le projectile est actif.
     *
     * @return {@code true} si le projectile est actif, {@code false} sinon.
     */
    public boolean isActive() {
        return actif;
    }

    /**
     * Définit le projectile comme inactif.
     * Un projectile inactif n'est pas mis à jour ni rendu.
     */
    public void setInactive() {
        actif = false;
    }
}

