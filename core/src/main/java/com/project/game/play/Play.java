package com.project.game.play;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.project.game.sound.MusicManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Cette classe gère la logique du jeu, y compris le vaisseau, les ennemis, les projectiles,
 * ainsi que la gestion des vies, du score, du niveau et des vagues d'ennemis.
 */
public class Play {
    private Player vaisseau;
    private ArrayList<com.project.game.play.Projectile> projectiles;
    private ArrayList<Enemy> ennemis;
    private BitmapFont police;
    private int vies = 5; // Initialisation des vies à 5
    private int score = 0;
    private boolean gameOver = false;
    private float invincibilityTime = 2.0f;
    private float invincibilityTimer = 0.0f;

    private int niveau = 1;
    private int ennemisPourNiveau = 20; // Ennemis nécessaires pour monter de niveau
    private int ennemisDetruits = 0; // Compteur d’ennemis détruits
    private int ennemisMax = 13; // Nombre maximal d’ennemis visibles
    private int ennemisMin = 7; // Nombre minimal d’ennemis visibles
    private float vitesseMax = 400f; // Vitesse maximale des ennemis

    /**
     * Different son joué pendant une partie
     */
    private Sound soundProjectile, soundLife, explosionSound;
    /**
     * Constructeur de la classe Play. Initialise le vaisseau, les projectiles, les ennemis, et le texte de la police.
     * Une vague d'ennemis est également ajoutée à ce moment.
     */
    public Play() {
        vaisseau = new Player();
        projectiles = new ArrayList<>();
        ennemis = new ArrayList<>();
        police = new BitmapFont();
        police.getData().setScale(2);
        ajouterVague();
    }

    /**
     * Met à jour l'état du jeu, y compris les mouvements du vaisseau, les projectiles et les ennemis.
     * Gère également les collisions et l'invincibilité du joueur.
     *
     * Joue le son de collisions
     *
     * @param delta Temps écoulé depuis la dernière mise à jour (en secondes).
     */
    public void update(float delta) {
        if (gameOver) return;

        if (invincibilityTimer > 0) {
            invincibilityTimer -= delta;
        }

        // Ajout d'ennemis dynamiquement pour rester entre les limites
        if (ennemis.size() < ennemisMin) {
            ajouterVague();
        }

        vaisseau.update(delta);

        projectiles.removeIf(projectile -> {
            projectile.update(delta);
            return !projectile.isActive();
        });

        Iterator<Enemy> it = ennemis.iterator();
        while (it.hasNext()) {
            Enemy ennemi = it.next();
            ennemi.update(delta);

            if (ennemi.isOffScreen()) {
                it.remove();
            } else if (invincibilityTimer <= 0 && checkCollision(vaisseau.getX(), vaisseau.getY(), vaisseau.getWidth(),
                    vaisseau.getHeight(), ennemi.getX(), ennemi.getY(), ennemi.getWidth(), ennemi.getHeight())) {
                vies--;
                soundLife = Gdx.audio.newSound(Gdx.files.internal("sound/sound_destroy.mp3"));
                new MusicManager().playSound(soundLife);
                invincibilityTimer = invincibilityTime;
                ennemi.destroy();
                if (vies <= 0) {
                    gameOver = true;
                }
            } else if (ennemi.estDetruit() && ennemi.isReadyToRemove()) {
                it.remove();
                ennemisDetruits++;
                score += niveau; // Augmentation progressive du score

                // Vérification pour augmenter le niveau
                if (ennemisDetruits >= ennemisPourNiveau) {
                    niveau++;
                    ennemisPourNiveau += 3; // Incrémentation progressive du seuil
                    ennemisDetruits = 0;
                }
            }
        }

        detectCollisions();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            tirer();
        }
    }

    /**
     * Détecte les collisions entre les projectiles et les ennemis.
     *
     * Joue le son de collisions
     */
    private void detectCollisions() {
        for (com.project.game.play.Projectile projectile : projectiles) {
            Iterator<Enemy> it = ennemis.iterator();
            while (it.hasNext()) {
                Enemy ennemi = it.next();
                if (checkCollision(projectile.getX(), projectile.getY(), 10, 10,
                        ennemi.getX(), ennemi.getY(), ennemi.getWidth(), ennemi.getHeight())) {
                    explosionSound = Gdx.audio.newSound(Gdx.files.internal("sound/sound_destroy.mp3"));
                    new MusicManager().playSound(explosionSound);
                    ennemi.destroy();
                    projectile.setInactive();
                    break;
                }
            }
        }
    }

    /**
     * Vérifie si deux rectangles (définis par leur position et leur taille) se chevauchent.
     *
     * @param x1, y1, width1, height1 Position et taille du premier rectangle.
     * @param x2, y2, width2, height2 Position et taille du second rectangle.
     * @return true si les rectangles se chevauchent, false sinon.
     */
    private boolean checkCollision(float x1, float y1, float width1, float height1,
                                   float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 && x1 + width1 > x2 &&
               y1 < y2 + height2 && y1 + height1 > y2;
    }

    /**
     * Ajoute les différentes vagues d'ennemis
     */
    private void ajouterVague() {
        int ennemisAAjouter = ennemisMax - ennemis.size();
        int espaceEntreEnnemis = Gdx.graphics.getWidth() / (ennemisAAjouter + 1);

        for (int i = 0; i < ennemisAAjouter; i++) {
            float x = (i + 1) * espaceEntreEnnemis - 25;
            Enemy ennemi = new Enemy(x, Gdx.graphics.getHeight());

            // Réglage de la vitesse avec une limite maximale
            float nouvelleVitesse = ennemi.getVitesse() + 0.5f * niveau;
            ennemi.setVitesse(Math.min(nouvelleVitesse, vitesseMax));

            ennemis.add(ennemi);
        }
    }

    /**
     * Ajoute une nouvelle vague d'ennemis à l'écran.
     */
    public void render(SpriteBatch batch) {
        if (gameOver) {
            displayGameOver(batch);
            return;
        }

        vaisseau.render(batch);
        for (com.project.game.play.Projectile projectile : projectiles) {
            projectile.render(batch);
        }
        for (Enemy ennemi : ennemis) {
            ennemi.render(batch);
        }

        police.draw(batch, "Lives: " + vies, 10, Gdx.graphics.getHeight() - 10);
        police.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight() - 40);
        police.draw(batch, "Level: " + niveau, 10, Gdx.graphics.getHeight() - 70);
        if (invincibilityTimer > 0) {
            police.draw(batch, "Invincibility: " + (int) invincibilityTimer, 10, Gdx.graphics.getHeight() - 100);
        }
    }

    /**
     * Affiche le texte du game over à l'écran.
     *
     * @param batch SpriteBatch utilisé pour rendre le texte.
     */
    private void displayGameOver(SpriteBatch batch) {
        police.getData().setScale(5);
        String text = "GAME OVER\nScore: " + score;
        float x = (Gdx.graphics.getWidth() - police.getData().scaleX * text.length() * 10) / 2;
        float y = Gdx.graphics.getHeight() / 2;
        police.draw(batch, text, x, y);
    }

    /**
     * Permet au joueur de tirer un projectile.
     *
     * Joue le son du projectile
     */
    public void tirer() {
        projectiles.add(new com.project.game.play.Projectile(vaisseau.getX() + 20, vaisseau.getY() + 40));
        soundProjectile = Gdx.audio.newSound(Gdx.files.internal("sound/sound_projectile.mp3"));
        new MusicManager().playSound(soundProjectile);
    }

    /**
     * Libère les ressources allouées pour le jeu, y compris les entités et la police.
     */
    public void dispose() {
        vaisseau.dispose();
        projectiles.forEach(Projectile::dispose);
        ennemis.forEach(Enemy::dispose);
        police.dispose();
        if (soundLife != null) {
            soundLife.dispose();
        }
        if (soundProjectile != null) {
            soundProjectile.dispose();
        }
        if (explosionSound != null) {
            explosionSound.dispose();
        }
    }

    public void setEnnemisMin(int ennemisMin) {
        this.ennemisMin = ennemisMin;
    }

    public void setEnnemisMax(int ennemisMax) {
        this.ennemisMax = ennemisMax;
    }

    public void setEnnemisPourNiveau(int ennemisPourNiveau) {
        this.ennemisPourNiveau = ennemisPourNiveau;
    }

    public void setVitesseMax(float vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    public void setVies(int vies) {
        this.vies = vies;
    }
}
