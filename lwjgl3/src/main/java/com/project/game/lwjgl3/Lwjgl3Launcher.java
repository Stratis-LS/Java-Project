package com.project.game.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.project.game.Main;

/**
 * Classe pour lancer l'application: <code>Lwjgl3Launcher</code>
 * @author Lucas
 * @author Fofana
 * @version 1.0.0
 */
public class Lwjgl3Launcher
{
    /**
     * Methode static main
     * @param args
     */
    public static void main(String[] args)
    {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    /**
     * Methode static pour creer l'application <code>createApplication</code>
     * @return Une fenetre avec les parametres definis dans <code>getDefaultConfiguration()</code>
     */
    private static Lwjgl3Application createApplication()
    {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    /**
     * Methode static pour definir les parametres de l'application <code>getDefaultConfiguration</code>
     * @return Les configurations de l'application (titre, taille, icon, fps, ...)
     */
    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration()
    {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

        configuration.setTitle("Java Project");
        configuration.setWindowedMode(1280, 720);
        configuration.setResizable(false);
        configuration.setWindowIcon("icon/icon128.png", "icon/icon64.png", "icon/icon32.png");
        configuration.useVsync(true);
        configuration.setForegroundFPS(60);
        configuration.setIdleFPS(1);

        return configuration;
    }
}
