package com.project.game.key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * La classe {@code KeyManager} gère les touches de contrôle du joueur pour déplacer un personnage à l'écran.
 * Elle permet de définir les touches par défaut, de récupérer les noms des touches actuelles et de calculer
 * les déplacements horizontaux et verticaux en fonction des touches appuyées.
 */
public class KeyManager {

    private static int left = Input.Keys.LEFT;
    private static int right = Input.Keys.RIGHT;
    private static int up = Input.Keys.UP;
    private static int down = Input.Keys.DOWN; // Codes des touches

    /**
     * Constructeur de la classe {@code KeyManager}.
     * Initialise les touches de contrôle avec les valeurs par défaut.
     *
     * @param filePath Chemin du fichier de configuration des touches (non utilisé dans cette version).
     */
    public KeyManager(String filePath) {
        setDefaultKeys();
    }

    /**
     * Initialise les touches de contrôle avec les valeurs par défaut.
     * Les touches par défaut sont : Flèche gauche, Flèche droite, Flèche haut, Flèche bas.
     */
    private void setDefaultKeys() {
        left = Input.Keys.LEFT;
        right = Input.Keys.RIGHT;
        up = Input.Keys.UP;
        down = Input.Keys.DOWN;

        System.out.println("Touches par défaut initialisées :");
        System.out.println("Left: " + Input.Keys.toString(left));
        System.out.println("Right: " + Input.Keys.toString(right));
        System.out.println("Up: " + Input.Keys.toString(up));
        System.out.println("Down: " + Input.Keys.toString(down));
    }

    /**
     * Récupère le nom de la touche associée à la direction "haut".
     *
     * @return Le nom de la touche "haut".
     */
    public static String getUpKeyName() {
        return Input.Keys.toString(up);
    }

    /**
     * Récupère le nom de la touche associée à la direction "bas".
     *
     * @return Le nom de la touche "bas".
     */
    public static String getDownKeyName() {
        return Input.Keys.toString(down);
    }

    /**
     * Récupère le nom de la touche associée à la direction "gauche".
     *
     * @return Le nom de la touche "gauche".
     */
    public static String getLeftKeyName() {
        return Input.Keys.toString(left);
    }

    /**
     * Récupère le nom de la touche associée à la direction "droite".
     *
     * @return Le nom de la touche "droite".
     */
    public static String getRightKeyName() {
        return Input.Keys.toString(right);
    }

    /**
     * Met à jour la position horizontale du joueur en fonction des touches de déplacement.
     *
     * @param x       Position actuelle sur l'axe X.
     * @param vitesse Vitesse de déplacement.
     * @param delta   Temps écoulé depuis le dernier appel (pour une mise à jour basée sur le temps).
     * @return La nouvelle position sur l'axe X après application du mouvement.
     */
    public float mouv_abs(float x, float vitesse, float delta) {
        if (Gdx.input.isKeyPressed(left) && left != Input.Keys.UNKNOWN) {
            x -= vitesse * delta;
        }
        if (Gdx.input.isKeyPressed(right) && right != Input.Keys.UNKNOWN) {
            x += vitesse * delta;
        }
        return x;
    }

    /**
     * Met à jour la position verticale du joueur en fonction des touches de déplacement.
     *
     * @param y       Position actuelle sur l'axe Y.
     * @param vitesse Vitesse de déplacement.
     * @param delta   Temps écoulé depuis le dernier appel (pour une mise à jour basée sur le temps).
     * @return La nouvelle position sur l'axe Y après application du mouvement.
     */
    public float mouv_ord(float y, float vitesse, float delta) {
        if (Gdx.input.isKeyPressed(up) && up != Input.Keys.UNKNOWN) {
            y += vitesse * delta;
        }
        if (Gdx.input.isKeyPressed(down) && down != Input.Keys.UNKNOWN) {
            y -= vitesse * delta;
        }
        return y;
    }
}
