package com.project.game.state;

import com.badlogic.gdx.audio.Music;

/**
 * Classe abstraite représentant un état générique du jeu.
 * Cette classe sert de base pour implémenter des états spécifiques du jeu
 * (par exemple, menu principal, partie en cours, etc.).
 */
public abstract class GameState {

    /**
     * Gestionnaire des états du jeu, permettant de changer ou manipuler les états actuels.
     */
    protected ManagerState gameStateManager;

    /**
     * Musique de fond associée à cet état du jeu.
     */
    protected Music backgroundMusic;

    /**
     * Constructeur de la classe GameState.
     *
     * @param managerState Gestionnaire des états du jeu.
     */
    public GameState(ManagerState managerState) {
        this.gameStateManager = managerState;
    }

    /**
     * Met à jour l'état du jeu. Cette méthode doit être implémentée par les sous-classes.
     *
     * @param delta Temps écoulé depuis la dernière image (en secondes).
     */
    public abstract void update(float delta);

    /**
     * Gère le rendu graphique de l'état du jeu. Cette méthode doit être implémentée par les sous-classes.
     */
    public abstract void render();

    /**
     * Libère les ressources utilisées par cet état du jeu.
     * Cette méthode doit être implémentée par les sous-classes.
     */
    public abstract void dispose();
}
