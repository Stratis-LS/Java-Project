package com.project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.project.game.sound.MusicManager;
import com.project.game.state.ManagerState;
import com.project.game.state.MenuState;

/**
 * Classe principale de l'application, héritant de la classe Game.
 * Elle initialise les éléments nécessaires au fonctionnement du jeu,
 * gère l'état du jeu et contrôle la musique de fond.
 *
 * Classe principale de l'application: <code>Main</code>
 * @author Lucas
 * @author Fofana
 * @version 1.0.0
 */
public class Main extends Game
{

    /**
     * Objet utilisé pour le rendu graphique 2D.
     */
    private SpriteBatch batch;

    /**
     * Gestionnaire des états du jeu.
     */
    private ManagerState managerState;

    /**
     * Gestionnaire de la musique, partagé entre toutes les instances.
     */
    private static MusicManager musicManager = new MusicManager();

    /**
     * Méthode appelée au démarrage de l'application.
     * Initialise les objets nécessaires (rendu graphique, état initial, musique de fond).
     */
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        managerState = new ManagerState();
        managerState.setState(new MenuState(managerState));
        musicManager.play("sound/space_background_music.mp3");
    }

    /**
     * Méthode appelée à chaque image pour mettre à jour et afficher le jeu.
     * Elle gère les mises à jour des états et leur rendu graphique.
     */
    @Override
    public void render()
    {
        managerState.update(Gdx.graphics.getDeltaTime());
        managerState.render();
    }

    /**
     * Méthode appelée lors de la fermeture de l'application.
     * Libère les ressources graphiques et musicales.
     */
    @Override
    public void dispose()
    {
        batch.begin();
        batch.dispose();
        musicManager.dispose();
        batch.end();
    }

    /**
     * Méthode appelée lors du redimensionnement de la fenêtre.
     *
     * @param width  Nouvelle largeur de la fenêtre.
     * @param height Nouvelle hauteur de la fenêtre.
     */
    @Override public void resize(int width, int height) {}

    /**
     * Méthode appelée lorsque l'application est mise en pause (ex. : interruption sur mobile).
     */
    @Override public void pause() {}

    /**
     * Méthode appelée lorsque l'application reprend après une pause.
     */
    @Override public void resume() {}

    /**
     * Définit un nouveau gestionnaire de musique.
     *
     * @param musicManager Nouvelle instance de MusicManager à utiliser.
     */
    public static void setMusicManager(MusicManager musicManager) {
        Main.musicManager = musicManager;
    }

    /**
     * Retourne le gestionnaire de musique actuel.
     *
     * @return Instance actuelle de MusicManager.
     */
    public static MusicManager getMusicManager() {
        return musicManager;
    }
}
