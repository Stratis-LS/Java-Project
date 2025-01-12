package com.project.game.state;

/**
 * Classe de gestion des états du jeu.
 * Permet de contrôler le passage entre les différents états et de déléguer les mises à jour
 * ainsi que les rendus au GameState actif.
 */
public class ManagerState
{

    /**
     * Instance représentant l'état actuel du jeu.
     */
    private GameState gameState;

    /**
     * Change l'état actuel du jeu.
     * Si un état est déjà actif, il est d'abord libéré via la méthode `dispose`.
     *
     * @param state Le nouvel état à activer.
     */
    public void setState(GameState state)
    {
        if (gameState != null) {
            gameState.dispose();
        }
        gameState = state;
    }

    /**
     * Met à jour l'état actuel du jeu.
     * Appelle la méthode `update` de l'état actif pour exécuter sa logique interne.
     *
     * @param delta Temps écoulé depuis la dernière image (en secondes).
     */
    public void update(float delta)
    {
        gameState.update(delta);
    }

    /**
     * Gère le rendu graphique de l'état actuel.
     * Appelle la méthode `render` de l'état actif pour afficher ses éléments graphiques.
     */
    public void render()
    {
        gameState.render();
    }
}
