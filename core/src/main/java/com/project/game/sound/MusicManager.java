package com.project.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Gestionnaire de musique pour l'application.
 * Permet de jouer, arrêter, régler le volume et gérer la musique de fond.
 */
public class MusicManager {

    /**
     * Musique actuellement en lecture.
     */
    private Music currentMusic;

    /**
     * Volume actuel de la musique, compris entre 0.0 (silence) et 1.0 (volume maximal).
     */
    private float volume = 1.0f;

    /**
     * Joue une nouvelle musique à partir du fichier spécifié.
     * Arrête et libère la musique actuelle si elle est en cours de lecture.
     *
     * @param filePath Chemin relatif du fichier musical à jouer.
     */
    public void play(String filePath) {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }

        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        currentMusic.setLooping(true);
        currentMusic.setVolume(volume);
        currentMusic.play();
    }

    /**
     * Arrête la musique actuellement en lecture.
     * Ne libère pas les ressources associées.
     */
    public void stop() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    /**
     * Libère les ressources associées à la musique actuelle
     * et met à jour l'état du gestionnaire.
     */
    public void dispose() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
        }
    }

    /**
     * Modifie le volume actuel en augmentant ou en diminuant selon une valeur donnée.
     * Le volume reste compris entre 0.0 et 1.0.
     *
     * @param modification Variation de volume (positive ou négative).
     * @return Nouveau volume après modification.
     */
    public float volumeModification(float modification)
    {
        if (modification > 0)
        {
            this.setVolume(Math.min(volume + modification, 1.0f));
        }
        else if (modification < 0)
        {
            this.setVolume(Math.max(volume + modification, 0.0f));
        }
        return this.volume;
    }

    /**
     * Définit le volume actuel de la musique.
     * Si une musique est en cours de lecture, son volume est mis à jour.
     *
     * @param volume Nouveau volume, compris entre 0.0 et 1.0.
     */
    public void setVolume(float volume) {
        this.volume = roundVolume(volume);
        if (currentMusic != null) {
            currentMusic.setVolume(this.volume);
        }
    }

    /**
     * Joue un "son" définit en paramètre.
     *
     * @param sound "Son" joué
     */
    public void playSound(Sound sound) {
        if (sound != null) {
            sound.play(new MusicManager().getVolume());
        }
    }

    /**
     * Retourne le volume actuel de la musique.
     *
     * @return Volume actuel, compris entre 0.0 et 1.0.
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Arrondit une valeur de volume à une décimale.
     *
     * @param value Valeur du volume à arrondir.
     * @return Volume arrondi à une décimale.
     */
    public static float roundVolume(float value) {
        return Math.round(value * 10) / 10.0f;
    }
}
