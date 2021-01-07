package com.company.gameplay;

import com.company.Profil;
import org.fusesource.jansi.Ansi;

/**
 * Représente un joueur lors d'une partie
 */
class Joueur {

    public final Profil profil;

    /**
     * Identique à profil.nom
     */
    public final String nom;
    /**
     * Identique à profil.couleur
     */
    public final Ansi.Color couleur;

    public int posX;
    public int posY;

    private boolean elimine;

    public boolean estElimine() {
        return elimine;
    }

    /**
     * Déclare le joueur éliminer
     */
    public void eliminer() {
        elimine = true;
    }

    public Joueur(Profil profil) {
        this.profil = profil;
        nom = profil.nom;
        couleur = profil.couleur;
    }
}
